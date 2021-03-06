// finding a state for which valid=0 in the design below takes 9.55 second on ifv:

module assert1 (count, count2, valid);

	input [11:0] count;
	input [11:0] count2;
	
	output valid;

	assign valid = (count != 12'hfff) | (count2 != 12'hfff);
	
endmodule

module top (clk, rst, ena1, ena2, count, valid);

	input clk, rst, ena1, ena2;
	
	output [11:0] count;
	
	output valid;
	
	reg [11:0] count;
	reg [11:0] count2;
	
	wire valid;
	
	always @(posedge clk or posedge rst) begin
	
		if (rst) begin
		
			count <= 0;
			
		end else if (ena1) begin
		
			count <= count + 1;
			
		end
	
	end
	
	always @(posedge clk or posedge rst) begin
	
		if (rst) begin
		
			count2 <= 0;
			
		end else if (ena2) begin
		
			count2 <= count2 + 1;
			
		end
	
	end
	
	assert1 u1 (count, count2, valid);		
	
endmodule

