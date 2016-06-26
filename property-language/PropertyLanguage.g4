grammar PropertyLanguage;

document
	: property* EOF
	;

property
	: expression ';'
	;

expression
	: identifier
	| identifier '|->' identifier
	;

identifier
   : Simple_identifier
   | Escaped_identifier
   ;

Simple_identifier
   : [a-zA-Z_] [a-zA-Z0-9_$]*
   ;

Escaped_identifier
   : '\\' ('\u0021'..'\u007E')+ ~ [ \r\t\n]*
   ;

WS
	: [ \r\n\t] + -> channel (HIDDEN)
	;