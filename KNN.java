import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;

class DatInst{

	double col1, col2, col3, col4; 
	double Distance;
	String cname;
	
	DatInst(double col1, double col2, double col3, double col4, String cname){
	
		this.col1 = col1;
		this.col2 = col2;
		this.col3 = col3;
		this.col4 = col4;
		this.cname = cname;
		Distance = 0;
		
	}
}

class CSVReader{

	String[] data = null;
	String line = "";
	String CSVfile = "iris.data";
	BufferedReader br = null;
	void createData(ArrayList Data){
	try{
		br = new BufferedReader(new FileReader(CSVfile));
		while((line = br.readLine()) != null){
	
			data = line.split(",");
			DatInst d = new DatInst(Double.parseDouble(data[0]),Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3]),data[4]);
			Data.add(d);
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	}
}

class KnnDist{
	void calculateDist(ArrayList Data, DatInst DtTest, int k){
		Iterator itr = Data.iterator();
		
		while(itr.hasNext()){
		
			DatInst s = (DatInst) itr.next();
			
			double euclidDist = Math.sqrt(Math.pow((s.col1-DtTest.col1),2)+Math.pow((s.col2-DtTest.col2),2)+Math.pow((s.col3-DtTest.col3),2)+Math.pow((s.col4-DtTest.col4),2));
			s.Distance = euclidDist;
			//System.out.println("DatInst "+s.name+" "+euclidDist);
		}
		
		Collections.sort(Data, new Comparator<DatInst>(){
			public int compare(DatInst d1, DatInst d2){
				return Double.compare(d1.Distance, d2.Distance);
			}
		
		});
		
		itr = Data.iterator();
		int Count1 = 0, Count2 = 0, Count3 = 0;
		
		while(itr.hasNext()){
			if(k--<=0)
				break;
			DatInst s = (DatInst) itr.next();
			System.out.println("DatInst "+s.cname);
			String Compl = s.cname;
			if(Compl.equals("Iris-setosa"))
				Count1++;
			else if(Compl.equals("Iris-versicolor"))
				Count2++;
			else
				Count3++;
		}
		System.out.println("Count1 "+Count1+" Count2 "+Count2+" Count3 "+Count3);
		System.out.println("Test Sample is classified as ");
		if(Count1>Count2 && Count1>Count3)
			System.out.println("Iris-setosa");
		else if(Count2>Count1 && Count2>Count3)
			System.out.println("Iris-versicolor");
		else
			System.out.println("Iris-virginica");
	
	}

}

public class KNN{

	public static void main(String[] args){
		
		ArrayList<DatInst> Data = new ArrayList<DatInst>();
		/*
		DatInst st1 = new DatInst("Rucha",88,50,"Yes");
		DatInst st2 = new DatInst("Mayur",87,54,"No");
		DatInst st3 = new DatInst("Nilesh",90,56,"No");
		DatInst st4 = new DatInst("Devika",90,55,"Yes");
		
		Data.add(st1);
		Data.add(st2);
		Data.add(st3);
		Data.add(st4);
		
		DatInst DtTest = new DatInst("Dushyant",88,45,"");
		
		KnnDist knd = new KnnDist();
		knd.CalculateDist(Data, DtTest, 3);
		*/
		
		CSVReader cvr = new CSVReader();
		cvr.createData(Data);

		DatInst DtTest = new DatInst(7.4,2.8,6.1,1.9,"");

		KnnDist knd = new KnnDist();
		knd.calculateDist(Data, DtTest, 25);
	}
}
