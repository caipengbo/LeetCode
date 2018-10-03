#include "binarysearch.h"
#include<iostream>
using namespace std;
//Array start from 1, not fount return 0
int binarysearch(int arr[], int n, int val)
{
	int low = 1,hight = n;
	int mid = 0;
	while (low <= hight)
	{
		mid = (low + hight) / 2;
		if (val < arr[mid]) hight = mid - 1;
		else if (val > arr[mid]) low = mid + 1;
		else return mid;
	}
	return 0;
}
//Recursive version
int binarysearch2(int arr[],int low,int hight,int val)
{
	if (low > hight) return 0;
	int mid = (low + hight) / 2;
	if (val < arr[mid]) return binarysearch2(arr, low, mid-1, val);
	else if (val > arr[mid])  return binarysearch2(arr, mid+1, hight, val);
	else return mid;
}
void test1()
{
	int arr[11] = {0,1,2,6,8,9,12,23,43,45,88}; //´Ó1¿ªÊ¼
	int position = binarysearch2(arr,1,10,121);
	if (position != 0)
	{
		cout << "Found:" << arr[position] << " Position:" << position << endl;
	}
	else {
		cout << "Not found" << endl;
	}
}
