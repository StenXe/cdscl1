#include<iostream>
#include<stdlib.h>
#include<time.h>
#define SIZE 1000000
using namespace std;

void merge_sort(int[],int,int);
void merge(int[],int,int,int);

int main()
{

	clock_t t;

	t = clock();

	int a[SIZE] ;
	int i=0,low,high,search;
	
	srand(time(NULL));
	cout<<"Input Array"<<endl;

	for(i=0;i<SIZE;i++)
	{
		a[i] = rand() % SIZE;
		//cout<<a[i]<<endl;
	}
	
	cout<<"END INPUT"<<endl;

	low = 0;
	high = SIZE - 1;

	merge_sort(a,low,high);


	/*for(i=0;i<SIZE;i++)
	{
		cout<<a[i]<<endl;
		
	}*/
	
	t = clock() - t;

	cout<<"Total time "<<((float) t)/CLOCKS_PER_SEC<<endl;

	return 0;
}

void merge(int a[], int low, int mid, int high)			
{
	int i,j,k;
	
	int n1 = mid - low + 1;
	int n2 = high - mid;

	int a_left[n1],a_right[n2];	

	for(i=0;i<n1;i++)
		a_left[i] = a[low + i];
	for(j=0;j<n2;j++)
		a_right[j] = a[mid + 1 + j];
	i = 0;
	j = 0;
	k = low;

	while(i<n1 && j<n2)						//Join the array by comparing elements of two sub arrays
	{
		if(a_left[i]<a_right[j])
		{
			a[k] = a_left[i];
			i++;
			k++;
		}
		else
		{
			a[k] = a_right[j];
			j++;
			k++;
		}
	}
	while(i<n1)							//Copy remaining elements of the left sub array
	{
		a[k] = a_left[i];
		i++;
		k++;
	}
	while(j<n2)							//Copy remaining elements of the right sub array
	{
		a[k] = a_right[j];
		j++;
		k++;
	}

}

void merge_sort(int a[],int low,int high)				//Recursively divide the array in half until single element is left 
{
	if(low<high)
	{
		int mid = (low+high)/2;
		#pragma omp parallel		
		{
		
		merge_sort(a,low,mid);
		merge_sort(a,mid+1,high);
		
		merge(a,low,mid,high);
		}
		
	}

}




