items_b = [100,40,95,50,30]
items_w = [1.98,2,5,3.14,3]
nodesList = []
CAP = 10

class node:
    def __init__(self):
        self.b = 0
        self.w = 0
        self.ub = 0
        self.item = 0
        self.path = []
        self.left = None
        self.right = None
        
def upperBound(item,b,w):
    
    WtR = CAP - w
    BenT = b
    WtT = w
    for i in range(item,len(items_b)):
        if(WtT >= CAP):
            break
        frac = WtR/float(items_w[i])
        if(frac > 1):
            frac = 1

        WtT += frac * items_w[i]
        BenT += frac * items_b[i]
        WtR -= frac * items_w[i]
    
    return BenT

def createNodes():
    
    if len(nodesList)==0:
        n = node()
        n.ub = upperBound(n.item,n.b,n.w)
        nodesList.append(n)
    
    n = nodesList.pop(0)

    if(n.item>=len(items_b)):
        print "Item Selection ",n.path
        print "Maximum Benefit ",n.ub
        return
    
    n.left = node()
    n.left.item = n.item + 1
    n.left.b = n.b + items_b[n.item]
    n.left.w = n.w + items_w[n.item]
    n.left.ub = upperBound(n.left.item,n.left.b,n.left.w)
    n.left.path += n.path
    n.left.path += [1]
    
    n.right = node()
    n.right.item = n.item + 1
    n.right.b = n.b
    n.right.w = n.w
    n.right.ub = upperBound(n.right.item,n.b,n.w)
    n.right.path += n.path
    n.right.path += [0]
    
    if(n.left.w<=CAP):
        nodesList.append(n.left)
    nodesList.append(n.right)

    #print "B ",n.b," W ",n.w
    #print "Path ",n.path
    
    
    nodesList.sort(key=lambda n:n.ub,reverse=True)

    #for i in range(len(nodesList)):
    #    print "NodesList ",nodesList[i].ub
    createNodes()

createNodes()

