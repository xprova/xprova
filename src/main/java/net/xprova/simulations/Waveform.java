package net.xprova.simulations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Waveform {

	private ArrayList<String> sigNames;

	private HashMap<String, int[]> waveforms;

	private int cycles;

	private static final int L = 0;

	private static final int H = -1;

	private static final int M = 0xf0f0f0f0;

	public Waveform() {

		sigNames = new ArrayList<String>();

		waveforms = new HashMap<String, int[]>();

		cycles = 0;
	}

	public void print(PrintStream out) {

		int maxL = 0;

		for (String s : sigNames)
			maxL = s.length() > maxL ? s.length() : maxL;

		String strFmt = String.format("%%%ds : ", maxL + 2);

		out.printf(strFmt, "Cycle");

		for (int i = 0; i < cycles; i++)
			System.out.printf("%d", i % 10);

		out.println();

		out.println();

		for (String sig : sigNames) {

			if (isVisible(sig)) {

				int[] sigData = waveforms.get(sig);

				System.out.printf(strFmt, sig);

				for (int i = 0; i < cycles; i++) {

					if (sigData[i] == H)
						out.printf("1");
					else if (sigData[i] == L)
						out.printf("0");
					else
						out.printf("X");

				}

				out.println();

			}

		}
	}

	public void readTextFile(String file) throws Exception {

		File f = new File(file);

		if (f.exists()) {

			BufferedReader br = new BufferedReader(new FileReader(f));

			sigNames = new ArrayList<String>();

			waveforms = new HashMap<String, int[]>();

			cycles = 0;

			try {

				while (true) {

					String line = br.readLine();

					if (line == null)
						break;

					if (!line.isEmpty()) {

						int k = line.indexOf(" : ");

						String sigName = line.substring(0, k).trim();

						String sigDataStr = line.substring(k + 3);

						int n = sigDataStr.length();

						int[] sigData = new int[n];

						for (int j = 0; j < n; j++) {

							char c = sigDataStr.charAt(j);

							if (c == '1')
								sigData[j] = H;
							else if (c == '0')
								sigData[j] = L;
							else
								sigData[j] = M;

						}

						sigNames.add(sigName);

						waveforms.put(sigName, sigData);

						cycles = cycles == 0 ? n : cycles;

						if (cycles != n)
							throw new Exception("signal " + sigName + " had a different number of cycles");

					}

				}

			} finally {

				br.close();

			}

		} else {

			throw new Exception("file + " + f.getAbsolutePath() + " does not exist");

		}

	}

	public void writeVCDFile(String file, boolean runGtkwave) throws Exception {

		File vcdFile = new File(file);

		ArrayList<String> vcdLines = new ArrayList<String>();

		ArrayList<String> tclLines = new ArrayList<String>();

		// rename signals with spaces

		ArrayList<String> sigNamesVCD = new ArrayList<String>();

		for (int i = 0; i < sigNames.size(); i++) {

			String s = sigNames.get(i);

			if (s.startsWith("@"))
				s = "prop-" + s.replace(" ", "-");

			if (s.startsWith("\\"))
				s = s.substring(1);

			sigNamesVCD.add(s);

		}

		// prepare vcd file content

		vcdLines.add("$timescale 1ns $end");

		vcdLines.add("$scope module logic $end");

		for (int i = 0; i < sigNames.size(); i++)
			vcdLines.add(String.format("$var wire 1 %s %s $end", getIdentifierVCD(i), sigNamesVCD.get(i)));

		vcdLines.add("$upscope $end");

		vcdLines.add("$enddefinitions $end");

		vcdLines.add("$dumpvars");

		for (int i = 0; i < sigNames.size(); i++)
			vcdLines.add(String.format("x%s", getIdentifierVCD(i)));

		vcdLines.add("$end");

		for (int j = 0; j < cycles; j++) {

			vcdLines.add(String.format("#%d", j));

			for (int i = 0; i < sigNames.size(); i++) {

				int[] sigWaveform = waveforms.get(sigNames.get(i));

				int newVal = sigWaveform[j];

				int oldVal = j > 0 ? sigWaveform[j - 1] : newVal + 1;

				if (newVal != oldVal) {

					String newValStr;

					if (newVal == -1)
						newValStr = "1";
					else if (newVal == 0)
						newValStr = "0";
					else
						newValStr = "x";

					vcdLines.add(String.format("%s%s", newValStr, getIdentifierVCD(i)));

				}

			}

		}

		vcdLines.add(String.format("#%d", cycles));

		// writing vcd file

		File tempDir = new File(System.getProperty("java.io.tmpdir"));

		System.out.println("Saving counter-example waveform data (VCD) to " + vcdFile.getAbsolutePath() + " ...");

		PrintStream fout = new PrintStream(vcdFile);

		for (String l : vcdLines)
			fout.println(l);

		fout.close();

		if (runGtkwave) {

			// prepare gtkwave tcl script content

			// gtkwave has an issue with adding array bits
			// as a walk-around add the array name instead
			// of the individual bits

			tclLines.add("set sigList [list]");

			for (String s : sigNamesVCD) {

				if (isVisible(s)) {

					if (!s.startsWith("\\") && s.contains("[")) {

						if (s.contains("[0]")) {

							s = s.replaceAll("\\[\\d+\\]", "");

						} else {

							continue;

						}

					}

					tclLines.add(String.format("lappend sigList {%s}", s));

				}
			}

			tclLines.add("set num_added [ gtkwave::addSignalsFromList $sigList ]");

			// write tcl script

			File tclFile = new File(tempDir, "counter-example-show.tcl");

			System.out.println("Saving gtkwave tcl script to " + tclFile.getAbsolutePath() + " ...");

			PrintStream fout2 = new PrintStream(tclFile);

			for (String l : tclLines)
				fout2.println(l);

			fout2.close();

			// run gtkwave

			String cmd = String.format("gtkwave \"%s\" -S \"%s\"", vcdFile.getAbsolutePath(),
					tclFile.getAbsolutePath());

			final Runtime rt = Runtime.getRuntime();

			try {

				rt.exec(cmd);

			} catch (IOException e) {

				e.printStackTrace();

				throw new Exception("unable to run gtkwave, make sure it is installed and setup in PATH");

			}

		}

	}

	private String getIdentifierVCD(int i) {

		// maps an integer to a valid VCD identifier

		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		int n = chars.length();

		String result = "";

		do {

			int d = i % n;

			result = chars.charAt(d) + result;

			i = (i - d) / n;

		} while (i > 0);

		return result;

	}

	private boolean isVisible(String signal) {

		// determines which signals are printed to console or written to file

		// TODO: replace heuristics used here with better rules

		if (signal.contains(".clk"))
			return false;

		if (signal.contains(".rst"))
			return false;

		if (signal.startsWith("_") && signal.endsWith("_"))
			return false;

		return true;

	}

}
