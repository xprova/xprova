package net.xprova.xprova;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CodeSimulator {

	public static final int L = 0;
	public static final int H = -1;

	public static void main(String args[]) throws Exception {

		// usage:
		// codesimulator [--print] [--vcd <file>] [--txt <file>] [--gtkwave]

		CodeSimulator sim1 = new CodeSimulator();

		int initial = sim1.getResetState();

		boolean runGtkwave = false, printCounter = false;

		File vcdFile = null, txtFile = null;

		for (int i = 0; i < args.length; i++) {

			String a = args[i];

			if ("--print".equals(a))
				printCounter = true;

			if ("--vcd".equals(a))
				vcdFile = new File(args[i + 1]);

			if ("--txt".equals(a))
				txtFile = new File(args[i + 1]);

			if ("--gtkwave".equals(a))
				runGtkwave = true;

		}

		if (runGtkwave && vcdFile == null) {

			File tempDir = new File(System.getProperty("java.io.tmpdir"));

			vcdFile = new File(tempDir, "counter-example.vcd");
		}

		int[] counterExample = sim1.exploreSpace(initial);

		if (counterExample != null) {

			sim1.simulate(initial, counterExample, vcdFile, txtFile, printCounter, runGtkwave);

			// 100 is a special return code for finding a counter-example but
			// terminating successfully

			System.exit(100);

		}

	}

	public int getResetState() {

		// return {RESET_STATE};
		return 0x0; // {EXPANDED}

	}

	public int[] exploreSpace(int initial) throws Exception {

		// method parameters:

		//@formatter:off
		// final int STATE_BUF_SIZE = 1 << {STATE_BIT_COUNT};
		final int STATE_BUF_SIZE = 1 << 23; // {EXPANDED}
		//@formatter:on

		final int DISCOVERED_BUF_SIZE = 100000;

		boolean printStateList = false;

		final int UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 23; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 9; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		int n_u1_reg1_q_0__51 = -(initial >> 0 & 1); // {EXPANDED}
		int n_u1_reg1_q_1__163 = -(initial >> 1 & 1); // {EXPANDED}
		int n_u1_reg1_q_2__153 = -(initial >> 2 & 1); // {EXPANDED}
		int n_u1_reg1_q_3__120 = -(initial >> 3 & 1); // {EXPANDED}
		int n_u1_reg1_q_4__173 = -(initial >> 4 & 1); // {EXPANDED}
		int n_u1_reg1_q_5__186 = -(initial >> 5 & 1); // {EXPANDED}
		int n_u1_reg1_q_6__105 = -(initial >> 6 & 1); // {EXPANDED}
		int n_u1_reg1_q_7__177 = -(initial >> 7 & 1); // {EXPANDED}
		int n_u1_stb_102 = -(initial >> 8 & 1); // {EXPANDED}
		int n_u1_sync1_f1_151 = -(initial >> 9 & 1); // {EXPANDED}
		int n_u1_sync1_f2_202 = -(initial >> 10 & 1); // {EXPANDED}
		int n_u2_reg1_q_0__33 = -(initial >> 11 & 1); // {EXPANDED}
		int n_u2_reg1_q_1__195 = -(initial >> 12 & 1); // {EXPANDED}
		int n_u2_reg1_q_2__167 = -(initial >> 13 & 1); // {EXPANDED}
		int n_u2_reg1_q_3__132 = -(initial >> 14 & 1); // {EXPANDED}
		int n_u2_reg1_q_4__136 = -(initial >> 15 & 1); // {EXPANDED}
		int n_u2_reg1_q_5__126 = -(initial >> 16 & 1); // {EXPANDED}
		int n_u2_reg1_q_6__34 = -(initial >> 17 & 1); // {EXPANDED}
		int n_u2_reg1_q_7__166 = -(initial >> 18 & 1); // {EXPANDED}
		int n_u2_reg4_q_155 = -(initial >> 19 & 1); // {EXPANDED}
		int n_u2_reg5_q_9 = -(initial >> 20 & 1); // {EXPANDED}
		int n_u2_sync1_f1_66 = -(initial >> 21 & 1); // {EXPANDED}
		int n_u2_sync1_f2_75 = -(initial >> 22 & 1); // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int n_u1_ack_199; // {EXPANDED}
		int n_u1_ack_selected_169; // {EXPANDED}
		int n_u1_ack_sync_115; // {EXPANDED}
		int n_u1_busy_185; // {EXPANDED}
		int n_u1_clk_25; // {EXPANDED}
		int n_u1_data_0__103; // {EXPANDED}
		int n_u1_data_1__20; // {EXPANDED}
		int n_u1_data_2__43; // {EXPANDED}
		int n_u1_data_3__94; // {EXPANDED}
		int n_u1_data_4__56; // {EXPANDED}
		int n_u1_data_5__15; // {EXPANDED}
		int n_u1_data_6__58; // {EXPANDED}
		int n_u1_data_7__8; // {EXPANDED}
		int n_u1_data_in_0__27; // {EXPANDED}
		int n_u1_data_in_1__134; // {EXPANDED}
		int n_u1_data_in_2__124; // {EXPANDED}
		int n_u1_data_in_3__150; // {EXPANDED}
		int n_u1_data_in_4__70; // {EXPANDED}
		int n_u1_data_in_5__117; // {EXPANDED}
		int n_u1_data_in_6__122; // {EXPANDED}
		int n_u1_data_in_7__17; // {EXPANDED}
		int n_u1_reg1_clk_174; // {EXPANDED}
		int n_u1_reg1_d_0__121; // {EXPANDED}
		int n_u1_reg1_d_1__97; // {EXPANDED}
		int n_u1_reg1_d_2__13; // {EXPANDED}
		int n_u1_reg1_d_3__171; // {EXPANDED}
		int n_u1_reg1_d_4__2; // {EXPANDED}
		int n_u1_reg1_d_5__165; // {EXPANDED}
		int n_u1_reg1_d_6__138; // {EXPANDED}
		int n_u1_reg1_d_7__3; // {EXPANDED}
		int n_u1_reg1_ena_196; // {EXPANDED}
		int n_u1_reg1_rst_187; // {EXPANDED}
		int n_u1_rst_14; // {EXPANDED}
		int n_u1_send_45; // {EXPANDED}
		int n_u1_sync1_clk_19; // {EXPANDED}
		int n_u1_sync1_rst_191; // {EXPANDED}
		int n_u1_sync1_sync_x_162; // {EXPANDED}
		int n_u1_sync1_x_81; // {EXPANDED}
		int n_u2_ack_118; // {EXPANDED}
		int n_u2_clk_111; // {EXPANDED}
		int n_u2_data_0__29; // {EXPANDED}
		int n_u2_data_1__178; // {EXPANDED}
		int n_u2_data_2__101; // {EXPANDED}
		int n_u2_data_3__7; // {EXPANDED}
		int n_u2_data_4__159; // {EXPANDED}
		int n_u2_data_5__175; // {EXPANDED}
		int n_u2_data_6__48; // {EXPANDED}
		int n_u2_data_7__68; // {EXPANDED}
		int n_u2_data_out_0__125; // {EXPANDED}
		int n_u2_data_out_1__147; // {EXPANDED}
		int n_u2_data_out_2__0; // {EXPANDED}
		int n_u2_data_out_3__84; // {EXPANDED}
		int n_u2_data_out_4__110; // {EXPANDED}
		int n_u2_data_out_5__23; // {EXPANDED}
		int n_u2_data_out_6__140; // {EXPANDED}
		int n_u2_data_out_7__40; // {EXPANDED}
		int n_u2_data_rec_0__96; // {EXPANDED}
		int n_u2_data_rec_1__31; // {EXPANDED}
		int n_u2_data_rec_2__6; // {EXPANDED}
		int n_u2_data_rec_3__80; // {EXPANDED}
		int n_u2_data_rec_4__128; // {EXPANDED}
		int n_u2_data_rec_5__28; // {EXPANDED}
		int n_u2_data_rec_6__148; // {EXPANDED}
		int n_u2_data_rec_7__188; // {EXPANDED}
		int n_u2_newdata_161; // {EXPANDED}
		int n_u2_reg1_clk_109; // {EXPANDED}
		int n_u2_reg1_d_0__47; // {EXPANDED}
		int n_u2_reg1_d_1__4; // {EXPANDED}
		int n_u2_reg1_d_2__78; // {EXPANDED}
		int n_u2_reg1_d_3__183; // {EXPANDED}
		int n_u2_reg1_d_4__30; // {EXPANDED}
		int n_u2_reg1_d_5__46; // {EXPANDED}
		int n_u2_reg1_d_6__170; // {EXPANDED}
		int n_u2_reg1_d_7__38; // {EXPANDED}
		int n_u2_reg1_ena_157; // {EXPANDED}
		int n_u2_reg1_rst_22; // {EXPANDED}
		int n_u2_reg3_clk_130; // {EXPANDED}
		int n_u2_reg3_d_156; // {EXPANDED}
		int n_u2_reg3_q_63; // {EXPANDED}
		int n_u2_reg3_rst_12; // {EXPANDED}
		int n_u2_reg4_clk_152; // {EXPANDED}
		int n_u2_reg4_d_106; // {EXPANDED}
		int n_u2_reg4_rst_91; // {EXPANDED}
		int n_u2_reg5_clk_86; // {EXPANDED}
		int n_u2_reg5_d_42; // {EXPANDED}
		int n_u2_reg5_rst_74; // {EXPANDED}
		int n_u2_rst_145; // {EXPANDED}
		int n_u2_stb_137; // {EXPANDED}
		int n_u2_stb_latched_135; // {EXPANDED}
		int n_u2_stb_selected_69; // {EXPANDED}
		int n_u2_stb_sync_85; // {EXPANDED}
		int n_u2_sync1_clk_21; // {EXPANDED}
		int n_u2_sync1_rst_87; // {EXPANDED}
		int n_u2_sync1_sync_x_180; // {EXPANDED}
		int n_u2_sync1_x_149; // {EXPANDED}
		int n_u2_valid_71; // {EXPANDED}
		int n_000__5; // {EXPANDED}
		int n_001__53; // {EXPANDED}
		int n_002__44; // {EXPANDED}
		int n_003__11; // {EXPANDED}
		int n_004__203; // {EXPANDED}
		int n_005__60; // {EXPANDED}
		int n_006__172; // {EXPANDED}
		int n_007__57; // {EXPANDED}
		int n_008__176; // {EXPANDED}
		int n_009__141; // {EXPANDED}
		int n_010__61; // {EXPANDED}
		int n_011__59; // {EXPANDED}
		int n_012__112; // {EXPANDED}
		int n_013__184; // {EXPANDED}
		int n_014__76; // {EXPANDED}
		int n_015__26; // {EXPANDED}
		int n_016__104; // {EXPANDED}
		int n_017__189; // {EXPANDED}
		int n_018__98; // {EXPANDED}
		int n_019__139; // {EXPANDED}
		int n_020__116; // {EXPANDED}
		int n_021__73; // {EXPANDED}
		int n_022__182; // {EXPANDED}
		int n_023__67; // {EXPANDED}
		int n_024__108; // {EXPANDED}
		int n_025__93; // {EXPANDED}
		int n_026__62; // {EXPANDED}
		int n_027__131; // {EXPANDED}
		int n_028__194; // {EXPANDED}
		int n_029__200; // {EXPANDED}
		int n_030__197; // {EXPANDED}
		int n_031__168; // {EXPANDED}
		int n_032__18; // {EXPANDED}
		int n_033__181; // {EXPANDED}
		int n_034__65; // {EXPANDED}
		int n_035__146; // {EXPANDED}
		int n_036__129; // {EXPANDED}
		int n_037__190; // {EXPANDED}
		int n_038__113; // {EXPANDED}
		int n_039__24; // {EXPANDED}
		int n_040__144; // {EXPANDED}
		int n_041__90; // {EXPANDED}
		int n_042__100; // {EXPANDED}
		int n_043__50; // {EXPANDED}
		int n_044__142; // {EXPANDED}
		int n_045__77; // {EXPANDED}
		int n_046__164; // {EXPANDED}
		int n_047__55; // {EXPANDED}
		int n_048__198; // {EXPANDED}
		int n_049__10; // {EXPANDED}
		int n_050__133; // {EXPANDED}
		int n_051__204; // {EXPANDED}
		int n_052__158; // {EXPANDED}
		int n_053__37; // {EXPANDED}
		int nack_35; // {EXPANDED}
		int nbusy_107; // {EXPANDED}
		int ndata_0__114; // {EXPANDED}
		int ndata_1__179; // {EXPANDED}
		int ndata_2__143; // {EXPANDED}
		int ndata_3__54; // {EXPANDED}
		int ndata_4__32; // {EXPANDED}
		int ndata_5__1; // {EXPANDED}
		int ndata_6__82; // {EXPANDED}
		int ndata_7__123; // {EXPANDED}
		int ndata_out_0__72; // {EXPANDED}
		int ndata_out_1__89; // {EXPANDED}
		int ndata_out_2__193; // {EXPANDED}
		int ndata_out_3__36; // {EXPANDED}
		int ndata_out_4__192; // {EXPANDED}
		int ndata_out_5__39; // {EXPANDED}
		int ndata_out_6__52; // {EXPANDED}
		int ndata_out_7__201; // {EXPANDED}
		int nstb_160; // {EXPANDED}
		int nvalid_92; // {EXPANDED}
		//@formatter:on

		int[] toVisitArr = new int[1];
		toVisitArr[0] = initial;
		int toVisitArrOccupied = 1;

		int distance = 1;

		int in; // input vector

		int[] parentState = new int[STATE_BUF_SIZE];
		int[] inputVector = new int[STATE_BUF_SIZE];

		Arrays.fill(parentState, UNDISCOVERED);

		// note: a state having the value UNDISCOVERED
		// will pose an issue to the algorithm below.
		// This possibility is here (unwisely?) ignored

		// note: an actual state of Integer.

		parentState[initial] = -1; // marked parentState as visited

		long statesDiscovered = 0;

		int violationState = UNDISCOVERED;

		int[][] buf = new int[2][DISCOVERED_BUF_SIZE];

		int bufSelector = 0;

		int state = initial;

		int all_assumptions;
		int all_assertions;

		System.out.println("Starting search ...");

		long startTime = System.nanoTime();

		loop1: while (toVisitArrOccupied > 0) {

			bufSelector = 1 - bufSelector;

			int[] toVisitNextArr = buf[bufSelector];

			int toVisitNextArrOccupied = 0;

			for (int i1 = 0; i1 < toVisitArrOccupied; i1++) {

				state = toVisitArr[i1];

				statesDiscovered += 1;

				//@formatter:off
				// {STATE_BIT} = -(state >> {STATE_BIT_INDEX} & 1);
				n_u1_reg1_q_0__51 = -(state >> 0 & 1); // {EXPANDED}
				n_u1_reg1_q_1__163 = -(state >> 1 & 1); // {EXPANDED}
				n_u1_reg1_q_2__153 = -(state >> 2 & 1); // {EXPANDED}
				n_u1_reg1_q_3__120 = -(state >> 3 & 1); // {EXPANDED}
				n_u1_reg1_q_4__173 = -(state >> 4 & 1); // {EXPANDED}
				n_u1_reg1_q_5__186 = -(state >> 5 & 1); // {EXPANDED}
				n_u1_reg1_q_6__105 = -(state >> 6 & 1); // {EXPANDED}
				n_u1_reg1_q_7__177 = -(state >> 7 & 1); // {EXPANDED}
				n_u1_stb_102 = -(state >> 8 & 1); // {EXPANDED}
				n_u1_sync1_f1_151 = -(state >> 9 & 1); // {EXPANDED}
				n_u1_sync1_f2_202 = -(state >> 10 & 1); // {EXPANDED}
				n_u2_reg1_q_0__33 = -(state >> 11 & 1); // {EXPANDED}
				n_u2_reg1_q_1__195 = -(state >> 12 & 1); // {EXPANDED}
				n_u2_reg1_q_2__167 = -(state >> 13 & 1); // {EXPANDED}
				n_u2_reg1_q_3__132 = -(state >> 14 & 1); // {EXPANDED}
				n_u2_reg1_q_4__136 = -(state >> 15 & 1); // {EXPANDED}
				n_u2_reg1_q_5__126 = -(state >> 16 & 1); // {EXPANDED}
				n_u2_reg1_q_6__34 = -(state >> 17 & 1); // {EXPANDED}
				n_u2_reg1_q_7__166 = -(state >> 18 & 1); // {EXPANDED}
				n_u2_reg4_q_155 = -(state >> 19 & 1); // {EXPANDED}
				n_u2_reg5_q_9 = -(state >> 20 & 1); // {EXPANDED}
				n_u2_sync1_f1_66 = -(state >> 21 & 1); // {EXPANDED}
				n_u2_sync1_f2_75 = -(state >> 22 & 1); // {EXPANDED}
				//@formatter:on

				int inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					int ndata_in_0__88 = -(in >> 0 & 1); // {EXPANDED}
					int ndata_in_1__154 = -(in >> 1 & 1); // {EXPANDED}
					int ndata_in_2__49 = -(in >> 2 & 1); // {EXPANDED}
					int ndata_in_3__99 = -(in >> 3 & 1); // {EXPANDED}
					int ndata_in_4__64 = -(in >> 4 & 1); // {EXPANDED}
					int ndata_in_5__83 = -(in >> 5 & 1); // {EXPANDED}
					int ndata_in_6__127 = -(in >> 6 & 1); // {EXPANDED}
					int ndata_in_7__119 = -(in >> 7 & 1); // {EXPANDED}
					int nsend_79 = -(in >> 8 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					n_053__37 = 0; // {EXPANDED}
					n_u2_data_out_2__0 = n_u2_reg1_q_2__167; // {EXPANDED}
					ndata_5__1 = n_u1_reg1_q_5__186; // {EXPANDED}
					n_u1_reg1_d_4__2 = ndata_in_4__64; // {EXPANDED}
					n_u1_reg1_d_7__3 = ndata_in_7__119; // {EXPANDED}
					n_u2_data_out_4__110 = n_u2_reg1_q_4__136; // {EXPANDED}
					n_038__113 = ~nsend_79; // {EXPANDED}
					n_u2_reg1_d_1__4 = n_u1_reg1_q_1__163; // {EXPANDED}
					n_u2_data_rec_2__6 = n_u2_reg1_q_2__167; // {EXPANDED}
					n_u2_data_3__7 = n_u1_reg1_q_3__120; // {EXPANDED}
					ndata_0__114 = n_u1_reg1_q_0__51; // {EXPANDED}
					n_u1_ack_sync_115 = n_u1_sync1_f2_202; // {EXPANDED}
					n_u1_data_in_5__117 = ndata_in_5__83; // {EXPANDED}
					n_u2_ack_118 = n_u2_reg5_q_9; // {EXPANDED}
					n_u1_data_7__8 = n_u1_reg1_q_7__177; // {EXPANDED}
					n_u1_reg1_d_0__121 = ndata_in_0__88; // {EXPANDED}
					n_049__10 = ~(n_u1_reg1_q_5__186 & n_038__113); // {EXPANDED}
					n_u1_data_in_6__122 = ndata_in_6__127; // {EXPANDED}
					ndata_7__123 = n_u1_reg1_q_7__177; // {EXPANDED}
					n_u1_data_in_2__124 = ndata_in_2__49; // {EXPANDED}
					n_u2_data_out_0__125 = n_u2_reg1_q_0__33; // {EXPANDED}
					n_u1_reg1_d_2__13 = ndata_in_2__49; // {EXPANDED}
					n_u2_data_rec_4__128 = n_u2_reg1_q_4__136; // {EXPANDED}
					n_u1_data_5__15 = n_u1_reg1_q_5__186; // {EXPANDED}
					n_u1_data_in_7__17 = ndata_in_7__119; // {EXPANDED}
					n_u1_data_1__20 = n_u1_reg1_q_1__163; // {EXPANDED}
					n_050__133 = ~(nsend_79 & ndata_in_5__83); // {EXPANDED}
					n_u1_data_in_1__134 = ndata_in_1__154; // {EXPANDED}
					n_u2_stb_latched_135 = n_u2_reg5_q_9; // {EXPANDED}
					n_u2_data_out_5__23 = n_u2_reg1_q_5__126; // {EXPANDED}
					n_039__24 = ~(n_u1_reg1_q_0__51 & n_038__113); // {EXPANDED}
					n_u2_stb_137 = n_u1_stb_102; // {EXPANDED}
					n_u1_reg1_d_6__138 = ndata_in_6__127; // {EXPANDED}
					n_u1_data_in_0__27 = ndata_in_0__88; // {EXPANDED}
					n_u2_data_out_6__140 = n_u2_reg1_q_6__34; // {EXPANDED}
					n_u2_data_rec_5__28 = n_u2_reg1_q_5__126; // {EXPANDED}
					n_u2_reg1_d_4__30 = n_u1_reg1_q_4__173; // {EXPANDED}
					n_u2_data_0__29 = n_u1_reg1_q_0__51; // {EXPANDED}
					n_u2_data_rec_1__31 = n_u2_reg1_q_1__195; // {EXPANDED}
					n_044__142 = ~(nsend_79 & ndata_in_2__49); // {EXPANDED}
					ndata_2__143 = n_u1_reg1_q_2__153; // {EXPANDED}
					ndata_4__32 = n_u1_reg1_q_4__173; // {EXPANDED}
					n_040__144 = ~(nsend_79 & ndata_in_0__88); // {EXPANDED}
					nack_35 = n_u2_reg5_q_9; // {EXPANDED}
					n_u2_data_out_1__147 = n_u2_reg1_q_1__195; // {EXPANDED}
					ndata_out_3__36 = n_u2_reg1_q_3__132; // {EXPANDED}
					n_u2_data_rec_6__148 = n_u2_reg1_q_6__34; // {EXPANDED}
					n_u2_sync1_x_149 = n_u1_stb_102; // {EXPANDED}
					n_u1_data_in_3__150 = ndata_in_3__99; // {EXPANDED}
					ndata_out_5__39 = n_u2_reg1_q_5__126; // {EXPANDED}
					n_u2_reg1_d_7__38 = n_u1_reg1_q_7__177; // {EXPANDED}
					n_u2_data_out_7__40 = n_u2_reg1_q_7__166; // {EXPANDED}
					n_u1_data_2__43 = n_u1_reg1_q_2__153; // {EXPANDED}
					n_u2_reg5_d_42 = n_u2_sync1_f2_75; // {EXPANDED}
					n_002__44 = ~(nsend_79 & ndata_in_7__119); // {EXPANDED}
					n_u2_reg3_d_156 = n_u2_sync1_f2_75; // {EXPANDED}
					n_u2_data_4__159 = n_u1_reg1_q_4__173; // {EXPANDED}
					n_052__158 = ~(nsend_79 & ndata_in_6__127); // {EXPANDED}
					nstb_160 = n_u1_stb_102; // {EXPANDED}
					n_u1_send_45 = nsend_79; // {EXPANDED}
					n_u2_reg1_d_5__46 = n_u1_reg1_q_5__186; // {EXPANDED}
					n_u2_reg1_d_0__47 = n_u1_reg1_q_0__51; // {EXPANDED}
					n_u1_sync1_sync_x_162 = n_u1_sync1_f2_202; // {EXPANDED}
					n_u2_data_6__48 = n_u1_reg1_q_6__105; // {EXPANDED}
					n_043__50 = ~(n_u1_reg1_q_2__153 & n_038__113); // {EXPANDED}
					ndata_out_6__52 = n_u2_reg1_q_6__34; // {EXPANDED}
					n_046__164 = ~(nsend_79 & ndata_in_3__99); // {EXPANDED}
					n_001__53 = ~(n_u1_reg1_q_7__177 & n_038__113); // {EXPANDED}
					n_u1_reg1_d_5__165 = ndata_in_5__83; // {EXPANDED}
					ndata_3__54 = n_u1_reg1_q_3__120; // {EXPANDED}
					n_047__55 = ~(n_038__113 & n_u1_reg1_q_4__173); // {EXPANDED}
					n_u1_data_4__56 = n_u1_reg1_q_4__173; // {EXPANDED}
					n_u1_data_6__58 = n_u1_reg1_q_6__105; // {EXPANDED}
					n_u1_ack_selected_169 = n_u1_sync1_f2_202; // {EXPANDED}
					n_u2_reg1_d_6__170 = n_u1_reg1_q_6__105; // {EXPANDED}
					n_u2_reg3_q_63 = n_u2_reg5_q_9; // {EXPANDED}
					n_u1_reg1_d_3__171 = ndata_in_3__99; // {EXPANDED}
					n_u2_data_7__68 = n_u1_reg1_q_7__177; // {EXPANDED}
					n_u2_stb_selected_69 = n_u2_sync1_f2_75; // {EXPANDED}
					n_u2_data_5__175 = n_u1_reg1_q_5__186; // {EXPANDED}
					n_u1_data_in_4__70 = ndata_in_4__64; // {EXPANDED}
					n_u2_valid_71 = n_u2_reg4_q_155; // {EXPANDED}
					ndata_out_0__72 = n_u2_reg1_q_0__33; // {EXPANDED}
					n_u2_data_1__178 = n_u1_reg1_q_1__163; // {EXPANDED}
					ndata_1__179 = n_u1_reg1_q_1__163; // {EXPANDED}
					n_u2_sync1_sync_x_180 = n_u2_sync1_f2_75; // {EXPANDED}
					n_045__77 = ~(n_u1_reg1_q_3__120 & n_038__113); // {EXPANDED}
					n_u2_reg1_d_3__183 = n_u1_reg1_q_3__120; // {EXPANDED}
					n_u2_reg1_d_2__78 = n_u1_reg1_q_2__153; // {EXPANDED}
					n_u1_sync1_x_81 = n_u2_reg5_q_9; // {EXPANDED}
					n_u2_data_rec_3__80 = n_u2_reg1_q_3__132; // {EXPANDED}
					ndata_6__82 = n_u1_reg1_q_6__105; // {EXPANDED}
					n_u2_data_out_3__84 = n_u2_reg1_q_3__132; // {EXPANDED}
					n_u2_data_rec_7__188 = n_u2_reg1_q_7__166; // {EXPANDED}
					n_037__190 = ~(n_u1_stb_102 | nsend_79); // {EXPANDED}
					n_u2_stb_sync_85 = n_u2_sync1_f2_75; // {EXPANDED}
					ndata_out_1__89 = n_u2_reg1_q_1__195; // {EXPANDED}
					n_041__90 = ~(n_038__113 & n_u1_reg1_q_1__163); // {EXPANDED}
					nvalid_92 = n_u2_reg4_q_155; // {EXPANDED}
					n_u1_data_3__94 = n_u1_reg1_q_3__120; // {EXPANDED}
					n_u2_data_rec_0__96 = n_u2_reg1_q_0__33; // {EXPANDED}
					ndata_out_4__192 = n_u2_reg1_q_4__136; // {EXPANDED}
					ndata_out_2__193 = n_u2_reg1_q_2__167; // {EXPANDED}
					n_u1_reg1_d_1__97 = ndata_in_1__154; // {EXPANDED}
					n_018__98 = ~n_u2_sync1_f2_75; // {EXPANDED}
					n_042__100 = ~(ndata_in_1__154 & nsend_79); // {EXPANDED}
					n_u2_data_2__101 = n_u1_reg1_q_2__153; // {EXPANDED}
					n_u1_reg1_ena_196 = nsend_79; // {EXPANDED}
					n_048__198 = ~(nsend_79 & ndata_in_4__64); // {EXPANDED}
					n_u1_data_0__103 = n_u1_reg1_q_0__51; // {EXPANDED}
					n_u1_ack_199 = n_u2_reg5_q_9; // {EXPANDED}
					ndata_out_7__201 = n_u2_reg1_q_7__166; // {EXPANDED}
					nbusy_107 = n_u1_stb_102 | n_u1_sync1_f2_202; // {EXPANDED}
					n_051__204 = ~(n_u1_reg1_q_6__105 & n_038__113); // {EXPANDED}
					n_011__59 = ~(n_040__144 & n_039__24); // {EXPANDED}
					n_019__139 = ~(n_002__44 & n_001__53); // {EXPANDED}
					n_017__189 = ~(n_052__158 & n_051__204); // {EXPANDED}
					n_010__61 = ~(n_037__190 | n_u1_sync1_f2_202); // {EXPANDED}
					n_015__26 = ~(n_048__198 & n_047__55); // {EXPANDED}
					n_u2_newdata_161 = ~(n_018__98 | n_u2_reg5_q_9); // {EXPANDED}
					n_012__112 = ~(n_041__90 & n_042__100); // {EXPANDED}
					n_014__76 = ~(n_045__77 & n_046__164); // {EXPANDED}
					n_016__104 = ~(n_050__133 & n_049__10); // {EXPANDED}
					n_020__116 = n_018__98 | n_u2_reg5_q_9; // {EXPANDED}
					n_013__184 = ~(n_043__50 & n_044__142); // {EXPANDED}
					n_u1_busy_185 = nbusy_107; // {EXPANDED}
					n_026__62 = ~(n_u2_newdata_161 & n_u1_reg1_q_2__153); // {EXPANDED}
					n_025__93 = ~(n_020__116 & n_u2_reg1_q_2__167); // {EXPANDED}
					n_035__146 = ~(n_020__116 & n_u2_reg1_q_7__166); // {EXPANDED}
					n_034__65 = ~(n_u1_reg1_q_6__105 & n_u2_newdata_161); // {EXPANDED}
					n_023__67 = ~(n_020__116 & n_u2_reg1_q_1__195); // {EXPANDED}
					n_028__194 = ~(n_u2_newdata_161 & n_u1_reg1_q_3__120); // {EXPANDED}
					n_u2_reg1_ena_157 = n_u2_newdata_161; // {EXPANDED}
					n_021__73 = ~(n_u2_reg1_q_0__33 & n_020__116); // {EXPANDED}
					n_030__197 = ~(n_u2_newdata_161 & n_u1_reg1_q_4__173); // {EXPANDED}
					n_036__129 = ~(n_u1_reg1_q_7__177 & n_u2_newdata_161); // {EXPANDED}
					n_032__18 = ~(n_u2_newdata_161 & n_u1_reg1_q_5__186); // {EXPANDED}
					n_027__131 = ~(n_u2_reg1_q_3__132 & n_020__116); // {EXPANDED}
					n_033__181 = ~(n_u2_reg1_q_6__34 & n_020__116); // {EXPANDED}
					n_022__182 = ~(n_u1_reg1_q_0__51 & n_u2_newdata_161); // {EXPANDED}
					n_029__200 = ~(n_020__116 & n_u2_reg1_q_4__136); // {EXPANDED}
					n_u2_reg4_d_106 = n_u2_newdata_161; // {EXPANDED}
					n_024__108 = ~(n_u2_newdata_161 & n_u1_reg1_q_1__163); // {EXPANDED}
					n_031__168 = ~(n_u2_reg1_q_5__126 & n_020__116); // {EXPANDED}
					n_000__5 = ~(n_021__73 & n_022__182); // {EXPANDED}
					n_003__11 = ~(n_023__67 & n_024__108); // {EXPANDED}
					n_007__57 = ~(n_032__18 & n_031__168); // {EXPANDED}
					n_005__60 = ~(n_028__194 & n_027__131); // {EXPANDED}
					n_009__141 = ~(n_035__146 & n_036__129); // {EXPANDED}
					n_004__203 = ~(n_026__62 & n_025__93); // {EXPANDED}
					n_008__176 = ~(n_034__65 & n_033__181); // {EXPANDED}
					n_006__172 = ~(n_029__200 & n_030__197); // {EXPANDED}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					nxState |= n_011__59 & (1 << 0); // {EXPANDED}
					nxState |= n_012__112 & (1 << 1); // {EXPANDED}
					nxState |= n_013__184 & (1 << 2); // {EXPANDED}
					nxState |= n_014__76 & (1 << 3); // {EXPANDED}
					nxState |= n_015__26 & (1 << 4); // {EXPANDED}
					nxState |= n_016__104 & (1 << 5); // {EXPANDED}
					nxState |= n_017__189 & (1 << 6); // {EXPANDED}
					nxState |= n_019__139 & (1 << 7); // {EXPANDED}
					nxState |= n_010__61 & (1 << 8); // {EXPANDED}
					nxState |= n_u2_reg5_q_9 & (1 << 9); // {EXPANDED}
					nxState |= n_u1_sync1_f1_151 & (1 << 10); // {EXPANDED}
					nxState |= n_000__5 & (1 << 11); // {EXPANDED}
					nxState |= n_003__11 & (1 << 12); // {EXPANDED}
					nxState |= n_004__203 & (1 << 13); // {EXPANDED}
					nxState |= n_005__60 & (1 << 14); // {EXPANDED}
					nxState |= n_006__172 & (1 << 15); // {EXPANDED}
					nxState |= n_007__57 & (1 << 16); // {EXPANDED}
					nxState |= n_008__176 & (1 << 17); // {EXPANDED}
					nxState |= n_009__141 & (1 << 18); // {EXPANDED}
					nxState |= n_u2_newdata_161 & (1 << 19); // {EXPANDED}
					nxState |= n_u2_sync1_f2_75 & (1 << 20); // {EXPANDED}
					nxState |= n_u1_stb_102 & (1 << 21); // {EXPANDED}
					nxState |= n_u2_sync1_f1_66 & (1 << 22); // {EXPANDED}
					//@formatter:on

					if (parentState[nxState] == UNDISCOVERED) {

						toVisitNextArr[toVisitNextArrOccupied] = nxState;

						toVisitNextArrOccupied += 1;

						parentState[nxState] = state;

						inputVector[nxState] = in;

					}

					all_assumptions = -1;
					all_assertions = -1;

					// all_assumptions &= {ASSUMPTION};
					all_assumptions &= ndata_out_3__36; // {EXPANDED}
					// all_assertions &= {ASSERTION};
					all_assertions &= (~nvalid_92); // {EXPANDED}

					if (all_assumptions == -1 && all_assertions == 0) {

						violationState = state;

						break loop1;

					}

				}

			}

			toVisitArr = toVisitNextArr;

			toVisitArrOccupied = toVisitNextArrOccupied;

			distance = distance + 1;

		}

		long endTime = System.nanoTime();

		double searchTime = (endTime - startTime) / 1e9;

		System.out.printf("Completed search in %f seconds\n", searchTime);

		System.out.printf("States discovered = %d\n", statesDiscovered);

		Stack<Integer> rList = new Stack<Integer>();

		if (violationState != UNDISCOVERED) {

			System.out.printf("Counter-example found (distance = %d)!\n", distance);

			int currentState = violationState;

			while (currentState != initial) {

				if (printStateList)
					System.out.println("currentState = " + getBinary(currentState, stateBitCount)
							+ ", reached from parent using input vector "
							+ getBinary(inputVector[currentState], inputBitCount));

				rList.add(inputVector[currentState]);

				currentState = parentState[currentState];
			}

			int[] result = new int[distance];

			for (int j = 0; j < distance - 1; j++)
				result[j] = rList.pop();

			return result;

		} else {

			System.out.println("Assertion proven, no counter-examples were found.");

			return null;

		}

	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		//@formatter:off
		// result.add("{STATE_BIT_ORG}");
		result.add("\\u1.reg1.q[0]"); // {EXPANDED}
		result.add("\\u1.reg1.q[1]"); // {EXPANDED}
		result.add("\\u1.reg1.q[2]"); // {EXPANDED}
		result.add("\\u1.reg1.q[3]"); // {EXPANDED}
		result.add("\\u1.reg1.q[4]"); // {EXPANDED}
		result.add("\\u1.reg1.q[5]"); // {EXPANDED}
		result.add("\\u1.reg1.q[6]"); // {EXPANDED}
		result.add("\\u1.reg1.q[7]"); // {EXPANDED}
		result.add("\\u1.stb"); // {EXPANDED}
		result.add("\\u1.sync1.f1"); // {EXPANDED}
		result.add("\\u1.sync1.f2"); // {EXPANDED}
		result.add("\\u2.reg1.q[0]"); // {EXPANDED}
		result.add("\\u2.reg1.q[1]"); // {EXPANDED}
		result.add("\\u2.reg1.q[2]"); // {EXPANDED}
		result.add("\\u2.reg1.q[3]"); // {EXPANDED}
		result.add("\\u2.reg1.q[4]"); // {EXPANDED}
		result.add("\\u2.reg1.q[5]"); // {EXPANDED}
		result.add("\\u2.reg1.q[6]"); // {EXPANDED}
		result.add("\\u2.reg1.q[7]"); // {EXPANDED}
		result.add("\\u2.reg4.q"); // {EXPANDED}
		result.add("\\u2.reg5.q"); // {EXPANDED}
		result.add("\\u2.sync1.f1"); // {EXPANDED}
		result.add("\\u2.sync1.f2"); // {EXPANDED}

		// result.add("{INPUT_BIT_ORG}");
		result.add("data_in[0]"); // {EXPANDED}
		result.add("data_in[1]"); // {EXPANDED}
		result.add("data_in[2]"); // {EXPANDED}
		result.add("data_in[3]"); // {EXPANDED}
		result.add("data_in[4]"); // {EXPANDED}
		result.add("data_in[5]"); // {EXPANDED}
		result.add("data_in[6]"); // {EXPANDED}
		result.add("data_in[7]"); // {EXPANDED}
		result.add("send"); // {EXPANDED}

		// result.add("{NON_STATE_BIT_ORG}");
		result.add("\\u1.ack"); // {EXPANDED}
		result.add("\\u1.ack_selected"); // {EXPANDED}
		result.add("\\u1.ack_sync"); // {EXPANDED}
		result.add("\\u1.busy"); // {EXPANDED}
		result.add("\\u1.clk"); // {EXPANDED}
		result.add("\\u1.data[0]"); // {EXPANDED}
		result.add("\\u1.data[1]"); // {EXPANDED}
		result.add("\\u1.data[2]"); // {EXPANDED}
		result.add("\\u1.data[3]"); // {EXPANDED}
		result.add("\\u1.data[4]"); // {EXPANDED}
		result.add("\\u1.data[5]"); // {EXPANDED}
		result.add("\\u1.data[6]"); // {EXPANDED}
		result.add("\\u1.data[7]"); // {EXPANDED}
		result.add("\\u1.data_in[0]"); // {EXPANDED}
		result.add("\\u1.data_in[1]"); // {EXPANDED}
		result.add("\\u1.data_in[2]"); // {EXPANDED}
		result.add("\\u1.data_in[3]"); // {EXPANDED}
		result.add("\\u1.data_in[4]"); // {EXPANDED}
		result.add("\\u1.data_in[5]"); // {EXPANDED}
		result.add("\\u1.data_in[6]"); // {EXPANDED}
		result.add("\\u1.data_in[7]"); // {EXPANDED}
		result.add("\\u1.reg1.clk"); // {EXPANDED}
		result.add("\\u1.reg1.d[0]"); // {EXPANDED}
		result.add("\\u1.reg1.d[1]"); // {EXPANDED}
		result.add("\\u1.reg1.d[2]"); // {EXPANDED}
		result.add("\\u1.reg1.d[3]"); // {EXPANDED}
		result.add("\\u1.reg1.d[4]"); // {EXPANDED}
		result.add("\\u1.reg1.d[5]"); // {EXPANDED}
		result.add("\\u1.reg1.d[6]"); // {EXPANDED}
		result.add("\\u1.reg1.d[7]"); // {EXPANDED}
		result.add("\\u1.reg1.ena"); // {EXPANDED}
		result.add("\\u1.reg1.rst"); // {EXPANDED}
		result.add("\\u1.rst"); // {EXPANDED}
		result.add("\\u1.send"); // {EXPANDED}
		result.add("\\u1.sync1.clk"); // {EXPANDED}
		result.add("\\u1.sync1.rst"); // {EXPANDED}
		result.add("\\u1.sync1.sync_x"); // {EXPANDED}
		result.add("\\u1.sync1.x"); // {EXPANDED}
		result.add("\\u2.ack"); // {EXPANDED}
		result.add("\\u2.clk"); // {EXPANDED}
		result.add("\\u2.data[0]"); // {EXPANDED}
		result.add("\\u2.data[1]"); // {EXPANDED}
		result.add("\\u2.data[2]"); // {EXPANDED}
		result.add("\\u2.data[3]"); // {EXPANDED}
		result.add("\\u2.data[4]"); // {EXPANDED}
		result.add("\\u2.data[5]"); // {EXPANDED}
		result.add("\\u2.data[6]"); // {EXPANDED}
		result.add("\\u2.data[7]"); // {EXPANDED}
		result.add("\\u2.data_out[0]"); // {EXPANDED}
		result.add("\\u2.data_out[1]"); // {EXPANDED}
		result.add("\\u2.data_out[2]"); // {EXPANDED}
		result.add("\\u2.data_out[3]"); // {EXPANDED}
		result.add("\\u2.data_out[4]"); // {EXPANDED}
		result.add("\\u2.data_out[5]"); // {EXPANDED}
		result.add("\\u2.data_out[6]"); // {EXPANDED}
		result.add("\\u2.data_out[7]"); // {EXPANDED}
		result.add("\\u2.data_rec[0]"); // {EXPANDED}
		result.add("\\u2.data_rec[1]"); // {EXPANDED}
		result.add("\\u2.data_rec[2]"); // {EXPANDED}
		result.add("\\u2.data_rec[3]"); // {EXPANDED}
		result.add("\\u2.data_rec[4]"); // {EXPANDED}
		result.add("\\u2.data_rec[5]"); // {EXPANDED}
		result.add("\\u2.data_rec[6]"); // {EXPANDED}
		result.add("\\u2.data_rec[7]"); // {EXPANDED}
		result.add("\\u2.newdata"); // {EXPANDED}
		result.add("\\u2.reg1.clk"); // {EXPANDED}
		result.add("\\u2.reg1.d[0]"); // {EXPANDED}
		result.add("\\u2.reg1.d[1]"); // {EXPANDED}
		result.add("\\u2.reg1.d[2]"); // {EXPANDED}
		result.add("\\u2.reg1.d[3]"); // {EXPANDED}
		result.add("\\u2.reg1.d[4]"); // {EXPANDED}
		result.add("\\u2.reg1.d[5]"); // {EXPANDED}
		result.add("\\u2.reg1.d[6]"); // {EXPANDED}
		result.add("\\u2.reg1.d[7]"); // {EXPANDED}
		result.add("\\u2.reg1.ena"); // {EXPANDED}
		result.add("\\u2.reg1.rst"); // {EXPANDED}
		result.add("\\u2.reg3.clk"); // {EXPANDED}
		result.add("\\u2.reg3.d"); // {EXPANDED}
		result.add("\\u2.reg3.q"); // {EXPANDED}
		result.add("\\u2.reg3.rst"); // {EXPANDED}
		result.add("\\u2.reg4.clk"); // {EXPANDED}
		result.add("\\u2.reg4.d"); // {EXPANDED}
		result.add("\\u2.reg4.rst"); // {EXPANDED}
		result.add("\\u2.reg5.clk"); // {EXPANDED}
		result.add("\\u2.reg5.d"); // {EXPANDED}
		result.add("\\u2.reg5.rst"); // {EXPANDED}
		result.add("\\u2.rst"); // {EXPANDED}
		result.add("\\u2.stb"); // {EXPANDED}
		result.add("\\u2.stb_latched"); // {EXPANDED}
		result.add("\\u2.stb_selected"); // {EXPANDED}
		result.add("\\u2.stb_sync"); // {EXPANDED}
		result.add("\\u2.sync1.clk"); // {EXPANDED}
		result.add("\\u2.sync1.rst"); // {EXPANDED}
		result.add("\\u2.sync1.sync_x"); // {EXPANDED}
		result.add("\\u2.sync1.x"); // {EXPANDED}
		result.add("\\u2.valid"); // {EXPANDED}
		result.add("_000_"); // {EXPANDED}
		result.add("_001_"); // {EXPANDED}
		result.add("_002_"); // {EXPANDED}
		result.add("_003_"); // {EXPANDED}
		result.add("_004_"); // {EXPANDED}
		result.add("_005_"); // {EXPANDED}
		result.add("_006_"); // {EXPANDED}
		result.add("_007_"); // {EXPANDED}
		result.add("_008_"); // {EXPANDED}
		result.add("_009_"); // {EXPANDED}
		result.add("_010_"); // {EXPANDED}
		result.add("_011_"); // {EXPANDED}
		result.add("_012_"); // {EXPANDED}
		result.add("_013_"); // {EXPANDED}
		result.add("_014_"); // {EXPANDED}
		result.add("_015_"); // {EXPANDED}
		result.add("_016_"); // {EXPANDED}
		result.add("_017_"); // {EXPANDED}
		result.add("_018_"); // {EXPANDED}
		result.add("_019_"); // {EXPANDED}
		result.add("_020_"); // {EXPANDED}
		result.add("_021_"); // {EXPANDED}
		result.add("_022_"); // {EXPANDED}
		result.add("_023_"); // {EXPANDED}
		result.add("_024_"); // {EXPANDED}
		result.add("_025_"); // {EXPANDED}
		result.add("_026_"); // {EXPANDED}
		result.add("_027_"); // {EXPANDED}
		result.add("_028_"); // {EXPANDED}
		result.add("_029_"); // {EXPANDED}
		result.add("_030_"); // {EXPANDED}
		result.add("_031_"); // {EXPANDED}
		result.add("_032_"); // {EXPANDED}
		result.add("_033_"); // {EXPANDED}
		result.add("_034_"); // {EXPANDED}
		result.add("_035_"); // {EXPANDED}
		result.add("_036_"); // {EXPANDED}
		result.add("_037_"); // {EXPANDED}
		result.add("_038_"); // {EXPANDED}
		result.add("_039_"); // {EXPANDED}
		result.add("_040_"); // {EXPANDED}
		result.add("_041_"); // {EXPANDED}
		result.add("_042_"); // {EXPANDED}
		result.add("_043_"); // {EXPANDED}
		result.add("_044_"); // {EXPANDED}
		result.add("_045_"); // {EXPANDED}
		result.add("_046_"); // {EXPANDED}
		result.add("_047_"); // {EXPANDED}
		result.add("_048_"); // {EXPANDED}
		result.add("_049_"); // {EXPANDED}
		result.add("_050_"); // {EXPANDED}
		result.add("_051_"); // {EXPANDED}
		result.add("_052_"); // {EXPANDED}
		result.add("_053_"); // {EXPANDED}
		result.add("ack"); // {EXPANDED}
		result.add("busy"); // {EXPANDED}
		result.add("data[0]"); // {EXPANDED}
		result.add("data[1]"); // {EXPANDED}
		result.add("data[2]"); // {EXPANDED}
		result.add("data[3]"); // {EXPANDED}
		result.add("data[4]"); // {EXPANDED}
		result.add("data[5]"); // {EXPANDED}
		result.add("data[6]"); // {EXPANDED}
		result.add("data[7]"); // {EXPANDED}
		result.add("data_out[0]"); // {EXPANDED}
		result.add("data_out[1]"); // {EXPANDED}
		result.add("data_out[2]"); // {EXPANDED}
		result.add("data_out[3]"); // {EXPANDED}
		result.add("data_out[4]"); // {EXPANDED}
		result.add("data_out[5]"); // {EXPANDED}
		result.add("data_out[6]"); // {EXPANDED}
		result.add("data_out[7]"); // {EXPANDED}
		result.add("stb"); // {EXPANDED}
		result.add("valid"); // {EXPANDED}
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		return 23; // {EXPANDED}
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		return 9; // {EXPANDED}
		//@formatter:on
	}

	public void simulate(int initial, int[] inputs, File vcdFile, File txtFile, boolean printCounter,
			boolean runGtkwave) throws Exception {

		ArrayList<String> sigNames = getSignalNames();

		int cycles = inputs.length;

		// determine longest signal name
		// (for pretty printing)

		int maxL = 0;

		for (String s : sigNames)
			maxL = s.length() > maxL ? s.length() : maxL;

		String strFmt = String.format("%%%ds : ", maxL + 2);

		// run simulation

		ArrayList<int[]> waveforms = simulate_internal(initial, inputs);

		// printing to console & files

		if (printCounter) {

			System.out.printf(strFmt, "Cycle");

			for (int i = 0; i < cycles; i++)
				System.out.printf("%d", i % 10);

			System.out.println();

			System.out.println();

			for (int j = 0; j < waveforms.size(); j++) {

				boolean isHidden = sigNames.get(j).charAt(0) == '*';

				if (isHidden)
					continue;

				if (j == getStateBitCount())
					System.out.println();

				int[] sig = waveforms.get(j);

				System.out.printf(strFmt, sigNames.get(j));

				for (int i = 0; i < cycles; i++) {

					if (sig[i] == H)
						System.out.printf("1");
					else if (sig[i] == L)
						System.out.printf("0");
					else
						System.out.printf("X");

				}

				System.out.println();

			}

			System.out.println();

			System.out.flush();

		}

		if (vcdFile != null)
			generateVCD(sigNames, waveforms, vcdFile, runGtkwave);

		if (txtFile != null)
			generateTextFile(sigNames, waveforms, txtFile);

	}

	private ArrayList<int[]> simulate_internal(int initial, int[] inputs) {

		int cycles = inputs.length;

		//@formatter:off
		// int[] {STATE_BIT} = new int[cycles];
		int[] n_u1_reg1_q_0__51 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_q_1__163 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_q_2__153 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_q_3__120 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_q_4__173 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_q_5__186 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_q_6__105 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_q_7__177 = new int[cycles]; // {EXPANDED}
		int[] n_u1_stb_102 = new int[cycles]; // {EXPANDED}
		int[] n_u1_sync1_f1_151 = new int[cycles]; // {EXPANDED}
		int[] n_u1_sync1_f2_202 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_q_0__33 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_q_1__195 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_q_2__167 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_q_3__132 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_q_4__136 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_q_5__126 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_q_6__34 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_q_7__166 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg4_q_155 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg5_q_9 = new int[cycles]; // {EXPANDED}
		int[] n_u2_sync1_f1_66 = new int[cycles]; // {EXPANDED}
		int[] n_u2_sync1_f2_75 = new int[cycles]; // {EXPANDED}

		// int[] {INPUT_BIT} = new int[cycles];
		int[] ndata_in_0__88 = new int[cycles]; // {EXPANDED}
		int[] ndata_in_1__154 = new int[cycles]; // {EXPANDED}
		int[] ndata_in_2__49 = new int[cycles]; // {EXPANDED}
		int[] ndata_in_3__99 = new int[cycles]; // {EXPANDED}
		int[] ndata_in_4__64 = new int[cycles]; // {EXPANDED}
		int[] ndata_in_5__83 = new int[cycles]; // {EXPANDED}
		int[] ndata_in_6__127 = new int[cycles]; // {EXPANDED}
		int[] ndata_in_7__119 = new int[cycles]; // {EXPANDED}
		int[] nsend_79 = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		n_u1_reg1_q_0__51[0] = -(initial >> 0 & 1); // {EXPANDED}
		n_u1_reg1_q_1__163[0] = -(initial >> 1 & 1); // {EXPANDED}
		n_u1_reg1_q_2__153[0] = -(initial >> 2 & 1); // {EXPANDED}
		n_u1_reg1_q_3__120[0] = -(initial >> 3 & 1); // {EXPANDED}
		n_u1_reg1_q_4__173[0] = -(initial >> 4 & 1); // {EXPANDED}
		n_u1_reg1_q_5__186[0] = -(initial >> 5 & 1); // {EXPANDED}
		n_u1_reg1_q_6__105[0] = -(initial >> 6 & 1); // {EXPANDED}
		n_u1_reg1_q_7__177[0] = -(initial >> 7 & 1); // {EXPANDED}
		n_u1_stb_102[0] = -(initial >> 8 & 1); // {EXPANDED}
		n_u1_sync1_f1_151[0] = -(initial >> 9 & 1); // {EXPANDED}
		n_u1_sync1_f2_202[0] = -(initial >> 10 & 1); // {EXPANDED}
		n_u2_reg1_q_0__33[0] = -(initial >> 11 & 1); // {EXPANDED}
		n_u2_reg1_q_1__195[0] = -(initial >> 12 & 1); // {EXPANDED}
		n_u2_reg1_q_2__167[0] = -(initial >> 13 & 1); // {EXPANDED}
		n_u2_reg1_q_3__132[0] = -(initial >> 14 & 1); // {EXPANDED}
		n_u2_reg1_q_4__136[0] = -(initial >> 15 & 1); // {EXPANDED}
		n_u2_reg1_q_5__126[0] = -(initial >> 16 & 1); // {EXPANDED}
		n_u2_reg1_q_6__34[0] = -(initial >> 17 & 1); // {EXPANDED}
		n_u2_reg1_q_7__166[0] = -(initial >> 18 & 1); // {EXPANDED}
		n_u2_reg4_q_155[0] = -(initial >> 19 & 1); // {EXPANDED}
		n_u2_reg5_q_9[0] = -(initial >> 20 & 1); // {EXPANDED}
		n_u2_sync1_f1_66[0] = -(initial >> 21 & 1); // {EXPANDED}
		n_u2_sync1_f2_75[0] = -(initial >> 22 & 1); // {EXPANDED}

		// int[] {NON_STATE_BIT} = new int[cycles];
		int[] n_u1_ack_199 = new int[cycles]; // {EXPANDED}
		int[] n_u1_ack_selected_169 = new int[cycles]; // {EXPANDED}
		int[] n_u1_ack_sync_115 = new int[cycles]; // {EXPANDED}
		int[] n_u1_busy_185 = new int[cycles]; // {EXPANDED}
		int[] n_u1_clk_25 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_0__103 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_1__20 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_2__43 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_3__94 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_4__56 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_5__15 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_6__58 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_7__8 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_in_0__27 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_in_1__134 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_in_2__124 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_in_3__150 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_in_4__70 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_in_5__117 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_in_6__122 = new int[cycles]; // {EXPANDED}
		int[] n_u1_data_in_7__17 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_clk_174 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_d_0__121 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_d_1__97 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_d_2__13 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_d_3__171 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_d_4__2 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_d_5__165 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_d_6__138 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_d_7__3 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_ena_196 = new int[cycles]; // {EXPANDED}
		int[] n_u1_reg1_rst_187 = new int[cycles]; // {EXPANDED}
		int[] n_u1_rst_14 = new int[cycles]; // {EXPANDED}
		int[] n_u1_send_45 = new int[cycles]; // {EXPANDED}
		int[] n_u1_sync1_clk_19 = new int[cycles]; // {EXPANDED}
		int[] n_u1_sync1_rst_191 = new int[cycles]; // {EXPANDED}
		int[] n_u1_sync1_sync_x_162 = new int[cycles]; // {EXPANDED}
		int[] n_u1_sync1_x_81 = new int[cycles]; // {EXPANDED}
		int[] n_u2_ack_118 = new int[cycles]; // {EXPANDED}
		int[] n_u2_clk_111 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_0__29 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_1__178 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_2__101 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_3__7 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_4__159 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_5__175 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_6__48 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_7__68 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_out_0__125 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_out_1__147 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_out_2__0 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_out_3__84 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_out_4__110 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_out_5__23 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_out_6__140 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_out_7__40 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_rec_0__96 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_rec_1__31 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_rec_2__6 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_rec_3__80 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_rec_4__128 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_rec_5__28 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_rec_6__148 = new int[cycles]; // {EXPANDED}
		int[] n_u2_data_rec_7__188 = new int[cycles]; // {EXPANDED}
		int[] n_u2_newdata_161 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_clk_109 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_d_0__47 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_d_1__4 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_d_2__78 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_d_3__183 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_d_4__30 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_d_5__46 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_d_6__170 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_d_7__38 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_ena_157 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg1_rst_22 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg3_clk_130 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg3_d_156 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg3_q_63 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg3_rst_12 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg4_clk_152 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg4_d_106 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg4_rst_91 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg5_clk_86 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg5_d_42 = new int[cycles]; // {EXPANDED}
		int[] n_u2_reg5_rst_74 = new int[cycles]; // {EXPANDED}
		int[] n_u2_rst_145 = new int[cycles]; // {EXPANDED}
		int[] n_u2_stb_137 = new int[cycles]; // {EXPANDED}
		int[] n_u2_stb_latched_135 = new int[cycles]; // {EXPANDED}
		int[] n_u2_stb_selected_69 = new int[cycles]; // {EXPANDED}
		int[] n_u2_stb_sync_85 = new int[cycles]; // {EXPANDED}
		int[] n_u2_sync1_clk_21 = new int[cycles]; // {EXPANDED}
		int[] n_u2_sync1_rst_87 = new int[cycles]; // {EXPANDED}
		int[] n_u2_sync1_sync_x_180 = new int[cycles]; // {EXPANDED}
		int[] n_u2_sync1_x_149 = new int[cycles]; // {EXPANDED}
		int[] n_u2_valid_71 = new int[cycles]; // {EXPANDED}
		int[] n_000__5 = new int[cycles]; // {EXPANDED}
		int[] n_001__53 = new int[cycles]; // {EXPANDED}
		int[] n_002__44 = new int[cycles]; // {EXPANDED}
		int[] n_003__11 = new int[cycles]; // {EXPANDED}
		int[] n_004__203 = new int[cycles]; // {EXPANDED}
		int[] n_005__60 = new int[cycles]; // {EXPANDED}
		int[] n_006__172 = new int[cycles]; // {EXPANDED}
		int[] n_007__57 = new int[cycles]; // {EXPANDED}
		int[] n_008__176 = new int[cycles]; // {EXPANDED}
		int[] n_009__141 = new int[cycles]; // {EXPANDED}
		int[] n_010__61 = new int[cycles]; // {EXPANDED}
		int[] n_011__59 = new int[cycles]; // {EXPANDED}
		int[] n_012__112 = new int[cycles]; // {EXPANDED}
		int[] n_013__184 = new int[cycles]; // {EXPANDED}
		int[] n_014__76 = new int[cycles]; // {EXPANDED}
		int[] n_015__26 = new int[cycles]; // {EXPANDED}
		int[] n_016__104 = new int[cycles]; // {EXPANDED}
		int[] n_017__189 = new int[cycles]; // {EXPANDED}
		int[] n_018__98 = new int[cycles]; // {EXPANDED}
		int[] n_019__139 = new int[cycles]; // {EXPANDED}
		int[] n_020__116 = new int[cycles]; // {EXPANDED}
		int[] n_021__73 = new int[cycles]; // {EXPANDED}
		int[] n_022__182 = new int[cycles]; // {EXPANDED}
		int[] n_023__67 = new int[cycles]; // {EXPANDED}
		int[] n_024__108 = new int[cycles]; // {EXPANDED}
		int[] n_025__93 = new int[cycles]; // {EXPANDED}
		int[] n_026__62 = new int[cycles]; // {EXPANDED}
		int[] n_027__131 = new int[cycles]; // {EXPANDED}
		int[] n_028__194 = new int[cycles]; // {EXPANDED}
		int[] n_029__200 = new int[cycles]; // {EXPANDED}
		int[] n_030__197 = new int[cycles]; // {EXPANDED}
		int[] n_031__168 = new int[cycles]; // {EXPANDED}
		int[] n_032__18 = new int[cycles]; // {EXPANDED}
		int[] n_033__181 = new int[cycles]; // {EXPANDED}
		int[] n_034__65 = new int[cycles]; // {EXPANDED}
		int[] n_035__146 = new int[cycles]; // {EXPANDED}
		int[] n_036__129 = new int[cycles]; // {EXPANDED}
		int[] n_037__190 = new int[cycles]; // {EXPANDED}
		int[] n_038__113 = new int[cycles]; // {EXPANDED}
		int[] n_039__24 = new int[cycles]; // {EXPANDED}
		int[] n_040__144 = new int[cycles]; // {EXPANDED}
		int[] n_041__90 = new int[cycles]; // {EXPANDED}
		int[] n_042__100 = new int[cycles]; // {EXPANDED}
		int[] n_043__50 = new int[cycles]; // {EXPANDED}
		int[] n_044__142 = new int[cycles]; // {EXPANDED}
		int[] n_045__77 = new int[cycles]; // {EXPANDED}
		int[] n_046__164 = new int[cycles]; // {EXPANDED}
		int[] n_047__55 = new int[cycles]; // {EXPANDED}
		int[] n_048__198 = new int[cycles]; // {EXPANDED}
		int[] n_049__10 = new int[cycles]; // {EXPANDED}
		int[] n_050__133 = new int[cycles]; // {EXPANDED}
		int[] n_051__204 = new int[cycles]; // {EXPANDED}
		int[] n_052__158 = new int[cycles]; // {EXPANDED}
		int[] n_053__37 = new int[cycles]; // {EXPANDED}
		int[] nack_35 = new int[cycles]; // {EXPANDED}
		int[] nbusy_107 = new int[cycles]; // {EXPANDED}
		int[] ndata_0__114 = new int[cycles]; // {EXPANDED}
		int[] ndata_1__179 = new int[cycles]; // {EXPANDED}
		int[] ndata_2__143 = new int[cycles]; // {EXPANDED}
		int[] ndata_3__54 = new int[cycles]; // {EXPANDED}
		int[] ndata_4__32 = new int[cycles]; // {EXPANDED}
		int[] ndata_5__1 = new int[cycles]; // {EXPANDED}
		int[] ndata_6__82 = new int[cycles]; // {EXPANDED}
		int[] ndata_7__123 = new int[cycles]; // {EXPANDED}
		int[] ndata_out_0__72 = new int[cycles]; // {EXPANDED}
		int[] ndata_out_1__89 = new int[cycles]; // {EXPANDED}
		int[] ndata_out_2__193 = new int[cycles]; // {EXPANDED}
		int[] ndata_out_3__36 = new int[cycles]; // {EXPANDED}
		int[] ndata_out_4__192 = new int[cycles]; // {EXPANDED}
		int[] ndata_out_5__39 = new int[cycles]; // {EXPANDED}
		int[] ndata_out_6__52 = new int[cycles]; // {EXPANDED}
		int[] ndata_out_7__201 = new int[cycles]; // {EXPANDED}
		int[] nstb_160 = new int[cycles]; // {EXPANDED}
		int[] nvalid_92 = new int[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);
			ndata_in_0__88[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}
			ndata_in_1__154[i] = -(inputs[i] >> 1 & 1); // {EXPANDED}
			ndata_in_2__49[i] = -(inputs[i] >> 2 & 1); // {EXPANDED}
			ndata_in_3__99[i] = -(inputs[i] >> 3 & 1); // {EXPANDED}
			ndata_in_4__64[i] = -(inputs[i] >> 4 & 1); // {EXPANDED}
			ndata_in_5__83[i] = -(inputs[i] >> 5 & 1); // {EXPANDED}
			ndata_in_6__127[i] = -(inputs[i] >> 6 & 1); // {EXPANDED}
			ndata_in_7__119[i] = -(inputs[i] >> 7 & 1); // {EXPANDED}
			nsend_79[i] = -(inputs[i] >> 8 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			n_053__37[i] = 0; // {EXPANDED}
			n_u2_data_out_2__0[i] = n_u2_reg1_q_2__167[i]; // {EXPANDED}
			ndata_5__1[i] = n_u1_reg1_q_5__186[i]; // {EXPANDED}
			n_u1_reg1_d_4__2[i] = ndata_in_4__64[i]; // {EXPANDED}
			n_u1_reg1_d_7__3[i] = ndata_in_7__119[i]; // {EXPANDED}
			n_u2_data_out_4__110[i] = n_u2_reg1_q_4__136[i]; // {EXPANDED}
			n_038__113[i] = ~nsend_79[i]; // {EXPANDED}
			n_u2_reg1_d_1__4[i] = n_u1_reg1_q_1__163[i]; // {EXPANDED}
			n_u2_data_rec_2__6[i] = n_u2_reg1_q_2__167[i]; // {EXPANDED}
			n_u2_data_3__7[i] = n_u1_reg1_q_3__120[i]; // {EXPANDED}
			ndata_0__114[i] = n_u1_reg1_q_0__51[i]; // {EXPANDED}
			n_u1_ack_sync_115[i] = n_u1_sync1_f2_202[i]; // {EXPANDED}
			n_u1_data_in_5__117[i] = ndata_in_5__83[i]; // {EXPANDED}
			n_u2_ack_118[i] = n_u2_reg5_q_9[i]; // {EXPANDED}
			n_u1_data_7__8[i] = n_u1_reg1_q_7__177[i]; // {EXPANDED}
			n_u1_reg1_d_0__121[i] = ndata_in_0__88[i]; // {EXPANDED}
			n_049__10[i] = ~(n_u1_reg1_q_5__186[i] & n_038__113[i]); // {EXPANDED}
			n_u1_data_in_6__122[i] = ndata_in_6__127[i]; // {EXPANDED}
			ndata_7__123[i] = n_u1_reg1_q_7__177[i]; // {EXPANDED}
			n_u1_data_in_2__124[i] = ndata_in_2__49[i]; // {EXPANDED}
			n_u2_data_out_0__125[i] = n_u2_reg1_q_0__33[i]; // {EXPANDED}
			n_u1_reg1_d_2__13[i] = ndata_in_2__49[i]; // {EXPANDED}
			n_u2_data_rec_4__128[i] = n_u2_reg1_q_4__136[i]; // {EXPANDED}
			n_u1_data_5__15[i] = n_u1_reg1_q_5__186[i]; // {EXPANDED}
			n_u1_data_in_7__17[i] = ndata_in_7__119[i]; // {EXPANDED}
			n_u1_data_1__20[i] = n_u1_reg1_q_1__163[i]; // {EXPANDED}
			n_050__133[i] = ~(nsend_79[i] & ndata_in_5__83[i]); // {EXPANDED}
			n_u1_data_in_1__134[i] = ndata_in_1__154[i]; // {EXPANDED}
			n_u2_stb_latched_135[i] = n_u2_reg5_q_9[i]; // {EXPANDED}
			n_u2_data_out_5__23[i] = n_u2_reg1_q_5__126[i]; // {EXPANDED}
			n_039__24[i] = ~(n_u1_reg1_q_0__51[i] & n_038__113[i]); // {EXPANDED}
			n_u2_stb_137[i] = n_u1_stb_102[i]; // {EXPANDED}
			n_u1_reg1_d_6__138[i] = ndata_in_6__127[i]; // {EXPANDED}
			n_u1_data_in_0__27[i] = ndata_in_0__88[i]; // {EXPANDED}
			n_u2_data_out_6__140[i] = n_u2_reg1_q_6__34[i]; // {EXPANDED}
			n_u2_data_rec_5__28[i] = n_u2_reg1_q_5__126[i]; // {EXPANDED}
			n_u2_reg1_d_4__30[i] = n_u1_reg1_q_4__173[i]; // {EXPANDED}
			n_u2_data_0__29[i] = n_u1_reg1_q_0__51[i]; // {EXPANDED}
			n_u2_data_rec_1__31[i] = n_u2_reg1_q_1__195[i]; // {EXPANDED}
			n_044__142[i] = ~(nsend_79[i] & ndata_in_2__49[i]); // {EXPANDED}
			ndata_2__143[i] = n_u1_reg1_q_2__153[i]; // {EXPANDED}
			ndata_4__32[i] = n_u1_reg1_q_4__173[i]; // {EXPANDED}
			n_040__144[i] = ~(nsend_79[i] & ndata_in_0__88[i]); // {EXPANDED}
			nack_35[i] = n_u2_reg5_q_9[i]; // {EXPANDED}
			n_u2_data_out_1__147[i] = n_u2_reg1_q_1__195[i]; // {EXPANDED}
			ndata_out_3__36[i] = n_u2_reg1_q_3__132[i]; // {EXPANDED}
			n_u2_data_rec_6__148[i] = n_u2_reg1_q_6__34[i]; // {EXPANDED}
			n_u2_sync1_x_149[i] = n_u1_stb_102[i]; // {EXPANDED}
			n_u1_data_in_3__150[i] = ndata_in_3__99[i]; // {EXPANDED}
			ndata_out_5__39[i] = n_u2_reg1_q_5__126[i]; // {EXPANDED}
			n_u2_reg1_d_7__38[i] = n_u1_reg1_q_7__177[i]; // {EXPANDED}
			n_u2_data_out_7__40[i] = n_u2_reg1_q_7__166[i]; // {EXPANDED}
			n_u1_data_2__43[i] = n_u1_reg1_q_2__153[i]; // {EXPANDED}
			n_u2_reg5_d_42[i] = n_u2_sync1_f2_75[i]; // {EXPANDED}
			n_002__44[i] = ~(nsend_79[i] & ndata_in_7__119[i]); // {EXPANDED}
			n_u2_reg3_d_156[i] = n_u2_sync1_f2_75[i]; // {EXPANDED}
			n_u2_data_4__159[i] = n_u1_reg1_q_4__173[i]; // {EXPANDED}
			n_052__158[i] = ~(nsend_79[i] & ndata_in_6__127[i]); // {EXPANDED}
			nstb_160[i] = n_u1_stb_102[i]; // {EXPANDED}
			n_u1_send_45[i] = nsend_79[i]; // {EXPANDED}
			n_u2_reg1_d_5__46[i] = n_u1_reg1_q_5__186[i]; // {EXPANDED}
			n_u2_reg1_d_0__47[i] = n_u1_reg1_q_0__51[i]; // {EXPANDED}
			n_u1_sync1_sync_x_162[i] = n_u1_sync1_f2_202[i]; // {EXPANDED}
			n_u2_data_6__48[i] = n_u1_reg1_q_6__105[i]; // {EXPANDED}
			n_043__50[i] = ~(n_u1_reg1_q_2__153[i] & n_038__113[i]); // {EXPANDED}
			ndata_out_6__52[i] = n_u2_reg1_q_6__34[i]; // {EXPANDED}
			n_046__164[i] = ~(nsend_79[i] & ndata_in_3__99[i]); // {EXPANDED}
			n_001__53[i] = ~(n_u1_reg1_q_7__177[i] & n_038__113[i]); // {EXPANDED}
			n_u1_reg1_d_5__165[i] = ndata_in_5__83[i]; // {EXPANDED}
			ndata_3__54[i] = n_u1_reg1_q_3__120[i]; // {EXPANDED}
			n_047__55[i] = ~(n_038__113[i] & n_u1_reg1_q_4__173[i]); // {EXPANDED}
			n_u1_data_4__56[i] = n_u1_reg1_q_4__173[i]; // {EXPANDED}
			n_u1_data_6__58[i] = n_u1_reg1_q_6__105[i]; // {EXPANDED}
			n_u1_ack_selected_169[i] = n_u1_sync1_f2_202[i]; // {EXPANDED}
			n_u2_reg1_d_6__170[i] = n_u1_reg1_q_6__105[i]; // {EXPANDED}
			n_u2_reg3_q_63[i] = n_u2_reg5_q_9[i]; // {EXPANDED}
			n_u1_reg1_d_3__171[i] = ndata_in_3__99[i]; // {EXPANDED}
			n_u2_data_7__68[i] = n_u1_reg1_q_7__177[i]; // {EXPANDED}
			n_u2_stb_selected_69[i] = n_u2_sync1_f2_75[i]; // {EXPANDED}
			n_u2_data_5__175[i] = n_u1_reg1_q_5__186[i]; // {EXPANDED}
			n_u1_data_in_4__70[i] = ndata_in_4__64[i]; // {EXPANDED}
			n_u2_valid_71[i] = n_u2_reg4_q_155[i]; // {EXPANDED}
			ndata_out_0__72[i] = n_u2_reg1_q_0__33[i]; // {EXPANDED}
			n_u2_data_1__178[i] = n_u1_reg1_q_1__163[i]; // {EXPANDED}
			ndata_1__179[i] = n_u1_reg1_q_1__163[i]; // {EXPANDED}
			n_u2_sync1_sync_x_180[i] = n_u2_sync1_f2_75[i]; // {EXPANDED}
			n_045__77[i] = ~(n_u1_reg1_q_3__120[i] & n_038__113[i]); // {EXPANDED}
			n_u2_reg1_d_3__183[i] = n_u1_reg1_q_3__120[i]; // {EXPANDED}
			n_u2_reg1_d_2__78[i] = n_u1_reg1_q_2__153[i]; // {EXPANDED}
			n_u1_sync1_x_81[i] = n_u2_reg5_q_9[i]; // {EXPANDED}
			n_u2_data_rec_3__80[i] = n_u2_reg1_q_3__132[i]; // {EXPANDED}
			ndata_6__82[i] = n_u1_reg1_q_6__105[i]; // {EXPANDED}
			n_u2_data_out_3__84[i] = n_u2_reg1_q_3__132[i]; // {EXPANDED}
			n_u2_data_rec_7__188[i] = n_u2_reg1_q_7__166[i]; // {EXPANDED}
			n_037__190[i] = ~(n_u1_stb_102[i] | nsend_79[i]); // {EXPANDED}
			n_u2_stb_sync_85[i] = n_u2_sync1_f2_75[i]; // {EXPANDED}
			ndata_out_1__89[i] = n_u2_reg1_q_1__195[i]; // {EXPANDED}
			n_041__90[i] = ~(n_038__113[i] & n_u1_reg1_q_1__163[i]); // {EXPANDED}
			nvalid_92[i] = n_u2_reg4_q_155[i]; // {EXPANDED}
			n_u1_data_3__94[i] = n_u1_reg1_q_3__120[i]; // {EXPANDED}
			n_u2_data_rec_0__96[i] = n_u2_reg1_q_0__33[i]; // {EXPANDED}
			ndata_out_4__192[i] = n_u2_reg1_q_4__136[i]; // {EXPANDED}
			ndata_out_2__193[i] = n_u2_reg1_q_2__167[i]; // {EXPANDED}
			n_u1_reg1_d_1__97[i] = ndata_in_1__154[i]; // {EXPANDED}
			n_018__98[i] = ~n_u2_sync1_f2_75[i]; // {EXPANDED}
			n_042__100[i] = ~(ndata_in_1__154[i] & nsend_79[i]); // {EXPANDED}
			n_u2_data_2__101[i] = n_u1_reg1_q_2__153[i]; // {EXPANDED}
			n_u1_reg1_ena_196[i] = nsend_79[i]; // {EXPANDED}
			n_048__198[i] = ~(nsend_79[i] & ndata_in_4__64[i]); // {EXPANDED}
			n_u1_data_0__103[i] = n_u1_reg1_q_0__51[i]; // {EXPANDED}
			n_u1_ack_199[i] = n_u2_reg5_q_9[i]; // {EXPANDED}
			ndata_out_7__201[i] = n_u2_reg1_q_7__166[i]; // {EXPANDED}
			nbusy_107[i] = n_u1_stb_102[i] | n_u1_sync1_f2_202[i]; // {EXPANDED}
			n_051__204[i] = ~(n_u1_reg1_q_6__105[i] & n_038__113[i]); // {EXPANDED}
			n_011__59[i] = ~(n_040__144[i] & n_039__24[i]); // {EXPANDED}
			n_019__139[i] = ~(n_002__44[i] & n_001__53[i]); // {EXPANDED}
			n_017__189[i] = ~(n_052__158[i] & n_051__204[i]); // {EXPANDED}
			n_010__61[i] = ~(n_037__190[i] | n_u1_sync1_f2_202[i]); // {EXPANDED}
			n_015__26[i] = ~(n_048__198[i] & n_047__55[i]); // {EXPANDED}
			n_u2_newdata_161[i] = ~(n_018__98[i] | n_u2_reg5_q_9[i]); // {EXPANDED}
			n_012__112[i] = ~(n_041__90[i] & n_042__100[i]); // {EXPANDED}
			n_014__76[i] = ~(n_045__77[i] & n_046__164[i]); // {EXPANDED}
			n_016__104[i] = ~(n_050__133[i] & n_049__10[i]); // {EXPANDED}
			n_020__116[i] = n_018__98[i] | n_u2_reg5_q_9[i]; // {EXPANDED}
			n_013__184[i] = ~(n_043__50[i] & n_044__142[i]); // {EXPANDED}
			n_u1_busy_185[i] = nbusy_107[i]; // {EXPANDED}
			n_026__62[i] = ~(n_u2_newdata_161[i] & n_u1_reg1_q_2__153[i]); // {EXPANDED}
			n_025__93[i] = ~(n_020__116[i] & n_u2_reg1_q_2__167[i]); // {EXPANDED}
			n_035__146[i] = ~(n_020__116[i] & n_u2_reg1_q_7__166[i]); // {EXPANDED}
			n_034__65[i] = ~(n_u1_reg1_q_6__105[i] & n_u2_newdata_161[i]); // {EXPANDED}
			n_023__67[i] = ~(n_020__116[i] & n_u2_reg1_q_1__195[i]); // {EXPANDED}
			n_028__194[i] = ~(n_u2_newdata_161[i] & n_u1_reg1_q_3__120[i]); // {EXPANDED}
			n_u2_reg1_ena_157[i] = n_u2_newdata_161[i]; // {EXPANDED}
			n_021__73[i] = ~(n_u2_reg1_q_0__33[i] & n_020__116[i]); // {EXPANDED}
			n_030__197[i] = ~(n_u2_newdata_161[i] & n_u1_reg1_q_4__173[i]); // {EXPANDED}
			n_036__129[i] = ~(n_u1_reg1_q_7__177[i] & n_u2_newdata_161[i]); // {EXPANDED}
			n_032__18[i] = ~(n_u2_newdata_161[i] & n_u1_reg1_q_5__186[i]); // {EXPANDED}
			n_027__131[i] = ~(n_u2_reg1_q_3__132[i] & n_020__116[i]); // {EXPANDED}
			n_033__181[i] = ~(n_u2_reg1_q_6__34[i] & n_020__116[i]); // {EXPANDED}
			n_022__182[i] = ~(n_u1_reg1_q_0__51[i] & n_u2_newdata_161[i]); // {EXPANDED}
			n_029__200[i] = ~(n_020__116[i] & n_u2_reg1_q_4__136[i]); // {EXPANDED}
			n_u2_reg4_d_106[i] = n_u2_newdata_161[i]; // {EXPANDED}
			n_024__108[i] = ~(n_u2_newdata_161[i] & n_u1_reg1_q_1__163[i]); // {EXPANDED}
			n_031__168[i] = ~(n_u2_reg1_q_5__126[i] & n_020__116[i]); // {EXPANDED}
			n_000__5[i] = ~(n_021__73[i] & n_022__182[i]); // {EXPANDED}
			n_003__11[i] = ~(n_023__67[i] & n_024__108[i]); // {EXPANDED}
			n_007__57[i] = ~(n_032__18[i] & n_031__168[i]); // {EXPANDED}
			n_005__60[i] = ~(n_028__194[i] & n_027__131[i]); // {EXPANDED}
			n_009__141[i] = ~(n_035__146[i] & n_036__129[i]); // {EXPANDED}
			n_004__203[i] = ~(n_026__62[i] & n_025__93[i]); // {EXPANDED}
			n_008__176[i] = ~(n_034__65[i] & n_033__181[i]); // {EXPANDED}
			n_006__172[i] = ~(n_029__200[i] & n_030__197[i]); // {EXPANDED}

			if (i < cycles-1) {

				//@formatter:off
				// {STATE_BIT}[i+1] |= {NEXT_STATE_BIT}[i];
				n_u1_reg1_q_0__51[i+1] |= n_011__59[i]; // {EXPANDED}
				n_u1_reg1_q_1__163[i+1] |= n_012__112[i]; // {EXPANDED}
				n_u1_reg1_q_2__153[i+1] |= n_013__184[i]; // {EXPANDED}
				n_u1_reg1_q_3__120[i+1] |= n_014__76[i]; // {EXPANDED}
				n_u1_reg1_q_4__173[i+1] |= n_015__26[i]; // {EXPANDED}
				n_u1_reg1_q_5__186[i+1] |= n_016__104[i]; // {EXPANDED}
				n_u1_reg1_q_6__105[i+1] |= n_017__189[i]; // {EXPANDED}
				n_u1_reg1_q_7__177[i+1] |= n_019__139[i]; // {EXPANDED}
				n_u1_stb_102[i+1] |= n_010__61[i]; // {EXPANDED}
				n_u1_sync1_f1_151[i+1] |= n_u2_reg5_q_9[i]; // {EXPANDED}
				n_u1_sync1_f2_202[i+1] |= n_u1_sync1_f1_151[i]; // {EXPANDED}
				n_u2_reg1_q_0__33[i+1] |= n_000__5[i]; // {EXPANDED}
				n_u2_reg1_q_1__195[i+1] |= n_003__11[i]; // {EXPANDED}
				n_u2_reg1_q_2__167[i+1] |= n_004__203[i]; // {EXPANDED}
				n_u2_reg1_q_3__132[i+1] |= n_005__60[i]; // {EXPANDED}
				n_u2_reg1_q_4__136[i+1] |= n_006__172[i]; // {EXPANDED}
				n_u2_reg1_q_5__126[i+1] |= n_007__57[i]; // {EXPANDED}
				n_u2_reg1_q_6__34[i+1] |= n_008__176[i]; // {EXPANDED}
				n_u2_reg1_q_7__166[i+1] |= n_009__141[i]; // {EXPANDED}
				n_u2_reg4_q_155[i+1] |= n_u2_newdata_161[i]; // {EXPANDED}
				n_u2_reg5_q_9[i+1] |= n_u2_sync1_f2_75[i]; // {EXPANDED}
				n_u2_sync1_f1_66[i+1] |= n_u1_stb_102[i]; // {EXPANDED}
				n_u2_sync1_f2_75[i+1] |= n_u2_sync1_f1_66[i]; // {EXPANDED}
				//@formatter:on

			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(n_u1_reg1_q_0__51); // {EXPANDED}
		waveforms.add(n_u1_reg1_q_1__163); // {EXPANDED}
		waveforms.add(n_u1_reg1_q_2__153); // {EXPANDED}
		waveforms.add(n_u1_reg1_q_3__120); // {EXPANDED}
		waveforms.add(n_u1_reg1_q_4__173); // {EXPANDED}
		waveforms.add(n_u1_reg1_q_5__186); // {EXPANDED}
		waveforms.add(n_u1_reg1_q_6__105); // {EXPANDED}
		waveforms.add(n_u1_reg1_q_7__177); // {EXPANDED}
		waveforms.add(n_u1_stb_102); // {EXPANDED}
		waveforms.add(n_u1_sync1_f1_151); // {EXPANDED}
		waveforms.add(n_u1_sync1_f2_202); // {EXPANDED}
		waveforms.add(n_u2_reg1_q_0__33); // {EXPANDED}
		waveforms.add(n_u2_reg1_q_1__195); // {EXPANDED}
		waveforms.add(n_u2_reg1_q_2__167); // {EXPANDED}
		waveforms.add(n_u2_reg1_q_3__132); // {EXPANDED}
		waveforms.add(n_u2_reg1_q_4__136); // {EXPANDED}
		waveforms.add(n_u2_reg1_q_5__126); // {EXPANDED}
		waveforms.add(n_u2_reg1_q_6__34); // {EXPANDED}
		waveforms.add(n_u2_reg1_q_7__166); // {EXPANDED}
		waveforms.add(n_u2_reg4_q_155); // {EXPANDED}
		waveforms.add(n_u2_reg5_q_9); // {EXPANDED}
		waveforms.add(n_u2_sync1_f1_66); // {EXPANDED}
		waveforms.add(n_u2_sync1_f2_75); // {EXPANDED}

		// waveforms.add({INPUT_BIT});
		waveforms.add(ndata_in_0__88); // {EXPANDED}
		waveforms.add(ndata_in_1__154); // {EXPANDED}
		waveforms.add(ndata_in_2__49); // {EXPANDED}
		waveforms.add(ndata_in_3__99); // {EXPANDED}
		waveforms.add(ndata_in_4__64); // {EXPANDED}
		waveforms.add(ndata_in_5__83); // {EXPANDED}
		waveforms.add(ndata_in_6__127); // {EXPANDED}
		waveforms.add(ndata_in_7__119); // {EXPANDED}
		waveforms.add(nsend_79); // {EXPANDED}

		// waveforms.add({NON_STATE_BIT});
		waveforms.add(n_u1_ack_199); // {EXPANDED}
		waveforms.add(n_u1_ack_selected_169); // {EXPANDED}
		waveforms.add(n_u1_ack_sync_115); // {EXPANDED}
		waveforms.add(n_u1_busy_185); // {EXPANDED}
		waveforms.add(n_u1_clk_25); // {EXPANDED}
		waveforms.add(n_u1_data_0__103); // {EXPANDED}
		waveforms.add(n_u1_data_1__20); // {EXPANDED}
		waveforms.add(n_u1_data_2__43); // {EXPANDED}
		waveforms.add(n_u1_data_3__94); // {EXPANDED}
		waveforms.add(n_u1_data_4__56); // {EXPANDED}
		waveforms.add(n_u1_data_5__15); // {EXPANDED}
		waveforms.add(n_u1_data_6__58); // {EXPANDED}
		waveforms.add(n_u1_data_7__8); // {EXPANDED}
		waveforms.add(n_u1_data_in_0__27); // {EXPANDED}
		waveforms.add(n_u1_data_in_1__134); // {EXPANDED}
		waveforms.add(n_u1_data_in_2__124); // {EXPANDED}
		waveforms.add(n_u1_data_in_3__150); // {EXPANDED}
		waveforms.add(n_u1_data_in_4__70); // {EXPANDED}
		waveforms.add(n_u1_data_in_5__117); // {EXPANDED}
		waveforms.add(n_u1_data_in_6__122); // {EXPANDED}
		waveforms.add(n_u1_data_in_7__17); // {EXPANDED}
		waveforms.add(n_u1_reg1_clk_174); // {EXPANDED}
		waveforms.add(n_u1_reg1_d_0__121); // {EXPANDED}
		waveforms.add(n_u1_reg1_d_1__97); // {EXPANDED}
		waveforms.add(n_u1_reg1_d_2__13); // {EXPANDED}
		waveforms.add(n_u1_reg1_d_3__171); // {EXPANDED}
		waveforms.add(n_u1_reg1_d_4__2); // {EXPANDED}
		waveforms.add(n_u1_reg1_d_5__165); // {EXPANDED}
		waveforms.add(n_u1_reg1_d_6__138); // {EXPANDED}
		waveforms.add(n_u1_reg1_d_7__3); // {EXPANDED}
		waveforms.add(n_u1_reg1_ena_196); // {EXPANDED}
		waveforms.add(n_u1_reg1_rst_187); // {EXPANDED}
		waveforms.add(n_u1_rst_14); // {EXPANDED}
		waveforms.add(n_u1_send_45); // {EXPANDED}
		waveforms.add(n_u1_sync1_clk_19); // {EXPANDED}
		waveforms.add(n_u1_sync1_rst_191); // {EXPANDED}
		waveforms.add(n_u1_sync1_sync_x_162); // {EXPANDED}
		waveforms.add(n_u1_sync1_x_81); // {EXPANDED}
		waveforms.add(n_u2_ack_118); // {EXPANDED}
		waveforms.add(n_u2_clk_111); // {EXPANDED}
		waveforms.add(n_u2_data_0__29); // {EXPANDED}
		waveforms.add(n_u2_data_1__178); // {EXPANDED}
		waveforms.add(n_u2_data_2__101); // {EXPANDED}
		waveforms.add(n_u2_data_3__7); // {EXPANDED}
		waveforms.add(n_u2_data_4__159); // {EXPANDED}
		waveforms.add(n_u2_data_5__175); // {EXPANDED}
		waveforms.add(n_u2_data_6__48); // {EXPANDED}
		waveforms.add(n_u2_data_7__68); // {EXPANDED}
		waveforms.add(n_u2_data_out_0__125); // {EXPANDED}
		waveforms.add(n_u2_data_out_1__147); // {EXPANDED}
		waveforms.add(n_u2_data_out_2__0); // {EXPANDED}
		waveforms.add(n_u2_data_out_3__84); // {EXPANDED}
		waveforms.add(n_u2_data_out_4__110); // {EXPANDED}
		waveforms.add(n_u2_data_out_5__23); // {EXPANDED}
		waveforms.add(n_u2_data_out_6__140); // {EXPANDED}
		waveforms.add(n_u2_data_out_7__40); // {EXPANDED}
		waveforms.add(n_u2_data_rec_0__96); // {EXPANDED}
		waveforms.add(n_u2_data_rec_1__31); // {EXPANDED}
		waveforms.add(n_u2_data_rec_2__6); // {EXPANDED}
		waveforms.add(n_u2_data_rec_3__80); // {EXPANDED}
		waveforms.add(n_u2_data_rec_4__128); // {EXPANDED}
		waveforms.add(n_u2_data_rec_5__28); // {EXPANDED}
		waveforms.add(n_u2_data_rec_6__148); // {EXPANDED}
		waveforms.add(n_u2_data_rec_7__188); // {EXPANDED}
		waveforms.add(n_u2_newdata_161); // {EXPANDED}
		waveforms.add(n_u2_reg1_clk_109); // {EXPANDED}
		waveforms.add(n_u2_reg1_d_0__47); // {EXPANDED}
		waveforms.add(n_u2_reg1_d_1__4); // {EXPANDED}
		waveforms.add(n_u2_reg1_d_2__78); // {EXPANDED}
		waveforms.add(n_u2_reg1_d_3__183); // {EXPANDED}
		waveforms.add(n_u2_reg1_d_4__30); // {EXPANDED}
		waveforms.add(n_u2_reg1_d_5__46); // {EXPANDED}
		waveforms.add(n_u2_reg1_d_6__170); // {EXPANDED}
		waveforms.add(n_u2_reg1_d_7__38); // {EXPANDED}
		waveforms.add(n_u2_reg1_ena_157); // {EXPANDED}
		waveforms.add(n_u2_reg1_rst_22); // {EXPANDED}
		waveforms.add(n_u2_reg3_clk_130); // {EXPANDED}
		waveforms.add(n_u2_reg3_d_156); // {EXPANDED}
		waveforms.add(n_u2_reg3_q_63); // {EXPANDED}
		waveforms.add(n_u2_reg3_rst_12); // {EXPANDED}
		waveforms.add(n_u2_reg4_clk_152); // {EXPANDED}
		waveforms.add(n_u2_reg4_d_106); // {EXPANDED}
		waveforms.add(n_u2_reg4_rst_91); // {EXPANDED}
		waveforms.add(n_u2_reg5_clk_86); // {EXPANDED}
		waveforms.add(n_u2_reg5_d_42); // {EXPANDED}
		waveforms.add(n_u2_reg5_rst_74); // {EXPANDED}
		waveforms.add(n_u2_rst_145); // {EXPANDED}
		waveforms.add(n_u2_stb_137); // {EXPANDED}
		waveforms.add(n_u2_stb_latched_135); // {EXPANDED}
		waveforms.add(n_u2_stb_selected_69); // {EXPANDED}
		waveforms.add(n_u2_stb_sync_85); // {EXPANDED}
		waveforms.add(n_u2_sync1_clk_21); // {EXPANDED}
		waveforms.add(n_u2_sync1_rst_87); // {EXPANDED}
		waveforms.add(n_u2_sync1_sync_x_180); // {EXPANDED}
		waveforms.add(n_u2_sync1_x_149); // {EXPANDED}
		waveforms.add(n_u2_valid_71); // {EXPANDED}
		waveforms.add(n_000__5); // {EXPANDED}
		waveforms.add(n_001__53); // {EXPANDED}
		waveforms.add(n_002__44); // {EXPANDED}
		waveforms.add(n_003__11); // {EXPANDED}
		waveforms.add(n_004__203); // {EXPANDED}
		waveforms.add(n_005__60); // {EXPANDED}
		waveforms.add(n_006__172); // {EXPANDED}
		waveforms.add(n_007__57); // {EXPANDED}
		waveforms.add(n_008__176); // {EXPANDED}
		waveforms.add(n_009__141); // {EXPANDED}
		waveforms.add(n_010__61); // {EXPANDED}
		waveforms.add(n_011__59); // {EXPANDED}
		waveforms.add(n_012__112); // {EXPANDED}
		waveforms.add(n_013__184); // {EXPANDED}
		waveforms.add(n_014__76); // {EXPANDED}
		waveforms.add(n_015__26); // {EXPANDED}
		waveforms.add(n_016__104); // {EXPANDED}
		waveforms.add(n_017__189); // {EXPANDED}
		waveforms.add(n_018__98); // {EXPANDED}
		waveforms.add(n_019__139); // {EXPANDED}
		waveforms.add(n_020__116); // {EXPANDED}
		waveforms.add(n_021__73); // {EXPANDED}
		waveforms.add(n_022__182); // {EXPANDED}
		waveforms.add(n_023__67); // {EXPANDED}
		waveforms.add(n_024__108); // {EXPANDED}
		waveforms.add(n_025__93); // {EXPANDED}
		waveforms.add(n_026__62); // {EXPANDED}
		waveforms.add(n_027__131); // {EXPANDED}
		waveforms.add(n_028__194); // {EXPANDED}
		waveforms.add(n_029__200); // {EXPANDED}
		waveforms.add(n_030__197); // {EXPANDED}
		waveforms.add(n_031__168); // {EXPANDED}
		waveforms.add(n_032__18); // {EXPANDED}
		waveforms.add(n_033__181); // {EXPANDED}
		waveforms.add(n_034__65); // {EXPANDED}
		waveforms.add(n_035__146); // {EXPANDED}
		waveforms.add(n_036__129); // {EXPANDED}
		waveforms.add(n_037__190); // {EXPANDED}
		waveforms.add(n_038__113); // {EXPANDED}
		waveforms.add(n_039__24); // {EXPANDED}
		waveforms.add(n_040__144); // {EXPANDED}
		waveforms.add(n_041__90); // {EXPANDED}
		waveforms.add(n_042__100); // {EXPANDED}
		waveforms.add(n_043__50); // {EXPANDED}
		waveforms.add(n_044__142); // {EXPANDED}
		waveforms.add(n_045__77); // {EXPANDED}
		waveforms.add(n_046__164); // {EXPANDED}
		waveforms.add(n_047__55); // {EXPANDED}
		waveforms.add(n_048__198); // {EXPANDED}
		waveforms.add(n_049__10); // {EXPANDED}
		waveforms.add(n_050__133); // {EXPANDED}
		waveforms.add(n_051__204); // {EXPANDED}
		waveforms.add(n_052__158); // {EXPANDED}
		waveforms.add(n_053__37); // {EXPANDED}
		waveforms.add(nack_35); // {EXPANDED}
		waveforms.add(nbusy_107); // {EXPANDED}
		waveforms.add(ndata_0__114); // {EXPANDED}
		waveforms.add(ndata_1__179); // {EXPANDED}
		waveforms.add(ndata_2__143); // {EXPANDED}
		waveforms.add(ndata_3__54); // {EXPANDED}
		waveforms.add(ndata_4__32); // {EXPANDED}
		waveforms.add(ndata_5__1); // {EXPANDED}
		waveforms.add(ndata_6__82); // {EXPANDED}
		waveforms.add(ndata_7__123); // {EXPANDED}
		waveforms.add(ndata_out_0__72); // {EXPANDED}
		waveforms.add(ndata_out_1__89); // {EXPANDED}
		waveforms.add(ndata_out_2__193); // {EXPANDED}
		waveforms.add(ndata_out_3__36); // {EXPANDED}
		waveforms.add(ndata_out_4__192); // {EXPANDED}
		waveforms.add(ndata_out_5__39); // {EXPANDED}
		waveforms.add(ndata_out_6__52); // {EXPANDED}
		waveforms.add(ndata_out_7__201); // {EXPANDED}
		waveforms.add(nstb_160); // {EXPANDED}
		waveforms.add(nvalid_92); // {EXPANDED}
		//@formatter:on

		return waveforms;
	}

	private String getBinary(int num, int digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Integer.toBinaryString(num)).replace(' ', '0');
	}

	private void generateTextFile(ArrayList<String> sigNames, ArrayList<int[]> waveforms, File txtFile)
			throws FileNotFoundException {

		// prepare file content

		ArrayList<String> lines = new ArrayList<String>();

		int maxSigName = 0;

		for (String s : sigNames)
			maxSigName = s.length() > maxSigName ? s.length() : maxSigName;

		String strFmt = String.format("%%%ds : ", maxSigName);

		for (int i = 0; i < sigNames.size(); i++) {

			String l = String.format(strFmt, sigNames.get(i));

			StringBuilder sb = new StringBuilder(l);

			int[] sigWaveform = waveforms.get(i);

			for (int j = 0; j < sigWaveform.length; j++) {

				int v = sigWaveform[j];

				if (v == -1)
					sb.append("1");
				else if (v == 0)
					sb.append("0");
				else
					sb.append("x");

			}

			lines.add(sb.toString());

		}

		// write to file

		System.out
				.println("Saving counter-example waveform data (plain-text) to " + txtFile.getAbsolutePath() + " ...");

		PrintStream fout = new PrintStream(txtFile);

		for (String l : lines)
			fout.println(l);

		fout.close();

	}

	private void generateVCD(ArrayList<String> sigNames, ArrayList<int[]> waveforms, File vcdFile, boolean runGtkwave)
			throws Exception {

		ArrayList<String> vcdLines = new ArrayList<String>();

		ArrayList<String> tclLines = new ArrayList<String>();

		// rename signals with spaces

		for (int i = 0; i < sigNames.size(); i++) {

			String s = sigNames.get(i);

			if (s.startsWith("@"))
				s = "prop-" + s.replace(" ", "-");

			if (s.startsWith("\\"))
				s = s.substring(1);

			sigNames.set(i, s);

		}

		// prepare vcd file content

		vcdLines.add("$timescale 1ns $end");

		vcdLines.add("$scope module logic $end");

		for (int i = 0; i < sigNames.size(); i++)
			vcdLines.add(String.format("$var wire 1 %s %s $end", getIdentifierVCD(i), sigNames.get(i)));

		vcdLines.add("$upscope $end");

		vcdLines.add("$enddefinitions $end");

		vcdLines.add("$dumpvars");

		for (int i = 0; i < sigNames.size(); i++)
			vcdLines.add(String.format("x%s", getIdentifierVCD(i)));

		vcdLines.add("$end");

		int cycles = waveforms.get(0).length;

		for (int j = 0; j < cycles; j++) {

			vcdLines.add(String.format("#%d", j));

			for (int i = 0; i < sigNames.size(); i++) {

				int newVal = waveforms.get(i)[j];

				int oldVal = j > 0 ? waveforms.get(i)[j - 1] : newVal + 1;

				if (newVal != oldVal) {

					String newValStr;

					if (newVal == -1)
						newValStr = "1";
					else if (newVal == 0)
						newValStr = "0";
					else
						newValStr = "x";

					vcdLines.add(String.format("%s%s", newValStr, getIdentifierVCD(i)));

				}

			}

		}

		vcdLines.add(String.format("#%d", cycles));

		// writing vcd file

		File tempDir = new File(System.getProperty("java.io.tmpdir"));

		System.out.println("Saving counter-example waveform data (VCD) to " + vcdFile.getAbsolutePath() + " ...");

		PrintStream fout = new PrintStream(vcdFile);

		for (String l : vcdLines)
			fout.println(l);

		fout.close();

		if (runGtkwave) {

			// prepare gtkwave tcl script content

			// gtkwave has an issue with adding array bits
			// as a walk-around add the array name instead
			// of the individual bits

			tclLines.add("set sigList [list]");

			for (String s : sigNames) {

				if (!s.startsWith("\\") && s.contains("[")) {

					if (s.contains("[0]")) {

						s = s.replaceAll("\\[\\d+\\]", "");

					} else {

						continue;

					}

				}

				tclLines.add(String.format("lappend sigList {%s}", s));
			}

			tclLines.add("set num_added [ gtkwave::addSignalsFromList $sigList ]");

			// write tcl script

			File tclFile = new File(tempDir, "counter-example-show.tcl");

			System.out.println("Saving gtkwave tcl script to " + tclFile.getAbsolutePath() + " ...");

			PrintStream fout2 = new PrintStream(tclFile);

			for (String l : tclLines)
				fout2.println(l);

			fout2.close();

			// run gtkwave

			String cmd = String.format("gtkwave \"%s\" -S \"%s\"", vcdFile.getAbsolutePath(),
					tclFile.getAbsolutePath());

			final Runtime rt = Runtime.getRuntime();

			try {

				rt.exec(cmd);

			} catch (IOException e) {

				e.printStackTrace();

				throw new Exception("unable to run gtkwave, make sure it is installed and setup in PATH");

			}

		}

	}

	private String getIdentifierVCD(int i) {

		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		int n = chars.length();

		int length = 4;

		String result = "";

		for (int j = 0; j < length; j++) {

			int d = i % n;

			result = chars.charAt(d) + result;

			i = (i - d) / n;

			if (i == 0)
				break;
		}

		return result;

	}

}
