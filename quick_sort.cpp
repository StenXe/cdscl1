#include<iostream>
#include<time.h>
#define SIZE 10
using namespace std;
void quicksort(int a[],int low,int high);
int partition(int a[],int low,int high);
int main()
{
	clock_t t;

	t = clock();

	int a[SIZE];

	for(int i=0;i<SIZE;i++)
	{
		a[i] = SIZE - i;
	}

	quicksort(a,0,SIZE);
	
	for(int i=0;i<SIZE;i++)
		cout<<a[i]<<endl;

	t = clock() - t;

	cout<<"Total time "<<((float)t)/CLOCKS_PER_SEC<<endl;
	return 0;
}
void quicksort(int a[],int low,int high)
{
	if(low<high)
	#pragma omp parallel
	{
	{
		int p = partition(a,low,high);

		quicksort(a,low,p-1);
		quicksort(a,p+1,high);
	}
	}
}

int partition(int a[],int low,int high)
{
	int pivot = a[high];

	int i = low - 1;

	for(int j=low;j<high;j++)
	{
		if(a[j]<pivot)
		{
			i++;
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	int temp = a[i+1];
	a[i+1] = a[high];
	a[high] = temp;

	return (i+1);
}
