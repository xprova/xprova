module top (clk, rst, ena, count);

	input clk, rst, ena;
	
	output [3:0] count;
	
	reg [3:0] count;
	
	always @(posedge clk or posedge rst) begin
	
		if (rst) begin
		
			count <= 0;
			
		end else if (ena) begin
		
			count <= count + 1;
			
		end
	
	end
	
	assertion1 u1 (clk, rst, count, ena, valid);
	
endmodule



module assertion1 (clk, rst, count, ena, valid);

	// assert property (ena |=> (count<5));

	input [3:0] count;
	
	input clk, rst, ena;
	
	output valid;
	
	wire ena_old;
	
	myflop u1 (.CK(clk), .RS(rst), .D(ena), .Q(ena_old));
	
	assign valid = (ena_old == 1) && (count<5);
	
endmodule



module myflop (CK, RS, D, Q);

	input CK, RS, D;
	output Q;
	
	always @(posedge CK or posedge RS) begin
		if (RS) begin
			Q <= 0;
		end else begin
			Q <= D;
		end
	end

endmodule