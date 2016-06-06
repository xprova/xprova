	public ArrayList<int[]> simulate(int[] initial, ArrayList<int[]> inputs, int cycles) {

		//@formatter:off
		// int[] {STATE_BIT} = new int[cycles];

		// {STATE_BIT}[0] = initial[{STATE_BIT_INDEX}];

		// int {INPUT_BIT}[] = inputs.get({INPUT_BIT_INDEX});

		// int[] {NON_STATE_BIT} = new int[cycles];
		//@formatter:on

		for (int i=0; i<cycles; i++) {

			//@formatter:off
			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}

			if (i < cycles-1) {

				// {STATE_ASSIGN} {POSTFIX1=[i+1]} {POSTFIX2=[i]}

			}
			//@formatter:on

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});

		// waveforms.add({INPUT_BIT});

		// waveforms.add({NON_STATE_BIT});
		//@formatter:on

		return waveforms;
	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		//@formatter:off
		// result.add("{STATE_BIT}");

		// result.add("{INPUT_BIT}");

		// result.add("{NON_STATE_BIT}");
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		//@formatter:on
	}