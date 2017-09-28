import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

class Node{
	int b, w, ub;
	int item;
	Node left, right;
	ArrayList<Integer> path;

	public Node(){
		b = 0;
		w = 0;
		ub = 0;
		item = 0;
		path = new ArrayList<Integer>(BNB.TotalItems);
		left = null;
		right = null;
	}

	

}

class TreeBuild{
	
	void createNodes(ArrayList Nodes){
		if(Nodes.size()==0){
			Node n = new Node();
			n.ub = upperBound(n.item, n.b, n.w);
			Nodes.add(n);
		}

		Node n = (Node) Nodes.remove(0);

		if(n.item>=BNB.TotalItems){
			System.out.print("Item Selection ");
			for(int i=0;i<n.path.size();i++)
				System.out.print(n.path.get(i));
			System.out.println("\nMaximum Benefit "+n.ub);
			return;
		}

		System.out.println("Item "+n.item);
		n.left = new Node();
		n.left.item = n.item + 1;
		n.left.b = n.b + BNB.items_b[n.item];
		n.left.w = n.w + BNB.items_w[n.item];
		n.left.ub = upperBound(n.left.item, n.left.b, n.left.w);
		//System.out.println("Path "+n.left.path[n.item]);
		n.left.path.addAll(n.path);
		n.left.path.add(1);

		n.right = new Node();
		n.right.item = n.item + 1;
		n.right.b = n.b;
		n.right.w = n.w;
		n.right.ub = upperBound(n.right.item, n.right.b, n.right.w);
		n.right.path.addAll(n.path);
		n.right.path.add(0);

		if(n.left.w<=BNB.CAP){
			Nodes.add(n.left);
		}
		Nodes.add(n.right);

		Collections.sort(Nodes,new Comparator<Node>(){
		@Override
		public int compare(Node n1, Node n2){
			return n2.ub - n1.ub;
		}
		});
 
		
		/*Iterator itr = Nodes.iterator();

		while(itr.hasNext()){
			Node s = (Node) itr.next();
			
			System.out.println(s.ub);
		}*/

		createNodes(Nodes);
		
	}

	int upperBound(int item, int b, int w){
		int WtR = BNB.CAP - w;
		int BenT = b;
		int WtT = w;

		for(int i=item;i<BNB.TotalItems;i++){
			if(WtT >= BNB.CAP)
				break;
			float frac = WtR/(float)BNB.items_w[i];
			if(frac > 1)
				frac = 1;

			WtT += frac * BNB.items_w[i];
			BenT += frac * BNB.items_b[i];
			WtR -= frac * BNB.items_w[i];
		}
		
		return BenT;

	}

}

public class BNB{

	public static final int TotalItems = 4;
	public static final int CAP = 10;
	public static int []items_b = {45,30,45,10};
	public static int []items_w = {3,5,9,5};
	static ArrayList<Node> Nodes = new ArrayList<Node>();

	public static void main(String[] args){
	//System.out.println("Hello World!");

	TreeBuild tb = new TreeBuild();
	tb.createNodes(Nodes);
	}

}
