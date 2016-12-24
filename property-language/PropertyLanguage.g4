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
	| eqExpr (DOUBLE_HASH(INTEGER)? eqExpr)*
	;

eqExpr
	: timeAtom ( (EQ|NEQ|LT|GT|LE|GE) timeAtom)?
	;

timeAtom
	: ( (HASH|AT) INTEGER)? nAtom
	;

nAtom
	: NOT? atom
	;

atom
	: ID
	| LPAREN baseExpr RPAREN
	| (ROSE|FELL|STABLE|CHANGED) LPAREN baseExpr RPAREN
	| (ALWAYS|NEVER|ONCE|ANY|ALL) LPAREN baseExpr RPAREN
	| (UNTIL|WHEN) LPAREN baseExpr COMMA baseExpr RPAREN
	| INTEGER
	;

// lexer rules

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

LT
	: '<'
	;

GT
	: '>'
	;

LE
	: '<='
	;

GE
	: '>='
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

UNTIL
	: '$until'
	;

WHEN
	: '$when'
	;

ANY
	: '$any'
	;

ALL
	: '$all'
	;

EVENTUALLY
	: '$eventually'
	;

INTEGER
	: [0-9] +
	;

COMMA
	: ','
	;

WS
	: [ \r\n\t] + -> channel (HIDDEN)
	;
