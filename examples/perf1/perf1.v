// finding a state for which err=0 in the design below takes 9.55 second on ifv:

module top (clk, rst, ena1, ena2, count, err);

	input clk, rst, ena1, ena2;
	
	output [11:0] count;
	
	output err;
	
	reg [11:0] count;
	reg [11:0] count2;
	
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
	
	assign err = (count == 12'hfff) | (count2 == 12'hfff);
	
endmodule
