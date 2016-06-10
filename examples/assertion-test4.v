module lfsr(clk, rst, ena, state);

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

module top (clk, rst, ena, valid);

	input clk, rst, ena;
	
	output valid;
	
	wire [3:0] state;
	
	lfsr u1 (clk, rst, ena, state);
	
	assign valid = state != 8;
	
endmodule

