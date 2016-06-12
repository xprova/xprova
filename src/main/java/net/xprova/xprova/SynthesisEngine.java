package net.xprova.xprova;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import net.xprova.piccolo.Console;

public class SynthesisEngine {

	public void synthesis(String behavioralDesign, String synthDesign, PrintStream out) throws Exception {

		// step 1: create temporary cell lib file

		File cellLibFile = File.createTempFile("mycells", ".lib");

		PrintWriter writer2 = new PrintWriter(cellLibFile);

		writer2.print(loadResourceString("mycells.lib"));

		writer2.close();

		// step 2: create temporary yosys script

		String yScript = loadResourceString("yosys_synth.ys");

		String[][] subs = { { "%BEHAV_FILE%", behavioralDesign }, { "%TOP%", "" },
				{ "%CELL_LIB%", cellLibFile.getAbsolutePath() }, { "%SYNTH_FILE%", synthDesign } };

		for (String[] sub : subs)
			yScript = yScript.replace(sub[0], sub[1]);

		File yScriptFile = File.createTempFile("yosys-script", ".ys");

		PrintWriter writer1 = new PrintWriter(yScriptFile);

		writer1.print(yScript);

		writer1.close();

		// step 3: execute yosys

		String cmd = "yosys -q -s " + yScriptFile;

		final Runtime rt = Runtime.getRuntime();

		Process proc;

		try {

			proc = rt.exec(cmd);

		} catch (IOException e) {

			throw new Exception("unable to run yosys, make sure it is installed and setup in PATH");

		}

		try {

			proc.waitFor();

		} catch (InterruptedException e) {

			throw new Exception("error while waiting for yosys to terminate");
		}

		if (proc.exitValue() == 0) {

			out.println("yosys synthesis complete");

		} else {

			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

			String s = null;

			out.println("yosys stderr:");

			while ((s = stdError.readLine()) != null)
				out.println(s);

			throw new Exception("yosys terminated with exit code = " + proc.exitValue());

		}

		// step 4: cleanup

		cellLibFile.delete();
		yScriptFile.delete();
	}

	private static String loadResourceString(String file) {

		Scanner s = null;

		String bannerFileContent;

		try {

			final InputStream stream;

			stream = Console.class.getClassLoader().getResourceAsStream(file);

			s = new Scanner(stream);

			bannerFileContent = s.useDelimiter("\\Z").next();

		} catch (Exception e) {

			bannerFileContent = "<could not load internal file>\n";

		} finally {

			if (s != null)
				s.close();

		}

		return bannerFileContent;

	}

}
