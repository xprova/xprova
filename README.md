## Xprova

This is an EDA tool for clock domain crossing verification, currently at an
early state of development and not yet available for use (please wait for a
release). More information on the tool is available on <https://xprova.net>.

### Requirement

* java 8
* Maven
* Yosys

### Usage:

#### 1. Library and Flip-Flop Definitions

Until I document the various functions and capabilities of the tool properly I
will list the commands currently supported here with brief explanations
following cookbook principles.

Xprova is a command line tool, when launched it should print the following
banner and prompt:

```
 __  __
 \ \/ /_ __  _ __ _____   ____ _
  \  /| '_ \| '__/ _ \ \ / / _` |
  /  \| |_) | | | (_) \ V / (_| |
 /_/\_\ .__/|_|  \___/ \_/ \__,_|
      |_|


Type :l for a list of available commands or :q to quit

>>
```

The first step is to always load a gate library by running `load_lib
file.lib`. The library must be a verilog file containing module headers and
input/output statements (to specify port directions), for example:

```Verilog
module AND (y, a, b);
	input a, b;
	output y;
endmodule

module NOT (y, a);
	input a;
	output y;
endmodule
```

Library modules may not instantiate other modules.

The tool must also be told which modules in the library are flip-flops
(currently there is no automated way of inferring this). Flip-flop modules are
defined by running:

```
def_ff QDFFRSBX1 CK RS D
```

The first argument of `def_ff` is the flip-flop module name and the rest are
clock port, reset port and data port respectively.

To list existing flip-flop definitions run `list_ff` and to clear them run
`clear_ff`.

#### 2. Loading Designs

To load a verilog netlist, run:

```
read_verilog design.v
```

If the file contains multiple modules then the first will be loaded and the
rest ignored. If you'd like to specify which module to load use:

```
read_verilog -m top design.v
```

Note: the tool supports flattened netlists only atm. Instantiations of modules
that are not defined in the library will cause an Exception to be thrown.

#### 3. Augmenting Designs

The command to do this is `augment_netlist`.

#### 4. Writing (Augmented) Designs

Simply run `write_verilog augmented.v`

#### 5. Generating Dot Files

The tool can output various graph representations of the netlist using the
command `export_dot`.

```
export_dot output.dot

# to create a graph of nets and gates only (ignore flip-flops):
export_dot --type=ng output.dot

# to create a graph of gates and flops only (ignore nets):
export_dot --type=gf output.dot

# to create a graph of flip flops only:
export_dot --type=f output.dot

# so basically the option --type can take any char combination of "ngf"

# to omit verticess (useful to remove reset and clock connections):
export_dot --ignore-vertices=rst,clk1,clk2 output.dot

# to omit edges (ditto):
export_dot --ignore-edges=SB,RB,CK

# to combine multiple vertices into one:
export_dot --combine=add[0],add[1],add[2] output.dot

```
