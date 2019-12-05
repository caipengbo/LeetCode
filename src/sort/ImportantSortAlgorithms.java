package sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * Title: 重要的(从小到大)排序算法（要求会手写）
 * Desc: 面试重要排序算法
 * Created by Myth-Lab on 10/11/2019
 */
public class ImportantSortAlgorithms {
    // 冒泡排序(交换相邻元素)
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private void bubbleSort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if (array[j] > array[j+1]) swap(array, j, j+1);
            }
        }
    }
    public void testBubbleSort(int[] array) {
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
    // 快速排序
    private int partition(int[] array, int l, int r) {
        int pivot = array[l];
        while (l < r) {
            // 一定要加等于号，不然重复数组会死循环
            while (l<r && array[r] >= pivot) r--;
            array[l] = array[r];
            while (l<r && array[l] <= pivot) l++;
            array[r] = array[l];
        }
        array[l] = pivot;
        return l;
    }

    private void testPartition() {
        // int[] arr = {5,2,1,9,4,8,0,6};
        int[] arr = {6,4,7,1,2};
        partition(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        quickSort(arr, l, p-1);
        quickSort(arr, p+1, r);
    }
    public void testQuickSort(int[] array) {
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }
    // 简单的插入排序(有序区在前)
    public void insertSort(int[] array) {
        int i, j, temp;
        // for (i = 0; i < array.length; i++) {
        // 因为初始时候array[0] 只有一个元素自成一个有序区，所以让 i 起始为 1
        for (i = 1; i < array.length; i++) {
            temp = array[i];
            for (j = i-1; j >= 0 && temp < array[j]; j--) {
                array[j+1] = array[j];
            }
            array[j+1] = temp;
        }
    }
    private void testInsertSort(int[] array) {
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }
    // 希尔排序，缩小增量排序：缩小规模
    // 简单的插入排序在数据有序性越高效率越高，规模越小效率越高
    public void shellSort(int[] array) {
        int n = array.length;
        for (int gap = n/2; gap > 0; gap/=2) {
            insertSort(array, gap);
        }
    }
    // 每一组进行插入排序： gap：间距增量;  i:起始位置
    private void insertSort(int[] array, int gap) {
        int i, j, temp;
        // 注意此处为何初始为gap?
        for (i = gap; i < array.length; i++) {
            temp = array[i];
            for (j = i-gap; j >= 0 && temp < array[j]; j-=gap) {
                array[j+gap] = array[j];
            }
            array[j+gap] = temp;
        }
    }
    private void testShellSort(int[] array) {
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }
    // 简单选择排序
    private void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[min] > array[j]) min = j;
            }
            if (min != i) swap(array, i, min);
        }
    }
    private void testSelectSort(int[] array) {
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }
    // 堆排序
    // 利用完全二叉树的结构来维护的一维数组
    // 从小到大的顺序排序 —— 大顶堆
    public void heapSort(int[] array) {
        int size = array.length;
        bulidMaxHeap(array, size);
        for (int i = array.length-1; i > 0; i--) {
            swap(array, 0, i);
            size--;
            heapify(array, 0, size);  // 只调整发生交换的一侧
        }
    }
    // 构(重)建堆
    private void bulidMaxHeap(int[] array, int size) {
        // 从最后一个非叶节点开始调整
        for (int i = size/2-1; i >= 0; i--) {
            heapify(array, i, size);
        }
    }
    // 调整堆  i: 当前节点序号 len: 当前堆的长度
    private void heapify(int[] array, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && array[left] > array[largest]) largest = left;
        if (right < len && array[right] > array[largest]) largest = right;
        if (largest != i) {
            swap(array, i, largest);
            // 继续（只）调整发生交换的一侧
            heapify(array, largest, len);
        }
    }
    private void testHeapSort(int[] array) {
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
    // 数组的归并排序（使用下标）
    private void merge(int[] array, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] temp1 = new int[n1];
        int[] temp2 = new int[n2];
        for (int i = 0; i < n1; i++)
            temp1[i] = array[i+l];
        for (int i = 0; i < n2; i++)
            temp2[i] = array[i+m+1];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (temp1[i] < temp2[j]) array[k++] = temp1[i++];
            else array[k++] = temp2[j++];
        }
        while (i < n1) array[k++] = temp1[i++];
        while (j < n2) array[k++] = temp2[j++];
    }
    public void mergeSort(int[] array, int l, int r) {
        if (l >= r) return;
        int m = (r + l) / 2;
        mergeSort(array, l, m);
        mergeSort(array, m+1, r);
        merge(array, l, m, r);
    }
    public void testMergeSort(int[] array) {
        mergeSort(array,0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array1 = {1};
        int[] array2 = {1,2};
        int[] array3 = {2,1};
        int[] array4 = {8,1,3,1,4,7};
        int[] array5 = {8,7,6,1,4,5};
        int[] array6 = {6,6,6,6,6,6};
        int[] array7 = {10,8,7,6,5,4};
        ImportantSortAlgorithms sortAlgorithms = new ImportantSortAlgorithms();
        sortAlgorithms.testHeapSort(array1);
        sortAlgorithms.testHeapSort(array2);
        sortAlgorithms.testHeapSort(array3);
        sortAlgorithms.testHeapSort(array4);
        sortAlgorithms.testHeapSort(array5);
        sortAlgorithms.testHeapSort(array6);
        sortAlgorithms.testHeapSort(array7);
    }

}
