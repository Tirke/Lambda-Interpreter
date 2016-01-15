grammar Lambda;


expression          :   LPAR expression RPAR            #parenExpression
                    |   VAR                             #variable
                    |   INT                             #integer
                    |   expression MULT expression      #mult
                    |   expression op=(PLUS|MINUS) expression       #add
                    |   IF LPAR expression op=(EQ|NEQ|GT|LT|GTEQ|LTEQ) expression RPAR THEN LCURL expression RCURL ELSE LCURL expression RCURL     #ifRule
                    |   expression ' ' expression       #application
                    |   LAMBDA VAR DOT expression        #abstraction
                    |   REC ' ' VAR ' ' VAR DOT expression   #recRule
                    ;

RPAR        :   ')';
LPAR        :   '(';
RCURL       :   '}';
LCURL       :   '{';

PLUS        :   '+';
MINUS       :   '-';
MULT        :   '*';
DOT         :   '.';


EQ          :   '=';
NEQ         :   '!=';
GT          :   '>';
LT          :   '<';
GTEQ        :   '>=';
LTEQ        :   '<=';

IF          :   'if';
THEN        :   'then';
ELSE        :   'else';
REC         :   'rec';

VAR         :   [a-zA-Z][a-zA-Z0-9]*;
INT         :   [0-9]+;

LAMBDA      :   'λ' | '\\' ;
WS          :   [ \t\n]+ -> skip ;
