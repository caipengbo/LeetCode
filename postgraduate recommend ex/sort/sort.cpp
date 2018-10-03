#include <iostream>
using namespace std;
//选择排序
void select_sort(int a[],int n)
{
    int min_index = 0;
    for(int i=0;i<n-1;i++)
    {
        min_index = i;
        for(int j=i+1;j<n;j++)
        {
            if(a[j]<a[min_index])
            {
                min_index = j;
            }
        }
        if(min_index!=i)
        {
            int temp = a[i];
            a[i] = a[min_index];
            a[min_index] = temp;
        }
    }
}
//冒泡排序
void bubble_sort(int a[],int n)
{
    int work=0;
    for(int i=n-1;i>0;i--)
    {
        work = 0;
        for(int j=0;j<i;j++)
        {
            if(a[j]>a[j+1])
            {
                 int temp = a[j];
                 a[j] = a[j+1];
                 a[j+1] = temp;
                 work = 1;
            }
        }
        if(work==0) return; //如果没有发生交换，则立即结束
    }
}
//改进版冒泡排序
void bubble_sort_2(int a[],int n)
{
    int exchange=n;
    int bound; //无序区的边界
    while(exchange != 0)
    {
        bound = exchange;
        exchange = 0;
        for(int j=0;j<bound;j++)
        {
            if(a[j]>a[j+1])
            {
                 int temp = a[j];
                 a[j] = a[j+1];
                 a[j+1] = temp;
                 exchange = j;
            }
        }
    }
}
//快速排序
//一次划分
int once_partition(int a[],int first,int last)
{
    while(first<last)
    {
        while(first<last && a[first]<=a[last])
        {
            last--;
        }
        if(first<last)
        {
            int temp = a[first];
            a[first] = a[last];
            a[last] = temp;
            first++;
        }
        while(first<last && first<last && a[first]<=a[last])
        {
            first++;
        }
        if(first<last)
        {
            int temp = a[first];
            a[first] = a[last];
            a[last] = temp;
            last--;
        }
    }
    return first;
}
//递归快排
void quick_sort(int a[],int first,int last)
{
    if(first<last)
    {
         int povit = once_partition(a,first,last);
         quick_sort(a,0,povit-1);
         quick_sort(a,povit+1,last);

    }


}
int main ()
{
    int a[10] = {2,1};
    int b[10] = {2,1,5,4,2,8,9,12,3,222};
    quick_sort(b,0,10);
    for(int i=0;i<10;i++)
    {
        cout<<b[i]<<" ";
    }
}