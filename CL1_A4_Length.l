%{
	#include<stdio.h>
	int leng = 0;
	char st[500];
%}

%%
[A-z]+	{
		int i = 0;
		if(yyleng>leng)
		{
			leng = yyleng;
			while(i<yyleng)
			{
				st[i] = yytext[i];
				i++;

			}
		}
	}


%%

void main(int argc,char** argv)
{
	yyin = fopen(argv[1],"r");

	yylex();

}
int yywrap()
{
	printf("Length %d\n",leng);

	printf("String %s\n",st);

}
