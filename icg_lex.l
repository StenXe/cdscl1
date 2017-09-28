%{
#include<stdio.h>
#include<string.h>
#include"y.tab.h"
#include<math.h>
%}
%%
[/t]+ ;
if { strcpy(yylval.cval,yytext);return IF;}
[{] { strcpy(yylval.cval,yytext);return BLST;}
[}] { strcpy(yylval.cval,yytext);return BLEND;}
[0-9]+ {yylval.dval=atoi(yytext); return NUM;}
[a-z]+ {strcpy(yylval.cval,yytext);return ID;}
"+"|"-"|"="|"*"|"/" {return *yytext;}
"("|")" {return *yytext;}
"<"|">" {strcpy(yylval.cval,yytext);return RELOP;}
\n {return *yytext;}
%%
yywrap()
{
	
}
