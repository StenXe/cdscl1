%{
	#include<stdio.h>
	#include<stdlib.h>
	#include "y.tab.h"
	#include<math.h>
	#include<string.h>
	typedef struct  symbolTable
	{
		char name[10];
		double value;	
	}SymbolTable;
	SymbolTable ST[50];
	int STTop=-1;
	void printSymbole();
%}



%%
sin			{
				return(SIN);
			};
cos			{
				return(COS);
			};
tan			{
				return(TAN);
			};
sqrt			{
				return(SQRT);
			};
[a-zA-Z]+	 	{
				int found=searchSymbol(yytext);
				if(found==-1)
				{
					strcpy(ST[++STTop].name,yytext);
					yylval.p=STTop;
				}
				else
				{
					yylval.p=found;
				}
				return IDENTIFIER;

			};
[0-9]+			{
			yylval.p=atof(yytext);				
				return (NUM);
			}
[0-9]+\.[0-9]*		{
				yylval.p=atof(yytext);				
				return (NUM);
			};
[=+*;/\(\)\-]		{
				return (yytext[0]);
			};
[ \t\n]+		;
.			{printf("Lexical error");}
%%

int searchSymbol(char searchName[])
{
	if(STTop==-1)
		return -1;
	int i;		
	for(i=0;i<=STTop;i++)
	{
		if(strcmp(ST[i].name,searchName)==0)
			return i;
	}
	return -1;
}
void printSymbole()
{
	int i=0;
	printf("\n\n=========Printing Symbol table========");
	for(i=0;i<=STTop;i++)
	{
		printf("\n[%s] = [%lf] ",ST[i].name,ST[i].value);
	
	}
	printf("\n=========Symbol table completed========\n");
}
