module graycounter (clk, rst, ena, count);

	input clk, rst, ena;

	output [1:0] count;

	reg [1:0] count;

	always @(posedge clk or posedge rst) begin

		if (rst) begin

			count <= 0;

		end else if (ena) begin

			if (count == 0) begin

				count <= 1;

			end else if (count == 1) begin

				count <= 3;

			end else if (count == 3) begin

				count <= 2;

			end else if (count == 2) begin

				count <= 0;

			end

		end

	end

endmodule