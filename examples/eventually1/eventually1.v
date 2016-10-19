// This is a simple design to test $eventually().
// The input `a` propagates through three FFs: r1, r2 and r3. Between r1 and
// r2, the value gets XOR'ed with b allowing `b` to mask the propagating value
// of `a`. Thus, the assertion $eventually(a, y) is false.

module top (clk, rst, a, b, y);

	input clk, rst, a, b;

	output y;

	reg r1, r2, r3;

	always @(posedge clk or posedge rst) begin

		if (rst) begin

			r1 <= 0;
			r2 <= 0;
			r3 <= 0;

		end else begin

			r1 <= a;
			r2 <= r1 ^ b;
			r3 <= r2;

		end

	end

	assign y = r3;

endmodule

