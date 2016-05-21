package net.xprova.xprova;

import java.io.InputStream;
import java.util.Scanner;

import net.xprova.piccolo.Console;

public class Main {

	private static String loadBanner() {

		Scanner s = null;

		String bannerFileContent;

		try {

			final InputStream stream;

			stream = Console.class.getClassLoader().getResourceAsStream("xprova_banner.txt");

			s = new Scanner(stream);

			bannerFileContent = s.useDelimiter("\\Z").next();

		} catch (Exception e) {

			bannerFileContent = "<could not load internal banner file>\n";

		} finally {

			if (s != null)
				s.close();

		}

		return bannerFileContent;

	}

	private static void runDebug(Console c) {

		try {

			c.runCommand("ll tests/minfar.lib");
			c.runCommand("read_verilog -m top tests/source.v");
			// c.runCommand("augment_netlist");
			// c.runCommand("write_verilog tests/augmented.v");
			// c.runCommand("temp2 unit2_AC_reg_2_ tests/source.dot");
			c.runCommand("export_dot --ignore-edges=SB,RB,CK --ignore-vertices=U1,resetn,clk2 "
					+ "--type=fng --to=unit2_AC_reg_2_ tests/source.dot");

			// c.runCommand("! dot -Tpng tests/source.dot -o tests/source.png");
			c.runCommand("! dot -Tpdf tests/source.dot -o tests/source.pdf");

		} catch (Exception e) {

			e.getCause().printStackTrace();

		}

	}

	public static void main(String[] args) {

		Console c = new Console();

		c.addHandler(new ConsoleHandler(System.out));

		c.setBanner(loadBanner());

		if (args.length == 1 && "runTests".equals(args[0])) {

			runDebug(c);

			return;

		} else {

			c.run();

		}

	}

}
