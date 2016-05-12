
module top ( clk1, clk2, resetn, AC, done );
  output [7:0] AC;
  input clk1, clk2, resetn;
  output done;
  wire   stb, ack, unit1_n34, unit1_n32, unit1_n30, unit1_n28, unit1_n26,
         unit1_n24, unit1_n22, unit1_n20, unit1_N9, unit1_N8, unit1_N7,
         unit1_N6, unit1_N5, unit1_N4, unit2_n74, unit2_n72, unit2_n70,
         unit2_n68, unit2_n66, unit2_n64, unit2_n62, unit2_n60, unit2_n58,
         unit2_n56, unit2_n54, unit2_n52, unit2_n50, unit2_n48, unit2_n46,
         unit2_n44, unit2_n42, unit2_n40, unit1_n35, unit2_N17, unit2_N16,
         unit2_N15, unit2_N14, unit2_N13, unit2_N12, unit2_counter_0_,
         unit2_counter_1_, unit2_counter_2_, unit2_counter_3_,
         unit2_counter_4_, unit2_counter_5_, unit2_counter_6_,
         unit2_counter_7_, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12,
         n13, n14, n15, n16, n17, n18, n19, n20, n21, n22, n23, n24, n25, n26,
         n27, n28, n29, n30, n31, n32, n33, n34, n35, n36, n37, n38, n39, n40,
         n41, n42, n43, n44, n45, n46, n47, n48, n49, n50, n51, n52, n53, n54,
         n55, n56, n57, n58, n59, n60, n61, n62, n63, n64, n65, n66, n67, n68,
         n69, n70, n71, n72, n73, n74, n75, n76, n77, n78, n79, n80, n81, n82,
         n83, n84, n85, n86, n87, n88;
  wire   [7:0] data;
  wire   [7:2] unit2_add_91_carry;
  wire   [7:2] unit1_add_41_carry;

  QDFFRSBX1 unit1_data_reg_6_ ( .D(unit1_n20), .CK(clk1), .RB(resetn), .SB(
        unit1_n35), .Q(data[6]) );
  QDFFRSBX1 unit1_data_reg_5_ ( .D(unit1_n22), .CK(clk1), .RB(resetn), .SB(
        unit1_n35), .Q(data[5]) );
  QDFFRSBX1 unit1_data_reg_4_ ( .D(unit1_n24), .CK(clk1), .RB(resetn), .SB(
        unit1_n35), .Q(data[4]) );
  QDFFRSBX1 unit1_data_reg_3_ ( .D(unit1_n26), .CK(clk1), .RB(resetn), .SB(
        unit1_n35), .Q(data[3]) );
  QDFFRSBX1 unit1_data_reg_2_ ( .D(unit1_n28), .CK(clk1), .RB(resetn), .SB(
        unit1_n35), .Q(data[2]) );
  QDFFRSBX1 unit1_data_reg_1_ ( .D(unit1_n30), .CK(clk1), .RB(resetn), .SB(
        unit1_n35), .Q(data[1]) );
  QDFFRSBX1 unit1_data_reg_0_ ( .D(unit1_n32), .CK(clk1), .RB(resetn), .SB(
        unit1_n35), .Q(data[0]) );
  QDFFRSBX1 unit1_data_reg_7_ ( .D(unit1_n34), .CK(clk1), .RB(resetn), .SB(
        unit1_n35), .Q(data[7]) );
  QDFFRSBX1 unit1_stb_reg ( .D(n88), .CK(clk1), .RB(resetn), .SB(unit1_n35), 
        .Q(stb) );
  QDFFRSBX1 unit2_done_reg ( .D(unit2_n40), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(done) );
  QDFFRSBX1 unit2_counter_reg_7_ ( .D(unit2_n42), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(unit2_counter_7_) );
  QDFFRSBX1 unit2_counter_reg_6_ ( .D(unit2_n44), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(unit2_counter_6_) );
  QDFFRSBX1 unit2_counter_reg_5_ ( .D(unit2_n46), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(unit2_counter_5_) );
  QDFFRSBX1 unit2_counter_reg_4_ ( .D(unit2_n48), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(unit2_counter_4_) );
  QDFFRSBX1 unit2_counter_reg_3_ ( .D(unit2_n50), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(unit2_counter_3_) );
  QDFFRSBX1 unit2_counter_reg_2_ ( .D(unit2_n52), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(unit2_counter_2_) );
  QDFFRSBX1 unit2_counter_reg_1_ ( .D(unit2_n54), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(unit2_counter_1_) );
  QDFFRSBX1 unit2_counter_reg_0_ ( .D(unit2_n56), .CK(clk2), .RB(unit1_n35), 
        .SB(resetn), .Q(unit2_counter_0_) );
  QDFFRSBX1 unit2_AC_reg_7_ ( .D(unit2_n58), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(AC[7]) );
  QDFFRSBX1 unit2_AC_reg_6_ ( .D(unit2_n60), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(AC[6]) );
  QDFFRSBX1 unit2_AC_reg_5_ ( .D(unit2_n62), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(AC[5]) );
  QDFFRSBX1 unit2_AC_reg_4_ ( .D(unit2_n64), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(AC[4]) );
  QDFFRSBX1 unit2_AC_reg_3_ ( .D(unit2_n66), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(AC[3]) );
  QDFFRSBX1 unit2_AC_reg_2_ ( .D(unit2_n68), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(AC[2]) );
  QDFFRSBX1 unit2_AC_reg_1_ ( .D(unit2_n70), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(AC[1]) );
  QDFFRSBX1 unit2_AC_reg_0_ ( .D(unit2_n72), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(AC[0]) );
  QDFFRSBX1 unit2_ack_reg ( .D(unit2_n74), .CK(clk2), .RB(resetn), .SB(
        unit1_n35), .Q(ack) );
  HA1X1 unit2_add_91_U1_1_1 ( .A(unit2_counter_1_), .B(unit2_counter_0_), .C(
        unit2_add_91_carry[2]), .S(unit2_N12) );
  HA1X1 unit2_add_91_U1_1_2 ( .A(unit2_counter_2_), .B(unit2_add_91_carry[2]), 
        .C(unit2_add_91_carry[3]), .S(unit2_N13) );
  HA1X1 unit2_add_91_U1_1_3 ( .A(unit2_counter_3_), .B(unit2_add_91_carry[3]), 
        .C(unit2_add_91_carry[4]), .S(unit2_N14) );
  HA1X1 unit2_add_91_U1_1_4 ( .A(unit2_counter_4_), .B(unit2_add_91_carry[4]), 
        .C(unit2_add_91_carry[5]), .S(unit2_N15) );
  HA1X1 unit2_add_91_U1_1_5 ( .A(unit2_counter_5_), .B(unit2_add_91_carry[5]), 
        .C(unit2_add_91_carry[6]), .S(unit2_N16) );
  HA1X1 unit2_add_91_U1_1_6 ( .A(unit2_counter_6_), .B(unit2_add_91_carry[6]), 
        .C(unit2_add_91_carry[7]), .S(unit2_N17) );
  HA1X1 unit1_add_41_U1_1_1 ( .A(data[1]), .B(data[0]), .C(
        unit1_add_41_carry[2]), .S(unit1_N4) );
  HA1X1 unit1_add_41_U1_1_2 ( .A(data[2]), .B(unit1_add_41_carry[2]), .C(
        unit1_add_41_carry[3]), .S(unit1_N5) );
  HA1X1 unit1_add_41_U1_1_3 ( .A(data[3]), .B(unit1_add_41_carry[3]), .C(
        unit1_add_41_carry[4]), .S(unit1_N6) );
  HA1X1 unit1_add_41_U1_1_4 ( .A(data[4]), .B(unit1_add_41_carry[4]), .C(
        unit1_add_41_carry[5]), .S(unit1_N7) );
  HA1X1 unit1_add_41_U1_1_5 ( .A(data[5]), .B(unit1_add_41_carry[5]), .C(
        unit1_add_41_carry[6]), .S(unit1_N8) );
  HA1X1 unit1_add_41_U1_1_6 ( .A(data[6]), .B(unit1_add_41_carry[6]), .C(
        unit1_add_41_carry[7]), .S(unit1_N9) );
  TIE1X1 U1 ( .O(unit1_n35) );
  OR2X1 U2 ( .I1(n1), .I2(n2), .O(unit2_n74) );
  AN2X1 U3 ( .I1(stb), .I2(ack), .O(n1) );
  OR2X1 U4 ( .I1(n3), .I2(n4), .O(unit2_n72) );
  AN2X1 U5 ( .I1(AC[0]), .I2(n5), .O(n4) );
  OR2X1 U6 ( .I1(n6), .I2(n7), .O(n5) );
  AN2X1 U7 ( .I1(n8), .I2(n2), .O(n3) );
  AN2X1 U8 ( .I1(data[0]), .I2(n7), .O(n8) );
  INVX1 U9 ( .I(n9), .O(unit2_n70) );
  HA1X1 U10 ( .A(AC[1]), .B(n10), .S(n9) );
  OR2X1 U11 ( .I1(n11), .I2(n6), .O(n10) );
  HA1X1 U12 ( .A(data[1]), .B(n7), .S(n11) );
  INVX1 U13 ( .I(n12), .O(n7) );
  HA1X1 U14 ( .A(AC[2]), .B(n13), .S(unit2_n68) );
  AN2X1 U15 ( .I1(n2), .I2(n14), .O(n13) );
  HA1X1 U16 ( .A(data[2]), .B(n15), .S(n14) );
  HA1X1 U17 ( .A(AC[3]), .B(n16), .S(unit2_n66) );
  AN2X1 U18 ( .I1(n2), .I2(n17), .O(n16) );
  HA1X1 U19 ( .A(data[3]), .B(n18), .S(n17) );
  HA1X1 U20 ( .A(AC[4]), .B(n19), .S(unit2_n64) );
  AN2X1 U21 ( .I1(n2), .I2(n20), .O(n19) );
  HA1X1 U22 ( .A(data[4]), .B(n21), .S(n20) );
  HA1X1 U23 ( .A(AC[5]), .B(n22), .S(unit2_n62) );
  AN2X1 U24 ( .I1(n2), .I2(n23), .O(n22) );
  HA1X1 U25 ( .A(data[5]), .B(n24), .S(n23) );
  HA1X1 U26 ( .A(n25), .B(n26), .S(unit2_n60) );
  OR2X1 U27 ( .I1(n6), .I2(n27), .O(n26) );
  HA1X1 U28 ( .A(data[6]), .B(n28), .S(n27) );
  INVX1 U29 ( .I(n29), .O(unit2_n58) );
  HA1X1 U30 ( .A(AC[7]), .B(n30), .S(n29) );
  OR2X1 U31 ( .I1(n31), .I2(n6), .O(n30) );
  HA1X1 U32 ( .A(data[7]), .B(n32), .S(n31) );
  AN2X1 U33 ( .I1(n33), .I2(n34), .O(n32) );
  OR2X1 U34 ( .I1(n35), .I2(n36), .O(n34) );
  INVX1 U35 ( .I(data[6]), .O(n36) );
  AN2X1 U36 ( .I1(n28), .I2(n25), .O(n35) );
  OR2X1 U37 ( .I1(n28), .I2(n25), .O(n33) );
  INVX1 U38 ( .I(AC[6]), .O(n25) );
  INVX1 U39 ( .I(n37), .O(n28) );
  OR2X1 U40 ( .I1(n38), .I2(n39), .O(n37) );
  AN2X1 U41 ( .I1(AC[5]), .I2(n24), .O(n39) );
  AN2X1 U42 ( .I1(data[5]), .I2(n40), .O(n38) );
  OR2X1 U43 ( .I1(AC[5]), .I2(n24), .O(n40) );
  OR2X1 U44 ( .I1(n41), .I2(n42), .O(n24) );
  AN2X1 U45 ( .I1(AC[4]), .I2(n21), .O(n42) );
  AN2X1 U46 ( .I1(data[4]), .I2(n43), .O(n41) );
  OR2X1 U47 ( .I1(AC[4]), .I2(n21), .O(n43) );
  OR2X1 U48 ( .I1(n44), .I2(n45), .O(n21) );
  AN2X1 U49 ( .I1(AC[3]), .I2(n18), .O(n45) );
  AN2X1 U50 ( .I1(data[3]), .I2(n46), .O(n44) );
  OR2X1 U51 ( .I1(AC[3]), .I2(n18), .O(n46) );
  OR2X1 U52 ( .I1(n47), .I2(n48), .O(n18) );
  AN2X1 U53 ( .I1(AC[2]), .I2(n15), .O(n48) );
  AN2X1 U54 ( .I1(data[2]), .I2(n49), .O(n47) );
  OR2X1 U55 ( .I1(AC[2]), .I2(n15), .O(n49) );
  OR2X1 U56 ( .I1(n50), .I2(n51), .O(n15) );
  AN2X1 U57 ( .I1(AC[1]), .I2(n12), .O(n51) );
  AN2X1 U58 ( .I1(data[1]), .I2(n52), .O(n50) );
  OR2X1 U59 ( .I1(n12), .I2(AC[1]), .O(n52) );
  AN2X1 U60 ( .I1(AC[0]), .I2(data[0]), .O(n12) );
  HA1X1 U61 ( .A(unit2_counter_0_), .B(n2), .S(unit2_n56) );
  OR2X1 U62 ( .I1(n53), .I2(n54), .O(unit2_n54) );
  AN2X1 U63 ( .I1(unit2_N12), .I2(n2), .O(n54) );
  AN2X1 U64 ( .I1(unit2_counter_1_), .I2(n6), .O(n53) );
  OR2X1 U65 ( .I1(n55), .I2(n56), .O(unit2_n52) );
  AN2X1 U66 ( .I1(unit2_N13), .I2(n2), .O(n56) );
  AN2X1 U67 ( .I1(unit2_counter_2_), .I2(n6), .O(n55) );
  OR2X1 U68 ( .I1(n57), .I2(n58), .O(unit2_n50) );
  AN2X1 U69 ( .I1(unit2_N14), .I2(n2), .O(n58) );
  AN2X1 U70 ( .I1(unit2_counter_3_), .I2(n6), .O(n57) );
  OR2X1 U71 ( .I1(n59), .I2(n60), .O(unit2_n48) );
  AN2X1 U72 ( .I1(unit2_N15), .I2(n2), .O(n60) );
  AN2X1 U73 ( .I1(unit2_counter_4_), .I2(n6), .O(n59) );
  OR2X1 U74 ( .I1(n61), .I2(n62), .O(unit2_n46) );
  AN2X1 U75 ( .I1(unit2_N16), .I2(n2), .O(n62) );
  AN2X1 U76 ( .I1(unit2_counter_5_), .I2(n6), .O(n61) );
  OR2X1 U77 ( .I1(n63), .I2(n64), .O(unit2_n44) );
  AN2X1 U78 ( .I1(unit2_N17), .I2(n2), .O(n64) );
  AN2X1 U79 ( .I1(unit2_counter_6_), .I2(n6), .O(n63) );
  HA1X1 U80 ( .A(unit2_counter_7_), .B(n65), .S(unit2_n42) );
  AN2X1 U81 ( .I1(unit2_add_91_carry[7]), .I2(n2), .O(n65) );
  OR2X1 U82 ( .I1(n66), .I2(done), .O(unit2_n40) );
  AN2X1 U83 ( .I1(n2), .I2(n67), .O(n66) );
  OR2X1 U84 ( .I1(n68), .I2(n69), .O(n67) );
  OR2X1 U85 ( .I1(unit2_counter_4_), .I2(unit2_counter_3_), .O(n69) );
  OR2X1 U86 ( .I1(unit2_counter_5_), .I2(n70), .O(n68) );
  OR2X1 U87 ( .I1(unit2_counter_7_), .I2(unit2_counter_6_), .O(n70) );
  INVX1 U88 ( .I(n6), .O(n2) );
  OR2X1 U89 ( .I1(n71), .I2(n72), .O(n6) );
  OR2X1 U90 ( .I1(done), .I2(ack), .O(n72) );
  HA1X1 U91 ( .A(data[7]), .B(n73), .S(unit1_n34) );
  AN2X1 U92 ( .I1(unit1_add_41_carry[7]), .I2(n74), .O(n73) );
  HA1X1 U93 ( .A(data[0]), .B(n74), .S(unit1_n32) );
  OR2X1 U94 ( .I1(n75), .I2(n76), .O(unit1_n30) );
  AN2X1 U95 ( .I1(unit1_N4), .I2(n74), .O(n76) );
  AN2X1 U96 ( .I1(data[1]), .I2(n77), .O(n75) );
  OR2X1 U97 ( .I1(n78), .I2(n79), .O(unit1_n28) );
  AN2X1 U98 ( .I1(unit1_N5), .I2(n74), .O(n79) );
  AN2X1 U99 ( .I1(data[2]), .I2(n77), .O(n78) );
  OR2X1 U100 ( .I1(n80), .I2(n81), .O(unit1_n26) );
  AN2X1 U101 ( .I1(unit1_N6), .I2(n74), .O(n81) );
  AN2X1 U102 ( .I1(data[3]), .I2(n77), .O(n80) );
  OR2X1 U103 ( .I1(n82), .I2(n83), .O(unit1_n24) );
  AN2X1 U104 ( .I1(unit1_N7), .I2(n74), .O(n83) );
  AN2X1 U105 ( .I1(data[4]), .I2(n77), .O(n82) );
  OR2X1 U106 ( .I1(n84), .I2(n85), .O(unit1_n22) );
  AN2X1 U107 ( .I1(unit1_N8), .I2(n74), .O(n85) );
  AN2X1 U108 ( .I1(data[5]), .I2(n77), .O(n84) );
  OR2X1 U109 ( .I1(n86), .I2(n87), .O(unit1_n20) );
  AN2X1 U110 ( .I1(unit1_N9), .I2(n74), .O(n87) );
  AN2X1 U111 ( .I1(data[6]), .I2(n77), .O(n86) );
  INVX1 U112 ( .I(n74), .O(n77) );
  AN2X1 U113 ( .I1(n88), .I2(n71), .O(n74) );
  INVX1 U114 ( .I(stb), .O(n71) );
  INVX1 U115 ( .I(ack), .O(n88) );
endmodule

