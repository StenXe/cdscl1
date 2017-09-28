import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

class Employee{
	String work, qual;
	int age;
	float exp;

	Employee(String work, int age, String qual, float exp){
		this.work = work;
		this.age = age;
		this.qual = qual;
		this.exp = exp;

	}

}

class CalculateProb implements Runnable{
	private ArrayList EmpData;
	private Employee eTest;

	CalculateProb(ArrayList EmpData, Employee eTest){

		this.EmpData = (ArrayList) EmpData.clone();
		this.eTest = eTest;
	}


	public synchronized void run(){
		int noSamples = EmpData.size();
		float pCon = 0, pRes = 0, pJob = 0;
		float pConBT = 0, pConMT = 0, pConPhD = 0;
		float pResBT = 0, pResMT = 0, pResPhD = 0;
		float pJobBT = 0, pJobMT = 0, pJobPhD = 0;
		float conAgeMean = 0, resAgeMean = 0, jobAgeMean = 0;
		float conExpMean = 0, resExpMean = 0, jobExpMean = 0;
		

		Iterator itr =  EmpData.iterator();
		while(itr.hasNext()){

			Employee ed = (Employee) itr.next();
			if(ed.work.equals("Consultancy")){
				if(ed.qual.equals("BTech"))
					pConBT++;
				else if(ed.qual.equals("MTech"))
					pConMT++;
				else if(ed.qual.equals("PhD"))
					pConPhD++;
				pCon++;
				conAgeMean += ed.age;
				conExpMean += ed.exp;
			}
			else if(ed.work.equals("Research")){
				if(ed.qual.equals("BTech"))
					pResBT++; 
				else if(ed.qual.equals("MTech"))
					pResMT++;
				else if(ed.qual.equals("PhD"))
					pResPhD++;
				pRes++;
				resAgeMean += ed.age;
				resExpMean += ed.exp;
			}
			else if(ed.work.equals("Job")){
				if(ed.qual.equals("BTech"))
					pJobBT++; 
				else if(ed.qual.equals("MTech"))
					pJobMT++;
				else if(ed.qual.equals("PhD"))
					pJobPhD++;
				pJob++;
				jobAgeMean += ed.age;
				jobExpMean += ed.exp;
			}
		}

		conAgeMean /= pCon;
		resAgeMean /= pRes;
		jobAgeMean /= pJob;

		conExpMean /= pCon;
		resExpMean /= pRes;
		jobExpMean /= pJob;

		System.out.println("Total Samples : "+noSamples);
		System.out.println("Consultancy : "+pCon);
		System.out.println("Research : "+pRes);
		System.out.println("Job : "+pJob);
		System.out.println("Consultancy BTech : "+pConBT);
		System.out.println("Consultancy MTech : "+pConMT);
		System.out.println("Consultancy PhD : "+pConPhD);
		System.out.println("Research BTech : "+pResBT);
		System.out.println("Research MTech : "+pResMT);
		System.out.println("Research PhD : "+pResPhD);
		System.out.println("Job BTech : "+pJobBT);
		System.out.println("Job MTech : "+pJobMT);
		System.out.println("Job PhD : "+pJobPhD);
		System.out.println("Consultancy Age Mean : "+conAgeMean);
		System.out.println("Research Age Mean : "+resAgeMean);
		System.out.println("Job Age Mean : "+jobAgeMean);
		System.out.println("Consultancy Exp Mean : "+conExpMean);
		System.out.println("Research Exp Mean : "+resExpMean);
		System.out.println("Job Exp Mean : "+jobExpMean);


		double conAgeSDev = 0,resAgeSDev = 0, jobAgeSDev = 0;
		double conExpSDev = 0,resExpSDev = 0, jobExpSDev = 0;
		int c = 0, r = 0, j = 0; 

		itr = EmpData.iterator();

		while(itr.hasNext()){

			Employee ed = (Employee) itr.next();

			if(ed.work.equals("Consultancy")){

				conAgeSDev += Math.pow((ed.age - conAgeMean),2);
				conExpSDev += Math.pow((ed.exp - conExpMean),2);
				c++;
			}
			else if(ed.work.equals("Research")){

				resAgeSDev += Math.pow((ed.age - resAgeMean),2);
				resExpSDev += Math.pow((ed.exp - resExpMean),2);
				r++;
			}
			else if(ed.work.equals("Job")){

				jobAgeSDev += Math.pow((ed.age - jobAgeMean),2);
				jobExpSDev += Math.pow((ed.exp - jobExpMean),2);
				j++;
			}
		}

		

		conAgeSDev = Math.sqrt(conAgeSDev/(c-1));
		conExpSDev = Math.sqrt(conExpSDev/(c-1));
		resAgeSDev = Math.sqrt(resAgeSDev/(r-1));
		resExpSDev = Math.sqrt(resExpSDev/(r-1));
		jobAgeSDev = Math.sqrt(jobAgeSDev/(j-1));
		jobExpSDev = Math.sqrt(jobExpSDev/(j-1));


		System.out.println("Consultancy Age SD : "+conAgeSDev);
		System.out.println("Research Age SD : "+resAgeSDev);
		System.out.println("Job Age SD : "+jobAgeSDev);
		System.out.println("Consultancy Exp SD : "+conExpSDev);
		System.out.println("Research Exp SD : "+resExpSDev);
		System.out.println("Job Exp SD : "+jobExpSDev);

		double gDistAgeCon = 0, gDistAgeRes = 0, gDistAgeJob = 0;
		double gDistExpCon = 0, gDistExpRes = 0, gDistExpJob = 0;

		gDistAgeCon = 1/(conAgeSDev*Math.sqrt(2*Math.PI))*Math.pow(Math.E,-Math.pow((eTest.age-conAgeMean),2)/(2*conAgeSDev*conAgeSDev));
		gDistAgeRes = 1/(resAgeSDev*Math.sqrt(2*Math.PI))*Math.pow(Math.E,-Math.pow((eTest.age-resAgeMean),2)/(2*resAgeSDev*resAgeSDev));
		gDistAgeJob = 1/(jobAgeSDev*Math.sqrt(2*Math.PI))*Math.pow(Math.E,-Math.pow((eTest.age-jobAgeMean),2)/(2*jobAgeSDev*jobAgeSDev));

		gDistExpCon = 1/(conExpSDev*Math.sqrt(2*Math.PI))*Math.pow(Math.E,-Math.pow((eTest.exp-conExpMean),2)/(2*conExpSDev*conExpSDev));
		gDistExpRes = 1/(resExpSDev*Math.sqrt(2*Math.PI))*Math.pow(Math.E,-Math.pow((eTest.exp-resExpMean),2)/(2*resExpSDev*resExpSDev));
		gDistExpJob = 1/(jobExpSDev*Math.sqrt(2*Math.PI))*Math.pow(Math.E,-Math.pow((eTest.exp-jobExpMean),2)/(2*jobExpSDev*jobExpSDev));


		double pConH = 0, pResH = 0, pJobH = 0;
		float pConQualType = 0, pResQualType = 0, pJobQualType = 0;
	

		if(eTest.qual.equals("BTech")){

			pConQualType = pConBT;
			pResQualType = pResBT;
			pJobQualType = pJobBT;
		}
		else if(eTest.qual.equals("MTech")){

			pConQualType = pConMT;
			pResQualType = pResMT;
			pJobQualType = pJobMT;
		}
		else if(eTest.qual.equals("PhD")){

			pConQualType = pConPhD;
			pResQualType = pResPhD;
			pJobQualType = pJobPhD;
		}

		pConH = gDistAgeCon * (pConQualType/pCon) * gDistExpCon * (pCon/noSamples);
		pResH = gDistAgeRes * (pResQualType/pRes) * gDistExpRes * (pRes/noSamples);
		pJobH = gDistAgeJob * (pJobQualType/pJob) * gDistExpJob * (pJob/noSamples);

		System.out.println("Consultancy : "+pConH);
		System.out.println("Research : "+pResH);
		System.out.println("Job : "+pJobH);

		double maxP = Math.max(pConH,Math.max(pResH,pJobH));

		System.out.println("Unlabelled WorkType is classified as ");
		if(maxP==pConH)
			System.out.println("Consultancy");
		else if(maxP==pResH)
			System.out.println("Research");
		else
			System.out.println("Job");


	}
}

public class NBThread{
	public static void main(String[] args) {
		ArrayList<Employee> EmpData = new ArrayList<Employee>();

		Employee e1 = new Employee("Consultancy",30,"PhD",9);
		Employee e2 = new Employee("Job",21,"MTech",1);
		Employee e3 = new Employee("Research",26,"MTech",2);
		Employee e4 = new Employee("Job",28,"BTech",10);
		Employee e5 = new Employee("Consultancy",40,"MTech",14);
		Employee e6 = new Employee("Research",35,"PhD",10);
		Employee e7 = new Employee("Research",27,"BTech",6);
		Employee e8 = new Employee("Job",32,"MTech",9);
		Employee e9 = new Employee("Consultancy",45,"BTech",17);
		Employee e10 = new Employee("Research",36,"PhD",7);

		EmpData.addAll(Arrays.asList(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10));

		Employee eTest = new Employee("",30,"MTech",8);				

		System.out.println("Unlabeled Data : ");

		System.out.println("WorkType : ? Age : 30 Qualification : MTech Experience : 8");

		CalculateProb cp = new CalculateProb(EmpData,eTest);
		Thread t = new Thread(cp);
		t.start();
		//cp.calProbs(EmpData,eTest);
	}
}
