module top (clk, rst, ena1, ena2, count, valid);

	input clk, rst, ena1, ena2;
	
	output [3:0] count;
	
	output valid;
	
	reg [3:0] count;
	
	wire a1, a2;
	
	assign a1 = ena1 && (count<2);
	assign a2 = ena2 && (count>=2); 
	
	always @(posedge clk or posedge rst) begin
	
		if (rst) begin
		
			count <= 0;
			
		end else if (ena1) begin
		
			count <= count + 1;
			
		end
	
	end
	
	// assertion logic
	
	reg ena1_old;
	
	always @(posedge clk or posedge rst) begin
		if (rst) begin
			ena1_old <= 0;
		end else begin
			ena1_old <= ena1;
		end
	end
	
	// (x -> y) == (~x V y)
	
	assign valid = (ena1_old == 0) || (count<5);
	
endmodule
