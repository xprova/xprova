`define DATA_BITS 8

// Setting 1:

// (uncomment to implement a 2-FF synchronizer on the receiver's end)

`define USE_RECEIVER_SYNCHRONIZER

// Setting 2:

// (uncomment to implement a 2-FF synchronizer on the sender's end)

`define USE_SENDER_SYNCHRONIZER

// Setting 3:

// use multiple synchronizer points assuming the synchronized signals are equal:

//`define USE_TWO_RECEIVERS_DIVERGE_HAZARD

// use multiple synchronization points without assuming synchronizer signals are equal:

//`define USE_TWO_RECEIVERS_DIVERGE_SAFE

// or uncomment both to use a single receiver

module topunit (clk1, clk2, rst, busy, send, data_in, data_out, valid);

	input clk1, clk2, rst, send;
	
	input [`DATA_BITS-1:0] data_in;

	output [`DATA_BITS-1:0] data_out;

	output busy, valid;

	// data, stb and ack are the crossover signals

	wire [`DATA_BITS-1:0] data;

	wire stb, ack;

	// module instantiations

	sender u1 (clk1, rst, busy, send, data_in, data, stb, ack);

	`ifdef USE_TWO_RECEIVERS_DIVERGE_HAZARD	
	
		two_receivers_hazard u2 (clk2, rst, data, stb, data_out, ack, valid);
		
	`elsif USE_TWO_RECEIVERS_DIVERGE_SAFE
	
		receiver_multiple_synchronization_safe u2 (clk2, rst, data, stb, data_out, ack, valid);
	
	`else
	
		receiver u2 (clk2, rst, data, stb, data_out, ack, valid);
	
	`endif

endmodule

module sender (clk, rst, busy, send, data_in, data, stb, ack);

	input clk, rst, send, ack;

	input [`DATA_BITS-1:0] data_in;

	output [`DATA_BITS-1:0] data;

	output stb, busy;

	reg stb;
	
	wire ack_selected;
	
	`ifdef USE_SENDER_SYNCHRONIZER
	
		synchronizer sync1 (clk, rst, ack, ack_sync);	
	
		assign ack_selected = ack_sync;
		
	`else
	
		assign ack_selected = ack;
		
	`endif
	
	assign busy = stb || ack_selected;
	
	myregister reg1 (clk, rst, data_in, data, send);
	
	always @(posedge clk or posedge rst) begin

		if (rst == 1) begin

			stb <= 0;
		
		end else if (send && !busy) begin

			stb <= 1;

		end else if (stb && ack_selected) begin
		
			stb <= 0;
			
		end

	end		

endmodule

module receiver (clk, rst, data, stb, data_out, ack, valid);

	input clk, rst, stb;

	input [`DATA_BITS-1:0] data;

	output [`DATA_BITS-1:0] data_out;

	output ack, valid;
	
	wire [`DATA_BITS-1:0] data_rec;
	
	wire stb_selected;
	
	`ifdef USE_RECEIVER_SYNCHRONIZER

		synchronizer sync1 (clk, rst, stb, stb_sync);
		
		assign stb_selected = stb_sync;
		
	`else
	
		assign stb_selected = stb;
		
	`endif
	
//	assign ack = valid | stb;
	/*
	reg ack;
	
	always @(posedge clk or posedge rst) begin
	
		if (rst == 1) begin
		
			ack <= 0;
			
		end else if (!ack & stb_selected) begin
		
			ack <= 1;
				
		end else if (ack & !stb_selected) begin
		
			ack <= 0;		
		
		end
		
	end
	*/
	
	//assign ack = stb_selected;
	
	myflop reg5 (clk, rst, stb_selected, ack, 1'b1);
	
	// copy `data` to `data_rec` when stb_selected=1

	myregister reg1 (clk, rst, data, data_rec, newdata);

	// copy `data_rec` to `data_out`
	
	//myregister reg2 (clk, rst, data_rec, data_out, 1'b1);
	
	assign data_out = data_rec;
	
	// receive is high for one cycle following assertion of stb_selected
	
	//myflop reg3 (clk, rst, stb_selected && !receive, receive, 1'b1);
	
	myflop reg3 (clk, rst, stb_selected, stb_latched, 1'b1);
	
	// valid bit
	
	//myflop reg4 (clk, rst, receive, valid, 1'b1);
	
	assign newdata = stb_selected & !stb_latched;
	
	myflop reg4 (clk, rst, newdata, valid, 1'b1);	
	
endmodule

module two_receivers_hazard (clk, rst, data, stb, data_out, ack, valid);

	input clk, rst, stb;

	input [`DATA_BITS-1:0] data;

	output [`DATA_BITS-1:0] data_out;

	output ack, valid;

	receiver u2 (clk2, rst, data, stb,         , ack, valid1);
	
	receiver u3 (clk2, rst, data, stb, data_out,    , valid2);
		
	assign valid = valid1;

endmodule

module receiver_multiple_synchronization_safe (clk, rst, data, stb, data_out, ack, valid);

	input clk, rst, stb;

	input [`DATA_BITS-1:0] data;

	output [`DATA_BITS-1:0] data_out;
	
	output ack, valid;
	
	receiver u1 (clk, rst, data, sync_stb, data_out, ack, valid);
	
	divg_synchronizer u2 (clk, rst, stb, sync_stb);	
	
endmodule


module myflop (clk, rst, d, q, ena);

	input clk, rst, ena;

	output q;

	reg q;
	
	input d;
	
	always @(posedge clk or posedge rst) begin
	
		if (rst == 1) begin
		
			q <= 0;
			
		end else if (ena) begin
		
			q <= d;
			
		end
		
	end		

endmodule

module myregister (clk, rst, d, q, ena);

	input clk, rst, ena;

	output [`DATA_BITS-1:0] q;

	reg [`DATA_BITS-1:0] q;
	
	input [`DATA_BITS-1:0] d;
	
	always @(posedge clk or posedge rst) begin
	
		if (rst == 1) begin
		
			q <= 0;
			
		end else if (ena) begin
		
			q <= d;
			
		end
		
	end		

endmodule


module forgetfulregister (clk, rst, d, q, ena);

	// behaves the sameway as a typical register
	// except it resets the data when ena is 0
	
	// this module simplifes some synthesized design
	// because it doesn't require instantiating a feedback
	// loop to retain the register state

	parameter BITS = 1;

	input clk, rst, ena;

	output [BITS-1:0] q;
	
	reg [BITS-1:0] q;	
	
	input [BITS-1:0] d;
	
	always @(posedge clk or posedge rst) begin
	
		if (rst == 1) begin
		
			q <= 0;
			
		end else if (ena) begin
		
			q <= d;
			
		end else begin
		
			q <= 0;
			
		end
		
	end		

endmodule

module synchronizer (clk, rst, x, sync_x);

	input clk, rst, x;
	
	output sync_x;

	reg f1, f2;
	
	assign sync_x = f2;

	always @(posedge clk or posedge rst) begin

		if (rst) begin
		
			f1 <= 0;
			
			f2 <= 0;
			
		end else begin
		
			f1 <= x;
			
			f2 <= f1;
			
		end
		
	end

endmodule

module divg_synchronizer (clk, rst, x, sync_x);

	input clk, rst, x;
	
	output sync_x;

	synchronizer u1 (clk, rst, x, sync_x_1);
	
	synchronizer u2 (clk, rst, x, sync_x_2);
	
	assign sync_x = sync_x_1 | sync_x_2;

endmodule
