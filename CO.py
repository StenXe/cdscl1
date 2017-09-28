three_addr_code1 = ["t1=2*3","t2=t1+b","t3=t2+c","t4=t1+2"]
three_addr_code2 = ["t1=a+b","t2=a*b","t3=a+b","t4=c/d","t5=b*a","t6=d/c","t7=c/d"]
constExp = []
def constExpEval():

    size = len(three_addr_code1)
    for i in range(0,size):
        stmt = three_addr_code1[i]
        print stmt
        j = 0
        while(j<len(constExp)):
            if(constExp[j][0] in stmt):
                stmt = stmt.replace(str(constExp[j][0]),str(constExp[j][1]))
            j += 1
        stmt = stmt.split("=")

        if(stmt[1][0].isdigit() and stmt[1][2].isdigit()):
            constExp.append([stmt[0],eval(stmt[1])])
            res = eval(stmt[1])
            stmt = stmt[0] +"="+ str(res)
        else:
        	stmt = stmt[0] +"="+ stmt[1]

        three_addr_code1.pop(i)
        three_addr_code1.insert(i,stmt)

def comSubExpElim():
	for i in range(0,len(three_addr_code2)):
		stmt = three_addr_code2[i]
		stmt = stmt.split("=")
		for j in range(i+1,len(three_addr_code2)):
			thrstmt = three_addr_code2[j].split("=")
			tmp = thrstmt[1]
			if(stmt[1]==tmp or (stmt[1]==tmp[::-1] and "/" not in tmp[::-1]) and (stmt[1]==tmp[::-1] and "-" not in tmp[::-1])):
				thrstmt = thrstmt[0] + "=" + stmt[0]
			else:
				thrstmt = thrstmt[0] + "=" + thrstmt[1]
			three_addr_code2.pop(j)
			three_addr_code2.insert(j,thrstmt)

print "Intermediate Code is "
constExpEval()
print "Optimized 3-Address code using Constant Expression Evaluation is "
for i in range(0,len(three_addr_code1)):
	print three_addr_code1[i]
	
print "Intermediate Code is"
for i in range(0,len(three_addr_code2)):
	print three_addr_code2[i]
comSubExpElim()
print "Optimized 3-Address code using Common Sub-Expression Elimination is "
for i in range(0,len(three_addr_code2)):
	print three_addr_code2[i]

