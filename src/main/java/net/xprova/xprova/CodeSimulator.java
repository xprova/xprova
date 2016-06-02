package net.xprova.xprova;

import java.util.ArrayList;

public class CodeSimulator {

	public void simulate() {

		GrayCounter gray1 = new GrayCounter();

		int[] initial = { 0, 0 };

		ArrayList<String> sigNames = gray1.getSignalNames();

		int[] ena = { -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0 };

		ArrayList<int[]> inputs = new ArrayList<int[]>();

		inputs.add(ena);

		int cycles = ena.length;

		ArrayList<int[]> results = gray1.simulate(initial, inputs, cycles);

		System.out.printf("%10s : ", "Cycle");

		for (int i = 0; i < cycles; i++)
			System.out.printf("%d", i % 10);

		System.out.println();

		System.out.println();

		for (int j = 0; j < results.size(); j++) {

			if (j == gray1.getStateBitCount())
				System.out.println();

			int[] sig = results.get(j);

			System.out.printf("%10s : ", sigNames.get(j));

			for (int i = 0; i < cycles; i++) {

				System.out.printf((sig[i] == -1) ? "1" : "0");

			}

			System.out.println();

		}

	}

}
