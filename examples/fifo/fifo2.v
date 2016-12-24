`ifndef _inc_fifo_
`define _inc_fifo_

module fifo2 (clk, reset, full, empty, item_in, item_out, write, read);

	parameter SIZE = 2;
	parameter DEPTH_LOG2 = 1;

	localparam DEPTH = 2 ** DEPTH_LOG2;

	input clk, reset, write, read;
	input [SIZE-1:0] item_in;
	output [SIZE-1:0] item_out;
	output full, empty;

	reg full, empty;
	reg [SIZE-1:0] memory [DEPTH-1:0];
	reg [DEPTH_LOG2-1:0] read_ptr;
	reg [DEPTH_LOG2-1:0] write_ptr;
	wire [DEPTH_LOG2-1:0] read_ptr_p1 = read_ptr + 1;
	wire [DEPTH_LOG2-1:0] write_ptr_p1 = write_ptr + 1;

	assign item_out = memory[read_ptr];

	integer i;

	wire do_read = read & !empty;
	wire do_write = write & !full;

	always @(posedge clk or posedge reset) begin

		if (reset) begin

			read_ptr <= 0;
			write_ptr <= 0;
			empty <= 1;
			full <= 0;
			for (i=0; i<DEPTH; i=i+1) memory[i] <= 0;

		end else begin

			if (do_read & do_write) begin

				read_ptr <= read_ptr_p1;
				write_ptr <= write_ptr_p1;
				memory[write_ptr] <= item_in;

			end else if (do_read) begin

				full <= 0;
				read_ptr <= read_ptr_p1;
				empty <= (read_ptr_p1 == write_ptr);

			end else if (do_write) begin

				memory[write_ptr] <= item_in;
				empty <= 0;
				write_ptr <= write_ptr_p1;
				full <= (read_ptr == write_ptr_p1);

			end

		end

	end

endmodule

`endif
