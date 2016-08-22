grammar Rsql;

expr: SELECTOR COMPARISONOP ARGUMENTS;

SELECTOR: 'name' | 'description';
ALLCHARS:   [a-zA-Z]+;
ESCAPED    : '\\' | ALLCHARS;

COMPARISONOP : COMPFIQL | COMPALT;
ALPHA : 'le' | 'ge' | 'lt' | 'gt';
COMPFIQL     : '==' | '!=' | '=' ALPHA '=';
COMPALT      : ( '>' | '<' )  [ '=' ];
ARGUMENTS    : VALUE | ( '(' (VALUE | ',' VALUE) ')' );
VALUE          : ALLCHARS;
WS: [ \t\r\n]+ -> skip;