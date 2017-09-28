%{

#include <stdio.h>
int flag=0;
%}
%token FOR OPBR CLBR SEMIC EQU ID NUM RELOP INC DEC
%%
S:FOR OPBR E1 SEMIC E2 SEMIC E3 CLBR
{printf("Accepted!\n");flag=1;}
;
E1: ID EQU ID
|   ID EQU NUM
;
E2: ID RELOP ID
|   ID RELOP NUM
;
E3: ID INC
|   ID DEC
;

%%
main()
{
yyparse();
}
yyerror(const char *msg)
{
if(flag==0);
printf("Not Accepted!\n");
printf("%s\n",msg);
}
