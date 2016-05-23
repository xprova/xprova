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

//			c.runCommand("set_flops QDFFRSBX1 DFFx");

			c.runCommand("def_ff QDFFRSBX1 CK RS");

			c.runCommand("def_ff DFF CK RS");

			c.runCommand("list_ff");

			c.runCommand("read_verilog -m top tests/source.v");

//			c.runCommand("augment_netlist");

			c.runCommand("write_verilog tests/augmented.v");

			c.runCommand("export_dot --ignore-edges=SB,RB,CK --ignore-vertices=U1,resetn,clk1,clk2 "
					+ "--type=fg "
					+ "--combine=unit2_add_91_U1,unit2_counter_reg,unit2_AC_reg,unit1_data_reg "
					+ "tests/source.dot");

			c.runCommand("! dot -Tpdf tests/source.dot -o tests/source.pdf");

		} catch (Exception e) {

			e.printStackTrace();

//			e.getCause().printStackTrace();

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
