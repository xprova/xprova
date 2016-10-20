`define SIZE 2

`define FIFO_DEPTH_LOG2 3

`define FIFO_DEPTH (1<<`FIFO_DEPTH_LOG2)

module fifo(clk, reset, full, empty, item_in, item_out, write, read);

  parameter routerid=-1;

  input clk, reset, write, read;

  output full, empty;

  reg [`SIZE-1:0] mem [`FIFO_DEPTH-1:0];

  reg [`FIFO_DEPTH_LOG2-1:0] read_ptr;

  reg [`FIFO_DEPTH_LOG2-1:0] write_ptr;

  wire [`FIFO_DEPTH_LOG2-1:0] read_ptr_p1; assign read_ptr_p1 = read_ptr + 1;

  wire [`FIFO_DEPTH_LOG2-1:0] write_ptr_p1; assign write_ptr_p1 = write_ptr + 1;

  reg [`FIFO_DEPTH_LOG2:0] count;

  input [`SIZE-1:0] item_in;

  output [`SIZE-1:0] item_out;

  reg full, empty;

  integer i;

  always @(posedge clk or posedge reset) begin

    if (reset) begin

      read_ptr <= 0;

      write_ptr <= 0;

      empty <= 1;

      full <= 0;

      count <= 0;

      for (i=0; i<`FIFO_DEPTH; i=i+1) begin
        mem[i] <= 0;
      end

    end else begin

      if (read & !empty) begin

        full <= 0;

        read_ptr <= read_ptr_p1;

        if (read_ptr_p1 == write_ptr) empty <= 1;

        //if (routerid > -1) $display("router %d fifo pop : %d", routerid, item_out);

      end

      if (write & !full) begin

        mem [write_ptr] <= item_in;

        //if (routerid > -1) $display("router %d fifo push : %d", routerid, item_in);

        empty <= 0;

        write_ptr <= write_ptr_p1;

        if (read_ptr == write_ptr_p1) full <= 1;

      end

      if (actual_read & !actual_write)
        count <= count-1;
      else if (actual_write & !actual_read)
        count <= count+1;


    end

  end

  assign item_out = mem [read_ptr];

  wire actual_read = read & !empty;
  wire actual_write = write & !full;



endmodule
