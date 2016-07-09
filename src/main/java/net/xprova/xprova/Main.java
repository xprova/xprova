package net.xprova.xprova;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

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

	public static void main(String[] args) throws Exception {

		// parse command input

		Option optModule = Option.builder("s").desc("name of script file to execute").hasArg().argName("SCRIPT_FILE")
				.required(false).build();

		Options options = new Options();

		options.addOption(optModule);

		CommandLineParser parser = new DefaultParser();

		CommandLine line = parser.parse(options, args);

		// prepare and run console

		Console c = new Console();

		c.addHandler(new ConsoleHandler(System.out));

		String banner = loadBanner();

		String version = Main.class.getPackage().getImplementationVersion();

		banner = banner.replace("{VERSION}", version == null ? "(debug)" : version);

		c.setBanner(banner);

		if (line.hasOption("s")) {

			c.runScript(line.getOptionValue("s"));

		} else {

			c.run();

		}

	}

}
