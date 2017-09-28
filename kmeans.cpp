#include<iostream>
#include<fstream>
#include<string.h>
#include<stdlib.h>
#include<math.h>
#include<unistd.h>
#define SIZE 150*4
using namespace std;

//sed -i.bak 's/$/,/' infile

int main()
{
	ifstream file("new_iris.data");
	string value;
	float initData[SIZE];
	float clust1[SIZE];
	float clust2[SIZE];
	float c1,c2;
	int i = 0;
	while(file.good())
	{
			
		getline(file,value,',');
		initData[i] = strtof(value.c_str(),NULL);
		i++;
		
	}

	int CSize;

	cout<<"Enter number of clusters (min 2)"<<endl;
	cin>>CSize;
	
	if(CSize<2)
	{
		cout<<"Invalid number of clusters"<<endl;
		cout<<"Resetting to default number 2"<<endl;
		CSize = 2;
	}

	float C[CSize][4];
	float newC[CSize][4];
	float CMean[CSize];
	float newCMean[CSize];

	for(int i=0;i<CSize;i++)
	{
		CMean[i] = 0;
		newCMean[i] = 0;
	}

	/*float C1[4] = {0}, C2[4] = {0};
	float newC1[4] = {0}, newC2[4] = {0};
	float C1Mean, C2Mean, newC1Mean, newC2Mean;*/
	int countC[CSize];

	int row_diff = SIZE/(4*(CSize-1));			//Number of rows to skip

	for(int i=0;i<4;i++)
	{
		newC[0][i] = initData[i];
		newC[CSize-1][i] = initData[SIZE-4+i];
		newCMean[0] += newC[0][i];
		newCMean[CSize-1] += newC[CSize-1][i];
		C[0][i] = newC[0][i];
		C[CSize-1][i] = newC[CSize-1][i];
	}

	for(int i=1;i<CSize-1;i++)
	{
		for(int j=0;j<4;j++)
		{
			newC[i][j] = initData[((i*row_diff*4)-4) + j];
			newCMean[i] += newC[i][j];
			C[i][j] = newC[i][j];
		}
	}
	
		
	for(int i=0;i<CSize;i++)
		newCMean[i] /= 4;

	for(int i=0;i<CSize;i++)
	{
		cout<<"Initial C"<<i<<": "<<endl;
		cout<<C[i][0]<<" "<<C[i][1]<<" "<<C[i][2]<<" "<<C[i][3]<<endl;
	}
	
	int flag = 0;	

	do
	{

		for(int j=0;j<CSize;j++)
		{
			C[j][0] = newC[j][0];
			C[j][1] = newC[j][1];
			C[j][2] = newC[j][2];
			C[j][3] = newC[j][3];

			CMean[j] = newCMean[j];

			newC[j][0] = 0;
			newC[j][1] = 0;
			newC[j][2] = 0;
			newC[j][3] = 0;

			countC[j] = 0;
		}

		for(int j=0;j<CSize;j++)
		{
			cout<<"J is "<<j<<endl;
			cout<<"Change C"<<j<<": "<<endl;
			cout<<C[j][0]<<" "<<C[j][1]<<" "<<C[j][2]<<" "<<C[j][3]<<endl;
		}
		
	
		for(int i=0;i<SIZE;i+=4)
		{
			
			float euclidDist[CSize];
			int minDistIndex = 0;
			float minDist = 9999;
			for(int j=0;j<CSize;j++)
			{
				euclidDist[j] = sqrt(pow((initData[i]-C[j][0]),2)+pow((initData[i+1]-C[j][1]),2)+pow((initData[i+2]-C[j][2]),2)+pow((initData[i+3]-C[j][3]),2));				
				if(minDist>euclidDist[j])
				{
					minDist = euclidDist[j];
					minDistIndex = j;			
				}
			}

			newC[minDistIndex][0] += initData[i];
			newC[minDistIndex][1] += initData[i+1];
			newC[minDistIndex][2] += initData[i+2];
			newC[minDistIndex][3] += initData[i+3];	
			
			countC[minDistIndex]++;

		}


		for(int j=0;j<CSize;j++)
		{
			newC[j][0] /= countC[j];
			newC[j][1] /= countC[j]; 
			newC[j][2] /= countC[j];
			newC[j][3] /= countC[j];			

			cout<<"Counts C"<<j<<" "<<countC[j]<<endl;
			
			newCMean[j] = newC[j][0] + newC[j][1] + newC[j][2] + newC[j][3];

		}

		
		flag = 0;		

		for(int j=0;j<CSize;j++)
		{

			if(CMean[j]!=newCMean[j])
			{
				flag = 1;		
				break;
			}
		}

	}while(flag);


	for(int j=0;j<CSize;j++)
	{
		cout<<"Final C"<<j<<": "<<endl;
		cout<<newC[j][0]<<" "<<newC[j][1]<<" "<<newC[j][2]<<" "<<newC[j][3]<<endl;
	}


	return 0;
}
