package net.xprova.simulations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Waveform {

	private ArrayList<String> sigNames;

	private HashMap<String, int[]> sigWaveforms;

	private int cycles;

	private static final int L = 0;

	private static final int H = -1;

	private static final int M = 0xf0f0f0f0;

	public Waveform() {

		sigNames = new ArrayList<String>();

		sigWaveforms = new HashMap<String, int[]>();

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

			int[] sigData = sigWaveforms.get(sig);

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

	public void readTextFile(String file) throws Exception {

		File f = new File(file);

		if (f.exists()) {

			BufferedReader br = new BufferedReader(new FileReader(f));

			sigNames = new ArrayList<String>();

			sigWaveforms = new HashMap<String, int[]>();

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

						sigWaveforms.put(sigName, sigData);

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

}
