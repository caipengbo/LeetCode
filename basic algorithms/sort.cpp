/**
 * 基本排序算法的实现
 *
 * 选择排序、冒泡排序、插入排序
 * 希尔排序、归并排序、快速排序、堆排序
 *
 * 从小到大排序 n > 1
 */

#include<iostream>
using namespace std;

const int MAX_LARGE = 100;

// 选择排序
void SelectSort(int data[MAX_LARGE], int n);
// 冒泡排序
void BubbleSort(int data[MAX_LARGE], int n);
// 插入排序
void InsertSort(int data[MAX_LARGE], int n);
// 希尔排序
void ShellSort(int data[MAX_LARGE]);
// 归并排序
void MergeSort(int data[MAX_LARGE]);
// 快速排序
void QuikSort(int data[MAX_LARGE]);
// 堆排序
void HeapSort(int data[MAX_LARGE]);

// 辅助函数
void Exchange(int& a, int& b)
{
    int temp = a;
    a = b;
    b = temp;
}
void Print(int data[MAX_LARGE], int n)
{
    for(int i = 0; i < n; i++) {
        cout << data[i] << " ";
    }
    cout << endl;
}

void SelectSort(int data[MAX_LARGE], int n)
{
     // 基本思想：从后面（无序区）选择最小的元素放到前方(有序区的尾部)
     // i 记录排好序的位置，j 从左往右选择最小元素的index
     int min_index;

     for(int i = 0; i < n; i++) {
        min_index = i;
        for(int j = i+1; j < n; j++) {
            if(data[j] < data[min_index]) {
                min_index = j;
            }
        }
        Exchange(data[i], data[min_index]);
     }
}

void BubbleSort(int data[MAX_LARGE], int n)
{
    // 一遍又一遍的交换相邻元素，直到有序
    int flag = true; // 是否交换了元素（是否冒泡）
    int i;

    while(flag) {
        flag = false;
        for(i = 0; i < n - 1; i++) {
            if(data[i] > data[i+1]) {
                Exchange(data[i], data[i+1]);
                flag = true;
            }
        }
    }
}

void InsertSort(int data[MAX_LARGE], int n)
{
    // 基本思想：左侧认为是有序区，右侧无序区的元素插入到左侧（设计到位置的依次后移）
    // i 有序区边界 ， j 在有序区移动插入无序区的元素
    int i, j;
    int temp;
    for(i = 0; i < n - 1; i++) {
        j = i;
        temp = data[i+1];
        while(j > 0 && data[j] > temp) {
            data[j+1] = data[j]; //后移
            j--;
        }
        data[j+1] = temp;
    }
}

int main()
{
    int data[10] = {2, 8, 3, 7, 9, 0, 12, 33, 4, 1};
    // SelectSort(data, 10);
    // BubbleSort(data, 10);
    InsertSort(data, 10);
    Print(data, 10);
}
