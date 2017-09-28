%{
#include<stdio.h>
#include<math.h>
#include<string.h>
struct quad
{
char op[10];
char arg1[10];
char arg2[10];
char res[10];
} quad[20];
int blk_cnt=1,cnt=1;
char arr[10],str[10],temp[10];
struct block
{
int st,end,if_flag;
} blk[20];
%}
%union
{
char cval[10];
int dval;
}
%token <cval> RELOP ID IF BLST BLEND
%token <dval> NUM
%type <cval> A
%left '-' '+'
%left '*' '/'
%left RELOP
%%
S : B '(' E ')' S1 '\n' {print();}
;
B : IF {blk[blk_cnt].if_flag=1;}
;
E : ID RELOP ID {strcpy(quad[cnt].op,$2);
strcpy(quad[cnt].arg1,$1);
strcpy(quad[cnt].arg2,$3);
arr[0]= 'a';
arr[1]='\0';
strcpy(str,"t");
strcat(str,arr);
strcpy(quad[cnt].res,str);
cnt++;
}
;
S1 : BLST1 AS BLEND1
;
AS : AS1
;
AS1 : ID '=' A { strcpy(quad[cnt].op,"=");
strcpy(quad[cnt].arg1,$3);
strcpy(quad[cnt].arg2,"-");
strcpy(quad[cnt].res,$1);
cnt++;
}
;
A : ID {strcpy($$,$1);}
| NUM {sprintf(temp,"%d",$1); strcpy($$,temp);}
| A '+' A { strcpy(quad[cnt].op,"+");
strcpy(quad[cnt].arg1,$1);
strcpy(quad[cnt].arg2,$3);
arr[0]='b';
arr[1]='\0';
strcpy(str,"t");
strcat(str,arr);
strcpy(quad[cnt].res,str);
cnt++;
strcpy($$,str);
}
;
BLST1 : BLST { if(blk[blk_cnt].if_flag==1)
{
blk[blk_cnt].st=cnt;
strcpy(quad[cnt].op,"if");
strcpy(str,"t");
strcat(str,arr);
strcpy(quad[cnt].arg1,str);
strcpy(quad[cnt].arg2,"-");
temp[0]=cnt+'2';
temp[1]='\0';
strcpy(quad[cnt].res,temp);
cnt++;
strcpy(quad[cnt].op,"goto");
strcpy(quad[cnt].arg1,"-");
strcpy(quad[cnt].arg2,"-");
cnt++;
}
}
;
BLEND1 : BLEND { if(blk[blk_cnt].if_flag==1)
{
blk[blk_cnt].if_flag=0;
blk[blk_cnt].end=cnt;
temp[0]=cnt+'0';
temp[1]='\0';
strcpy(quad[blk[blk_cnt].st+1].res,temp);
}
blk_cnt++;
}
;
%%
main()
{
yyparse();
}
yyerror()
{
printf("ERROR");
return 1;
}
print()
{
int i;
printf("\nS.No\tOpr\tArg1\tArg2\tResult\n");
printf("===============================================\n");
for(i=1;i<cnt;i++)
{
printf("%d\t%s\t%s\t%s\t%s\n",i,quad[i].op,quad[i].arg1,
quad[i].arg2,quad[i].res);
}
printf("%d\n",i);
}
