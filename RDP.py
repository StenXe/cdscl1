inpstr = "a+a*a"
i = 0
error = 0
def E():
    print "E-->TE'"
    T()
    Ep()

def T():
    print "T-->FT'"
    F()
    Tp()

def Ep():
    global i
    if(i<len(inpstr)):
        if(inpstr[i]=="+"):
            print "E'-->+TE'"
            i += 1
            T()
            Ep()
        else:
            print "E'-->",u'\u03b5'
            
def Tp():
    global i
    if(i<len(inpstr)):
        if(inpstr[i]=="*"):
            print "T'-->*FT'"
            i += 1
            F();
            Tp();
        else:
            
            print "T'-->",u'\u03b5'

def F():
    global i
    if(inpstr[i].isalpha() or inpstr[i].isdigit()):
        print "F-->id"
        i += 1
    elif(inpstr[i]=="("):
        print "F-->(E)"
        i += 1
        E()
        if(inpstr[i]==")"):
            i += 1
    else:
        error = 1

E()
if(len(inpstr)==i and error==0):
    print "Accepted"
else:
    print "Rejected"
