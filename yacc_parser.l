%{
#include "y.tab.h"
extern int yylval;
%}
%%
for {return (FOR);}
"(" {return (OPBR);}
")" {return (CLBR);}
";" {return (SEMIC);}
"=" {return (EQU);}
"<"|">" {return (RELOP);}
"++" {return (INC);}
"--" {return (DEC);}
[a-zA-Z]+ {yylval=yytext[0];return(ID);}
[0-9]+ {yylval=atoi(yytext);return(NUM);}
%%
yywrap()
{
return 1;
}
