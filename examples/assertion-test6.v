module top (clk, rst, ena, count);

	input clk, rst, ena;

	output [5:0] count;

	reg [5:0] count;

	always @(posedge clk or posedge rst) begin

		if (rst) begin

			count <= 0;

		end else if (ena) begin

			count <= count + 1;

		end

	end

endmodule