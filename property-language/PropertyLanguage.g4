grammar PropertyLanguage;

// supported operators (in order or precedence):
// ~
// ==, !=
// &
// ^
// |
// |->

property
	: tempExpr EOF
	;

// temporal layer

tempExpr
	: baseExpr
	| EVENTUALLY LPAREN (baseExpr COMMA)? baseExpr RPAREN
	;

// boolean layer

baseExpr
	: implyExpr
	| (ALWAYS|NEVER|ONCE) LPAREN implyExpr RPAREN
	;

implyExpr
	: orExpr ((IMPLY|IMPLY_NEXT) orExpr)?
	;

orExpr
	: xorExpr (OR xorExpr)*
	;

xorExpr
	: andExpr (XOR andExpr)*
	;

andExpr
	: eqExpr (AND eqExpr)*
	| eqExpr (DOUBLE_HASH(NUM)? eqExpr)*
	;

eqExpr
	: timeAtom ( (EQ|NEQ) timeAtom)?
	;

timeAtom
	: ( (HASH|AT) NUM)? nAtom
	;

nAtom
	: NOT? atom
	;

atom
	: ID
	| LPAREN baseExpr RPAREN
	| (ROSE|FELL|STABLE|CHANGED) LPAREN baseExpr RPAREN
	| High
	| Low
	;

// lexer rules

High
	: '1'
	;

Low
	: '0'
	;

ID
	: Simple_identifier
	| Bit_identifier
	| Escaped_identifier
	;

Simple_identifier
	: [a-zA-Z_] [a-zA-Z0-9_$]*
	;

Bit_identifier
	: [a-zA-Z_] [a-zA-Z0-9_$]* '[' [0-9] [0-9]* ']'
	;

Escaped_identifier
	: '\\' ('\u0021'..'\u007E')+ ~ [ \r\t\n]*
	;

AND
	: '&'
	;

OR
	: '|'
	;

XOR
	: '^'
	;

NOT
	: '~'
	;

EQ
	: '=='
	;

NEQ
	: '!='
	;

IMPLY
	: '|->'
	;

IMPLY_NEXT
	: '|=>'
	;

LPAREN
	: '('
	;

RPAREN
	: ')'
	;

HASH
	: '#'
	;

DOUBLE_HASH
	: '##'
	;

AT
	: '@'
	;

ROSE
	: '$rose'
	;

FELL
	: '$fell'
	;

STABLE
	: '$stable'
	;

CHANGED
	: '$changed'
	;

ALWAYS
	: '$always'
	;

NEVER
	: '$never'
	;

ONCE
	: '$once'
	;

EVENTUALLY
	: '$eventually'
	;

NUM
	: [0-9] +
	;

COMMA
	: ','
	;

WS
	: [ \r\n\t] + -> channel (HIDDEN)
	;
