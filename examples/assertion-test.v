module top (clk, rst, ena, count, valid);

	input clk, rst, ena;
	
	output [3:0] count;
	
	output valid;
	
	reg [3:0] count;
	
	always @(posedge clk or posedge rst) begin
	
		if (rst) begin
		
			count <= 0;
			
		end else if (ena) begin
		
			count <= count + 1;
			
		end
	
	end
	
	// assertion logic
	
	reg ena_old;
	
	always @(posedge clk or posedge rst) begin
		if (rst) begin
			ena_old <= 0;
		end else begin
			ena_old <= ena;
		end
	end
	
	// (x -> y) == (~x V y)
	
	assign valid = (ena_old == 0) || (count<5);
	
endmodule
