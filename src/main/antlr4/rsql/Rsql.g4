grammar Rsql;

expr: (SELECTOR COMPARISONOP ARGUMENTS (OP expr)*) EOF;


OP : '&&'|'||';

fragment ESC
   : '\\' (["\\/bfnrt] | [a-zA-Z])
   ;

SELECTOR: 'name' | 'description';
ALLCHARS:   [a-zA-Z_] [a-zA-Z]*;
ESCAPED    : '\\' | ALLCHARS;


COMPARISONOP : COMPFIQL | COMPALT;
ALPHA : 'le' | 'ge' | 'lt' | 'gt';
COMPFIQL     : '==' | '!=' | '=' ALPHA '=';
COMPALT      : ( '>' | '<' )  [ '=' ];
ARGUMENTS    : VALUE | ( '(' (VALUE | ',' VALUE) ')' );
VALUE        : ('"' | '\'') (ESC | ~ ["\\])* ('"' | '\'')
             ;
WS: [ \n\t\r] ->skip;