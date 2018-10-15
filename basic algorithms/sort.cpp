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
// 希尔排序（基于插入排序）
void ShellSort(int data[MAX_LARGE], int n);
// 归并排序
void MergeSort(int data[MAX_LARGE], int low, int high);
// 快速排序
void QuikSort(int data[MAX_LARGE], int low, int high);
// 堆排序
void HeapSort(int data[MAX_LARGE], int n);

// 辅助函数
void Exchange(int& a, int& b)
{
	int temp = a;
	a = b;
	b = temp;
}
void Print(int data[MAX_LARGE], int n)
{
	for (int i = 0; i < n; i++) {
		cout << data[i] << " ";
	}
	cout << endl;
}

void SelectSort(int data[MAX_LARGE], int n)
{
	// 基本思想：从后面（无序区）选择最小的元素放到前方(有序区的尾部)
	// i 记录排好序的位置，j 从左往右选择最小元素的index
	int min_index;

	for (int i = 0; i < n; i++) {
		min_index = i;
		for (int j = i + 1; j < n; j++) {
			if (data[j] < data[min_index]) {
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

	while (flag) {
		flag = false;
		for (i = 0; i < n - 1; i++) {
			if (data[i] > data[i + 1]) {
				Exchange(data[i], data[i + 1]);
				flag = true;
			}
		}
	}
}

void InsertSort(int data[MAX_LARGE], int n)
{
	// 基本思想：左侧认为是有序区，右侧无序区的元素插入到左侧（设计到位置的依次后移）
	// i 有序区边界 ， j 在有序区移动（从后向前）找到合适的位置，插入无序区的元素
	// 还有一种实现方法，就是从后往前依次有序的交换
	int i, j;
	int temp;

	for (i = 0; i < n - 1; i++) {
		j = i;
		temp = data[i + 1];
		while (j >= 0 && data[j] > temp) {          // 注意此处j的取值
			data[j + 1] = data[j]; //后移
			j--;
		}
		data[j + 1] = temp;
	}

}

void ShellSort(int data[MAX_LARGE], int n)
{
	// 基本思想：对不相邻的元素（有间隔h）进行插入排序，然后缩短间隔至1
	// 如何缩短间隔序列（递增序列，希尔排序又叫缩小递增序列排序）：有专门的研究，不需要自己设计，选择某些常用的即可
	int h = 1;
	int temp;
	int i, j;

	// 设置h的初始值
	while (h < n / 3) h = h * 3 + 1;  // 递增序列 1, 4, 13, 40, 121, 364 .....

	while (h >= 1) {
		// 将插入排序的每次移动1，改为移动h
		for (i = 0; i < n - h; i++) {
			j = i;
			temp = data[i + h];
			while (j >= 0 && data[j] > temp) {
				data[j + h] = data[j];
				j -= h;
			}
			data[j + h] = temp;
		}
		h /= 3;
	}
}
// 原地归并，low索引下界，high索引上界
void Merge(int data[MAX_LARGE], int low, int mid, int high)
{
	// 原地归并，对于一个数组，将其前后半部分（均有序小到大），归并成一个有序数组
	int i = low, j = mid + 1;  // j 必须是mid+1，因为 mid有可能和low相等
	int *aux = new int[high + 1];  // 辅助数组

	for (int k = low; k <= high; k++)
		aux[k] = data[k];

	// 将归并的结果放到原数组中
	for (int k = low; k <= high; k++) {
		if (i > mid) // 前半部分归并完毕
			data[k] = aux[j++];
		else if (j > high) // 后半部分归并完毕
			data[k] = aux[i++];
		else if (aux[i] < aux[j]) // 注意是比较辅助数组，不可比较原数组（因为原数组已经变化了）
			data[k] = aux[i++];
		else
			data[k] = aux[j++];
	}
}

void MergeSort(int data[MAX_LARGE], int low, int high)
{
	// 自上而下（分治思想）
	if (low >= high) return;
	int mid = (low + high) / 2;

	// 分治，一定要多注意如何分，对于边界要多考虑特殊情况
	// MergeSort(data, low, mid-1); // 若mid==0
	// MergeSort(data, mid, high); // 此种情况下会导致 MergeSort(data, 0, 1)一直出现

	MergeSort(data, low, mid);
	MergeSort(data, mid + 1, high);
	Merge(data, low, mid, high);
}

// 原地切分(对于边界的判断是个难点)
int Partition(int data[MAX_LARGE], int low, int high)
{
	// high > low 
	int i = low, j = high;
	while (true) {
		while (data[++i] < data[low]) {// 找到比中轴元素data[low] 大的元素data[i]
			if (i == high) break;  // 避免越界
		}
		while (data[j] > data[low]) { // 找到比中轴元素data[low] 小的元素data[j]
			j--; // 不可在while中 data[j--] ，因为这样即使data[j] 不大于data[low]依然会自减
			if (j == low) break;  // 避免越界
		}
		if (i >= j) break;  // 结束条件
		Exchange(data[i], data[j]);
	}
	Exchange(data[low], data[j]);
	return j;
}

void QuikSort(int data[MAX_LARGE], int low, int high)
{
	cout << "low: " << low << " high: " << high << endl;
	if (low >= high) return;
	// 切分
	int pivot = Partition(data, low, high);
	cout << " pivot:" << pivot << endl;
	// 排序中轴 前部分j--;
	QuikSort(data, low, pivot - 1);
	// 排序中轴 后部分
	QuikSort(data, pivot + 1, high);
}
// 三向切分快速排序（有大量重复元素时，算法效率很高）
void QuikSort3Way(int data[MAX_LARGE], int low, int high)
{
	if (low >= high) return;
	// data[low...lt-1] < pivot  data[lt...i-1] == pivot   data[i...gt] 未确定的 data[gt...high] > pivot
	int lt = low;
	int gt = high;
	int i = low + 1;
	int pivot = data[low];
	// if a[i] < pivot a[lt] <-> a[i] lt++ i++
	// if a[i] > pivot a[gt] <-> a[i] gt--
	// if a[i] == pivot i++
	while (i <= gt) {
		if (data[i] < pivot)
			Exchange(data[lt++], data[i++]);
		else if (data[i] > pivot)
			Exchange(data[i], data[gt--]);
		else
			i++;
	}
	QuikSort3Way(data, low, lt - 1);
	QuikSort3Way(data, gt + 1, high);
}

int main()
{
	int data[30] = { 121, 18, 3, 7, 9, 11, 612, 133, 4, 1, 6, 8, 13, 7, 39, 0, 162, 33, 4, 5, 2, 8, 55, 63, 49, 0, 12, 33, 4, 14 };
	// SelectSort(data, 30);
	// BubbleSort(data, 30);
	// InsertSort(data, 30);
	// ShellSort(data, 30);
	// Print(data, 30);
	// int merge_data[] = {110, 37};
	int data2[] = { 5, 6, 4 };
	QuikSort3Way(data, 0, 30 - 1);
	Print(data, 30);
}

