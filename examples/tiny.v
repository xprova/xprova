module top (clk, rst, a, b, c, d, count);

	input a, b;

	input clk, rst;

	output c, d;

	reg c, d;

	output [3:0] count;

	reg [3:0] count;

	always @(posedge clk or posedge rst) begin

		if (rst) begin

			c <= 0;
			d <= 0;
			count <= 0;

		end else begin

			c <= a;
			d <= b;

			count <= count + a;

		end

	end

endmodule
