#include <stdio.h>
#include <time.h>

using namespace std;

int getStateBitCount() {

	// return {STATE_BIT_COUNT};

}

int getInputBitCount() {

	// return {INPUT_BIT_COUNT};

}

void exploreSpace(int initial) {

	int stateBitCount = getStateBitCount();

	int inputBitCount = getInputBitCount();

	int DMASK = 1 << 31;

	// method parameters:

	const int STATE_BUF_SIZE = 1 << stateBitCount;

	const int DISCOVERED_BUF_SIZE = 1 << stateBitCount - 2;

	bool printStateList = false;

	// memory usage checks:

//	if (stateBitCount > 29)
		//throw new Exception(String.format("Memory requirements exceed 4 GB (state bits = %d)", stateBitCount));

	//if (inputBitCount > 32)
		//throw new Exception(String.format("Input vector not representable as int type (input bits = %d)", inputBitCount));

	// search variables

	int *toVisitArr = new int[1];

	toVisitArr[0] = initial;

	const int L = 0;

	const int H = -1;

	int toVisitArrOccupied = 1;

	int distance = 0;

	int in = 0; // input vector

	int statesDiscovered = 0;

	int statesVisited = -1; // to offset the two visits to `initial`

	bool counter_example_found = false;

	int bufSelector = 0;

	int state = initial;

	int assumptions;

	int assertions;

	// state LUT and discovery arrays

	int *parentState = new int[STATE_BUF_SIZE];

	int *inputVector = new int[STATE_BUF_SIZE];

	int **buf = new int*[2];

	for (int k = 0; k < 2; k++)
		buf[k] = new int[DISCOVERED_BUF_SIZE];

	for (int k = 0; k < STATE_BUF_SIZE; k++) {
		parentState[k] = 0;
		inputVector[k] = 0;
	}

	// net declarations

	// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);

	// int {NON_STATE_BIT};

	printf("Starting search ...\n");

	timespec startTime, endTime;

	clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &startTime);

	search_loop: while (toVisitArrOccupied > 0) {

		bufSelector = 1 - bufSelector;

		int* toVisitNextArr = buf[bufSelector];

		int toVisitNextArrOccupied = 0;

		for (int i1 = 0; i1 < toVisitArrOccupied && !counter_example_found; i1++) {

			state = toVisitArr[i1];

			statesVisited++;

			// {STATE_BIT} = -(state >> {STATE_BIT_INDEX} & 1);

			int inputPermutes = 1 << (inputBitCount);

			for (in = 0; in < inputPermutes; in++) {

				// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);

				// {COMB_ASSIGN}

				int nxState = 0;

				// nxState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});

				// check assumptions

				assumptions = H; // intersection of assumptions

				assertions = H; // intersection of assertions

				// assumptions &= {ASSUMPTION} | (distance < {MAXDELAY} ? H : L);

				// assertions &= {ASSERTION} | (distance < {MAXDELAY} ? H : L);

				if (assumptions == H) {

					// check assertions

					if (assertions == L) {

						counter_example_found = true;

						break;

					}

					// Note: we check if state is discovered only if all
					// assumptions hold. This avoids accessing
					// `parentState` when it's not really needed.

					bool undiscovered = (parentState[nxState] & DMASK) == 0;

					if (undiscovered) {

						statesDiscovered++;

						toVisitNextArr[toVisitNextArrOccupied] = nxState;

						toVisitNextArrOccupied++;

						parentState[nxState] = state | DMASK;

						inputVector[nxState] = in;

					}

				}

			}

		}

		toVisitArr = toVisitNextArr;

		toVisitArrOccupied = toVisitNextArrOccupied;

		distance++;

	}

	clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &endTime);

 	double seconds = (endTime.tv_sec - startTime.tv_sec);

 	double nanoSeconds = (endTime.tv_nsec - startTime.tv_nsec);

	double searchTime = seconds + nanoSeconds / 1e9;

	printf("Completed search in %f seconds\n", searchTime);

	printf("State bits                     : %d\n", getStateBitCount());

	printf("Input bits                     : %d\n", getInputBitCount());

	//printf("State LUT                      : %s\n", getByteSize(8 * ((long)STATE_BUF_SIZE)));

	//printf("State discovery buffer         : %s\n", getByteSize(8 * ((long)DISCOVERED_BUF_SIZE)));

	printf("States visited                 : %d\n", statesVisited);

	printf("States discovered              : %d\n", statesDiscovered);

	if (counter_example_found) {

		 printf("Counter-example found in %d cycles\n", distance);

		 //int currentState = state; // violation state

		 //int transitions = distance;

		 //Stack<Integer> rList = new Stack<Integer>();

		 //rList.push(in);

		 //while (transitions > 0) {

			// if (printStateList)
			//	 printf("currentState = " + getBinary(currentState, stateBitCount)
			//		 + ", reached from parent using input vector "
			//		 + getBinary(inputVector[currentState], inputBitCount));

			// rList.add(inputVector[currentState]);

			// currentState = parentState[currentState] & ~DMASK;

			// transitions--;
		 //}

		 //int[] result = new int[distance + 1];

		 //for (int j = 0; j < distance + 1; j++)
			// result[j] = rList.pop();

		 //return result;

	 } else {

		 printf("Assertion proven, no counter-examples were found.");

		 //return null;

	}

}

int main()
{
	exploreSpace(0);

	return 0;
}
