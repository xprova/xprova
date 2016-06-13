
grammar PropertyLanguage;

document : assertion* ;

assertion : 'assert' ID ;

ID : [a-z]+ ;

WS : [ \r\n\t] + -> channel (HIDDEN) ;