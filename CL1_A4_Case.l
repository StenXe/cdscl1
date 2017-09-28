%{
	#include<stdio.h>
	#include<ctype.h>
	#include<string.h>
	int ch;
%}

%%
[A-z.]+ {
	switch(ch)
	{
		int i = 0;
		char st[100];
		case 1:
			i=0;
			while(i<yyleng)
			{
				printf("%c",toupper(yytext[i])); 
				i++;
	
			}
			break;
		case 2:
			i=0;
			while(i<yyleng)
			{
				printf("%c",tolower(yytext[i])); 
				i++;
		
			}
			break;
		case 3:
			
			i = 0;
			while(i<yyleng)
			{
				if(ispunct(yytext[i]))
				{
					printf("%c",yytext[i]);
					if(yytext[i+1]!='\0')
					{
						printf("%c",toupper(yytext[i+1]));
						i++;
					}
				}
				else
				{
					if(i==0)
						printf("%c",toupper(yytext[i]));
					else
						printf("%c",yytext[i]);
				}
			i++;
			}
			break;
		case 4:
			i=0;
			while(i<yyleng)
			{
				isupper(yytext[i])?printf("%c",tolower(yytext[i])):printf("%c",toupper(yytext[i]));
				i++;
			}
			break;
		default:
			printf("%s",yytext);
	}
       }

%%

void main()
{
	printf("Select:\n1.Uppercase\n2.Lowercase\n3.Sentence\n4.Toggle\n");
	scanf("%d",&ch);
	
	yylex();
	
}
