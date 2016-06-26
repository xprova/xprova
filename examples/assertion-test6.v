module top (clk, rst, ena, a, b, c);

	input clk, rst, ena;
	
	reg [5:0] count;

	output a, b, c;

	assign a = ~count[5];
	assign b = count[4];
	assign c = count[0];

	always @(posedge clk or posedge rst) begin

		if (rst) begin

			count <= 0;

		end else if (ena) begin

			count <= count + 1;

		end

	end

endmodule