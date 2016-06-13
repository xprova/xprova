grammar PropertyLanguage;

document
	: assertion* EOF
	;

assertion
	: 'assert' expression ';'
	;

expression
	: ID
	| expression operator expression
	| '(' expression ')'
	;

operator
	: '+'
	| '-'
	| '*'
	| '/'
	;

ID
	: [a-z]+
	;

WS
	: [ \r\n\t] + -> channel (HIDDEN)
	;