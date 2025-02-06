import java_cup.runtime.*;
%%

%implements java_cup.runtime.Scanner
%type Symbol
%function next_token
%class A3Scanner
%eofval{ return null;
%eofval}

IDENTIFIER = [a-zA-Z_][a-zA-Z0-9_]*
NUMBER = [0-9]+
%%
"+" { return new Symbol(A3Symbol.PLUS); }
"-" { return new Symbol(A3Symbol.MINUS); }
"*" { return new Symbol(A3Symbol.TIMES); }
"/" { return new Symbol(A3Symbol.DIVIDE); }
{NUMBER} { return new Symbol(A3Symbol.NUMBER, Integer.valueOf(yytext()));}  
\r|\n {}
. {}