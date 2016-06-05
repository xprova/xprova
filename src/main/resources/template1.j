public void exploreSpace() throws Exception {

	// int stateBitCount = {STATE_BIT_COUNT};
	int stateBitCount = 5; // {EXPANDED}
	// int inputBitCount = {INPUT_BIT_COUNT};
	int inputBitCount = 2; // {EXPANDED}

	// int {STATE_BIT} = 0;
	int count_0_ = 0; // {EXPANDED}
	int count_1_ = 0; // {EXPANDED}
	int count_2_ = 0; // {EXPANDED}
	int count_3_ = 0; // {EXPANDED}
	int n26 = 0; // {EXPANDED}

	// int {NON_STATE_BIT};
	int n1; // {EXPANDED}
	int n10; // {EXPANDED}
	int n11; // {EXPANDED}
	int n12; // {EXPANDED}
	int n13; // {EXPANDED}
	int n14; // {EXPANDED}
	int n15; // {EXPANDED}
	int n16; // {EXPANDED}
	int n17; // {EXPANDED}
	int n18; // {EXPANDED}
	int n19; // {EXPANDED}
	int n2; // {EXPANDED}
	int n20; // {EXPANDED}
	int n21; // {EXPANDED}
	int n22; // {EXPANDED}
	int n23; // {EXPANDED}
	int n24; // {EXPANDED}
	int n25; // {EXPANDED}
	int n3; // {EXPANDED}
	int n4; // {EXPANDED}
	int n5; // {EXPANDED}
	int n6; // {EXPANDED}
	int n7; // {EXPANDED}
	int n8; // {EXPANDED}
	int n9; // {EXPANDED}
	int valid; // {EXPANDED}

	Graph<Integer> stateGraph = new Graph<Integer>();

	HashSet<Integer> toVisit = new HashSet<Integer>();

	HashSet<Integer> visited = new HashSet<Integer>();

	HashMap<Integer, Integer> distanceFromInitial = new HashMap<Integer, Integer>();

	// key: (state, next_state)
	// value: input vector
	MultiMap<Integer, Integer, Integer> iVectors = new MultiMap<Integer, Integer, Integer>();

	distanceFromInitial.put(0, 0);

	int distance = 1;

	toVisit.add(0);

	Integer violationState = null;

	loop1: while (!toVisit.isEmpty()) {

		HashSet<Integer> toVisitNext = new HashSet<Integer>();

		for (Integer state : toVisit) {

			// {STATE_BIT} = (state >> {STATE_BIT_INDEX} & 1) * -1;
			count_0_ = (state >> 0 & 1) * -1; // {EXPANDED}
			count_1_ = (state >> 1 & 1) * -1; // {EXPANDED}
			count_2_ = (state >> 2 & 1) * -1; // {EXPANDED}
			count_3_ = (state >> 3 & 1) * -1; // {EXPANDED}
			n26 = (state >> 4 & 1) * -1; // {EXPANDED}

			String stateBin = getBinary(state, stateBitCount);

			stateGraph.addVertex(state);

			int inputPermutes = 2 ^ 5;

			for (int in = 0; in < inputPermutes; in++) {

				// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);

				// {COMB_ASSIGN}
				n19 = ~(count_0_ & count_1_); // {EXPANDED}
				n6 = ~(count_3_ | count_1_); // {EXPANDED}
				n5 = ~count_0_; // {EXPANDED}
				n17 = (count_0_ ^ count_1_); // {EXPANDED}
				n8 = count_3_ | count_2_; // {EXPANDED}
				n22 = ~count_2_; // {EXPANDED}
				n12 = ~ena2; // {EXPANDED}
				n10 = ~(n8 | count_1_); // {EXPANDED}
				n7 = ~(n6 & n5); // {EXPANDED}
				n11 = ena1 & n10; // {EXPANDED}
				n9 = n8 & n26; // {EXPANDED}
				n13 = ~(n10 | n12); // {EXPANDED}
				n23 = ~(n22 | n19); // {EXPANDED}
				n16 = n11 | n13; // {EXPANDED}
				n24 = (count_3_ ^ n23); // {EXPANDED}
				n14 = ~(n11 | n13); // {EXPANDED}
				valid = ~(n7 & n9); // {EXPANDED}
				n25 = ~(n24 & n16); // {EXPANDED}
				n21 = ~(count_3_ & n14); // {EXPANDED}
				n20 = ~(n19 | n14); // {EXPANDED}
				n18 = ~(n17 & n16); // {EXPANDED}
				n1 = (n14 ^ n5); // {EXPANDED}
				n15 = ~(n14 & count_1_); // {EXPANDED}
				n2 = ~(n18 & n15); // {EXPANDED}
				n4 = ~(n25 & n21); // {EXPANDED}
				n3 = (n20 ^ count_2_); // {EXPANDED}

				// {STATE_ASSIGN} {PREFIX1=int next_}
				int next_count_0_ = n1; // {EXPANDED}
				int next_count_1_ = n2; // {EXPANDED}
				int next_count_2_ = n3; // {EXPANDED}
				int next_count_3_ = n4; // {EXPANDED}
				int next_n26 = ena1; // {EXPANDED}

				int nxState = 0;

				// nxState += (next_{STATE_BIT} & 1) << {STATE_BIT_INDEX};
				nxState += (next_count_0_ & 1) << 0; // {EXPANDED}
				nxState += (next_count_1_ & 1) << 1; // {EXPANDED}
				nxState += (next_count_2_ & 1) << 2; // {EXPANDED}
				nxState += (next_count_3_ & 1) << 3; // {EXPANDED}
				nxState += (next_n26 & 1) << 4; // {EXPANDED}

				int inputVector = 0;

				// inputVector += ({INPUT_BIT} & 1) << {INPUT_BIT_INDEX};
				inputVector += (ena1 & 1) << 0; // {EXPANDED}
				inputVector += (ena2 & 1) << 1; // {EXPANDED}

				toVisitNext.add(nxState);

				Integer currentDistance = distanceFromInitial.getOrDefault(nxState, Integer.MAX_VALUE);

				distanceFromInitial.put(nxState, distance < currentDistance ? distance : currentDistance);

				iVectors.put(state, nxState, inputVector);

				String nextStateBin = getBinary(nxState, stateBitCount);

				String inputVecBin = getBinary(inputVector, inputBitCount);

				System.out.printf("discovered %s (%2d) -> %s (%2d) (distance = %d), input vector = %s\n", stateBin,
						state, nextStateBin, nxState, distance, inputVecBin);

				stateGraph.addVertex(nxState);

				stateGraph.addConnection(state, nxState);

				if (valid == 0) {

					violationState = nxState;

					break loop1;

				}

			}

		}

		visited.addAll(toVisit);

		toVisitNext.removeAll(visited);

		toVisit = toVisitNext;

		distance = distance + 1;

	}

	GraphDotPrinter.printGraph("output/state.dot", stateGraph);

	if (violationState != null) {

		System.out.println("Counter-example found!");

		// now back track to find shortest path from violationState to
		// initial state

		ArrayList<Integer> counterExampleState = new ArrayList<Integer>();

		ArrayList<Integer> counterExampleInputVectors = new ArrayList<Integer>();

		HashSet<Integer> visitedStates = new HashSet<Integer>();

		Integer current = violationState;

		int shortestDistance = distance + 1;

		Integer lastCurrent = current;

		while (current != 0) {

			counterExampleState.add(current);

			if (current != lastCurrent)
				counterExampleInputVectors.add(iVectors.get(current, lastCurrent));

			lastCurrent = current;

			HashSet<Integer> prevStates = stateGraph.bfs(current, 1, true);

			prevStates.removeAll(visitedStates);

			if (prevStates.isEmpty())
				throw new Exception("could not generate counter example.");

			for (Integer previousState : prevStates) {

				int d = distanceFromInitial.get(previousState);

				if ((d < shortestDistance) || (previousState == 0)) {

					current = previousState;

					shortestDistance = d;

				}

			}

			visitedStates.addAll(prevStates);

		}

		counterExampleState.add(0);

		Collections.reverse(counterExampleState);

		for (Integer state : counterExampleState) {

			System.out.println(getBinary(state, stateBitCount));

		}

		System.out.println("input vectors :");

		for (int i = 0; i < counterExampleState.size() - 1; i++) {

			int s1 = counterExampleState.get(i);
			int s2 = counterExampleState.get(i + 1);

			Integer inpVec = iVectors.get(s1, s2);

			System.out.println(getBinary(inpVec, inputBitCount));

		}

	}

}
