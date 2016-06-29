grammar PropertyLanguage;

property
	: identifier
	| identifier '|->' identifier
	;

identifier
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

WS
	: [ \r\n\t] + -> channel (HIDDEN)
	;