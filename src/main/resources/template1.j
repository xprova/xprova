public void exploreSpace() throws Exception {

	// int {STATE_BIT} = 0;

	// int {NON_STATE_BIT};

	int[] vals = { 0, -1 };

	Graph<String> stateGraph = new Graph<String>();

	HashSet<Integer> toVisit = new HashSet<Integer>();

	HashSet<Integer> visited = new HashSet<Integer>();

	toVisit.add(0);

	while (!toVisit.isEmpty()) {

		HashSet<Integer> toVisitNext = new HashSet<Integer>();

		for (Integer state : toVisit) {

			// {STATE_BIT} = (state >> {STATE_BIT_INDEX}) & 1;

			String s1 = String.format("%5s", Integer.toBinaryString(state)).replace(' ', '0');

			stateGraph.addVertex(s1);

			// for (int {INPUT_BIT} : vals) {

				// {COMB_ASSIGN}

				// {STATE_ASSIGN} {PREFIX1=int next_}
				
				int next_state = 0;
				
				// next_state += (next_{STATE_BIT} & 1) << {STATE_BIT_INDEX};
				
				toVisitNext.add(next_state);

				String s2 = String.format("%5s", Integer.toBinaryString(next_state)).replace(' ', '0');

				System.out.printf("discovered %s -> %s\n", s1, s2);

				stateGraph.addVertex(s2);

				stateGraph.addConnection(s1, s2);

			// } // closing bracket for {INPUT_BIT}

		}

		visited.addAll(toVisit);

		toVisitNext.removeAll(visited);

		toVisit = toVisitNext;

	}

	GraphDotPrinter.printGraph("output/state.dot", stateGraph, new GraphDotFormatter<String>(),
			stateGraph.getVertices());

}