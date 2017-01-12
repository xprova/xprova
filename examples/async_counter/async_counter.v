module async_counter(clk, reset, active, count);

	parameter N = 10;

	input clk, reset;

	input [5:0] active; // enough bits for (3*N)

	reg [N-1:0] n5;
	reg [N-1:0] n7;
	reg [N-1:0] n9;

	output [N-1:0] count = n7;

	wire [N-1:0] n7_prev;
	wire [N-1:0] n5_next;

	generate

		genvar i;

		for (i=0; i<N; i=i+1) begin

			if (i>0)
				assign n7_prev[i] = n7[i-1];
			else
				assign n7_prev[i] = ~n5[0];

			if (i<N-1)
				assign n5_next[i] = n5[i+1];
			else
				assign n5_next[i] = n7[i];

			always @(posedge clk or posedge reset)
				if (reset) begin
					n5[i] <= 0;
					n7[i] <= 0;
					n9[i] <= 1;
				end else begin
					n5[i] <= (active == (i*3 + 0)) ? (n7[i] & n9[i] | ~n7[i] & ~n9[i]) : n5[i];
					n7[i] <= (active == (i*3 + 1)) ? (n7[i] & ~n7_prev[i] | n7_prev[i] & n9[i]) : n7[i];
					n9[i] <= (active == (i*3 + 2)) ? (n7_prev[i] & n9[i] | ~n7_prev[i] & ~n5_next[i]) : n9[i];
				end
		end

	endgenerate

endmodule
