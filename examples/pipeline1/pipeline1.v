module pipeline1 (clk, rst, ena, data_in, data_out, valid, busy);

	parameter SIZE = 8;

	input clk, rst, ena;

	input [SIZE-1:0] data_in;
	output [SIZE-1:0] data_out = stage2;

	output valid = valid2;

	output busy = valid1;

	reg [SIZE-1:0] stage1;
	reg [SIZE-1:0] stage2;

	reg valid1;
	reg valid2;

	always @(posedge clk or posedge rst) begin

		if (rst) begin

			stage1 <= 0;
			stage2 <= 0;
			valid1 <= 0;
			valid2 <= 0;

		end else if (ena) begin

			stage1 <= data_in;
			stage2 <= stage1;
			valid1 <= valid1 | ena;
			valid2 <= valid1;

		end

	end

endmodule
