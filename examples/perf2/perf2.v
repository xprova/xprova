// finding a state for which valid=0 in the design below takes 26.5 second on ifv:

module lfsr15(clk, rst, ena, state);

	// period = 32767

	input clk, rst, ena;

	output [14:0] state;

	reg [14:0] state;

	always @(posedge rst or posedge clk) begin

		if (rst == 1) begin

			state <= 1;

		end else if (ena) begin

			state[14:1] <= state[13:0];
			state[0] <= state[13] ^ state[14];

		end

	end

endmodule

module lfsr13(clk, rst, ena, state);

	// period = 8191

	input clk, rst, ena;

	output [12:0] state;

	reg [12:0] state;

	always @(posedge rst or posedge clk) begin

		if (rst == 1) begin

			state <= 1;

		end else if (ena) begin

			state[12:1] <= state[11:0];
			state[0] <= state[7] ^ state[10] ^ state[11] ^ state[12];

		end

	end

endmodule

module lfsr12(clk, rst, ena, state);

	// period = 4095

	input clk, rst, ena;

	output [11:0] state;

	reg [11:0] state;

	always @(posedge rst or posedge clk) begin

		if (rst == 1) begin

			state <= 1;

		end else if (ena) begin

			state[11:1] <= state[10:0];
			state[0] <= state[3] ^ state[9] ^ state[10] ^ state[11];

		end

	end

endmodule

module lfsr4(clk, rst, ena, state);

	// period = 15

	input clk, rst, ena;

	output [3:0] state;

	reg [3:0] state;

	always @(posedge rst or posedge clk) begin

		if (rst == 1) begin

			state <= 1;

		end else if (ena) begin

			state[3:1] <= state[2:0];

			state[0] <= state[2] ^ state[3];

		end

	end

endmodule

module top (clk, rst, code, err, secret, state);

	input clk, rst;

	output err;

	input [14:0] code;

	output [11:0] state;

	lfsr12 u1 (clk, rst, ena, state);

	output [14:0] secret;

	//lfsr13 u2 (clk, rst, ena, secret);
	lfsr15 u2 (clk, rst, ena, secret);

	wire ena;

	assign ena = (code == secret);

	assign err = state != 'b100000000000;

endmodule

