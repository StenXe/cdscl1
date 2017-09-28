%{
#include<stdio.h>
int maxLength = 0;
char LongString[500];

%}

%%

[A-z]+		{
			printf("String \n");
			if(yyleng>maxLength)
			{
				maxLength = yyleng;
				strcpy(yytext,LongString);
			}
		}
.		{
			printf("Not a string\n");
		}
%%

void main()
{
	
	yylex();
}
int yywrap()
{
	printf("Longest String: %s\n",LongString);
	printf("Length: %d\n",maxLength);
	
	return 0;
}
