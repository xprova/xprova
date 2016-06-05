public ArrayList<int[]> simulate(int[] initial, ArrayList<int[]> inputs, int cycles) {

	// int[] {STATE_BIT} = new int[cycles];

	// {STATE_BIT}[0] = initial[{STATE_BIT_INDEX}];

	// int {INPUT_BIT}[] = inputs.get({INPUT_BIT_INDEX});

	// int[] {NON_STATE_BIT} = new int[cycles];

	for (int i=0; i<cycles; i++) {
	
		// {COMB_ASSIGN}

		if (i < cycles-1) {
		
			// {STATE_ASSIGN} {POSTFIX1=[i+1]} {POSTFIX2=[i]} 

		}

	}

	ArrayList<int[]> waveforms = new ArrayList<int[]>();

	// waveforms.add({STATE_BIT});

	// waveforms.add({INPUT_BIT});
	
	// waveforms.add({NON_STATE_BIT});

	return waveforms;
}

public ArrayList<String> getSignalNames() {

	ArrayList<String> result = new ArrayList<String>();

	// result.add("{STATE_BIT}");

	// result.add("{INPUT_BIT}");
	
	// result.add("{NON_STATE_BIT}");

	return result;
}

public int getStateBitCount() {

	return {STATE_BIT_COUNT};
}

public int getInputBitCount() {

	return {INPUT_BIT_COUNT};
}