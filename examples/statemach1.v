// state transitions:
// 0 -> 1 (a=0)
// 0 -> 2 (a=1)
// 1 -> 3 (a=0)
// 1 -> 4 (a=1)
// 4 -> 5 (a=0)
// 4 -> 6 (a=1)
// 2 -> 6
// 5 -> 0

module statemach1(clk, rst, a, state);

	input clk, rst, a;
	output [2:0] state;

	reg [2:0] state;

	always @(posedge clk or posedge rst) begin

		if (rst) begin
			state <= 0;
		end else begin
			if (state == 0)
				state <= 1 + a;
			else if (state == 1)
				state <= 3 + a;
			else if (state == 4)
				state <= 5 + a;
			else if (state == 2)
				state <= 6;
			else if (state == 5)
				state <= 0;
		end

	end

endmodule