package sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * Title: 重要的(从小到大)排序算法（要求会手写）
 * Desc: 面试重要排序算法
 * Created by Myth-Lab on 10/11/2019
 */
public class ImportantSortAlgorithms {
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
    // 选择排序
    private void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[min] > array[j]) min = j;
            }
            if (min != i) swap(array, i, min);
        }
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
    private void testPartition() {
        // int[] arr = {5,2,1,9,4,8,0,6};
        int[] arr = {6,4,7,1,2};
        partition(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private void testSelectSort(int[] array) {
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }
    public static void main(String[] args) {
        int[] array1 = {1};
        int[] array2 = {1,2};
        int[] array3 = {2,1};
        int[] array4 = {8,1,3,1,4,7};
        int[] array5 = {8,7,6,1,4,5};
        int[] array6 = {6,6,6,6,6,6};
        ImportantSortAlgorithms sortAlgorithms = new ImportantSortAlgorithms();
        sortAlgorithms.testQuickSort(array1);
        sortAlgorithms.testQuickSort(array2);
        sortAlgorithms.testQuickSort(array3);
        sortAlgorithms.testQuickSort(array4);
        sortAlgorithms.testQuickSort(array5);
        sortAlgorithms.testQuickSort(array6);
    }

}
