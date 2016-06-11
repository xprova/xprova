module top (clk, rst, x, count, valid, assume);

	input clk, rst, x;

	output [7:0] count;

	output valid, assume;

	wire ena;

	assign ena = x == ^count;

	assign valid = (count != 25);

	assign assume = (count < 50);

	counter u1 (clk, rst, ena, count);

endmodule

module counter (clk, rst, ena, count);

	input clk, rst, ena;

	output [7:0] count;

	reg [7:0] count;

	always @(posedge rst or posedge clk) begin

		if (rst) begin

			count <= 0;

		end else begin

			count <= count + ena;

		end

	end

endmodule
