%{
	#include<stdio.h>
	#include<math.h>
	extern void printSymbole();
	typedef struct  symbolTable
	{
		char name[10];
		double value;	
	}SymbolTable;
	SymbolTable ST[50];
%}
%union
{
	double p;
}
%token <p> NUM 
%token <p> IDENTIFIER
%token  SIN COS TAN SQRT
%left '+' '-'
%left '*' '/'
%type <p> E 


%%
Edash:E';'{printf("\n=%f",$1); printSymbole();}
	|Edash E';'{printf("\n=%f",$2);printSymbole();}

E: 	E'+'E 	{$$=$1+$3;}
	|E'-'E 	{$$=$1-$3;}
	|E'*'E 	{$$=$1*$3;}
	|E'/'E 	{$$=$1/$3;}
	|NUM	{$$=$1;}
	|IDENTIFIER	{$$=ST[(int)$1].value;}
	|'('E')'	{$$=$2;}
	|IDENTIFIER'='E {$$ = ST[(int)$1].value=$3;}
	|SIN E		{$$ = sin($2*3.141/180);}
	|COS E		{$$ = cos($2*3.141/180);}
	|TAN E		{$$ = tan($2*3.141/180);}
	|SQRT E		{$$ = sqrt($2);}
	;
%%

int main(void)
{
	yyparse();
	return 0;
}

yyerror()
{
	printf("Error found");
}
