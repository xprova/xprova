grammar PropertyLanguage;

// supported operators (in order or precedence):
// ~
// ==, !=
// &
// ^
// |
// |->

property
	: expr EOF
	;

expr
	: implyExp
	;

implyExp
	: orExpr (IMPLY orExpr)?
	;

orExpr
	: xorExpr (OR xorExpr)*
	;

xorExpr
	: andExpr (XOR andExpr)*
	;

andExpr
	: eqExpr (AND eqExpr)*
	;

eqExpr
	: nAtom ( (EQ|NEQ) nAtom)?
	;

nAtom
	: NOT? atom
	;

atom
	: ID
	| LPAREN expr RPAREN
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

IMPLY
	: '|->'
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

WS
	: [ \r\n\t] + -> channel (HIDDEN)
	;
