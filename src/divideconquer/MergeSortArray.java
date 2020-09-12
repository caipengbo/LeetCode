package divideconquer;

import java.util.Arrays;

/**
* Title: 【经典分治例题】归并排序（数组）
* Desc:  链表见148题（在divideconquer包和list包内）
* Created by Myth on 01/15/2020 in VSCode
*/

public class MergeSortArray {
    public void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid+1, end);
        merge(nums, start, mid, mid+1, end);
    }
    public void merge(int[] nums, int start1, int end1, int start2, int end2) {
        if (start1 > end1 || start2 > end2) return;
        int[] temp = Arrays.copyOf(nums, nums.length);
        int i = start1;  // i不一定是从0开始的，是从start1开始的，start1 > start2
        while (start1 <= end1 && start2 <= end2) {
            if (temp[start1] <= temp[start2]) nums[i++] = temp[start1++];
            else nums[i++] = temp[start2++];
        }
        // 两部分不等长时，处理剩下的
        while (start1 <= end1) nums[i++] = temp[start1++];
        while (start2 <= end2) nums[i++] = temp[start2++];
    }

    public void testMergeSort2(int[] array) {
        mergeSort(array,0, array.length-1);
        System.out.println(Arrays.toString(array));
    }
    // ======================原地归并算法(原地Merge)
    // 交换[start1, end1)  [start2, end2)
    void exchange(int[] A, int start1, int end1, int start2, int end2) {
        reverse(A, start1, end1);  // 左
        reverse(A, start2, end2);  // 右
        reverse(A, start1, end2);  // 全部
    }
    
    void reverse(int[] A, int start, int end) {
        int i= start;
        int j= end-1;
        while (i < j) {
            swap(A, i, j);
            i++;
            j--;
        }
    }
    
    void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    //数组两个有序部分的归并
    void merge(int[] A,int begin, int mid, int end) {
        int i = begin;
        int j = mid+1;
        int k = end;
        // i的左边肯定比j前面的要小
        // start... i _____ | ___step__ j ...end
        while (i < j && j <= k) {
            int step = 0;
            // 左边
            while (i < j && A[i] <= A[j]) {
                i++;
            }
            // 右边的长度
            while (j <= k && A[j] < A[i]) {
                j++;
                step++;
            }
            exchange(A, i, j-step, j-step, j);
            i = i + step;  // 每次排序step个
        }
    }
    // end = len-1
    public void mergeSort2(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        mergeSort2(nums, start, mid);
        mergeSort2(nums, mid+1, end);
        merge(nums, start, mid, end);
    }
    public void testMergeSort(int[] array) {
        mergeSort2(array,0, array.length-1);
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
        int[] array8 = {5,2,3,1};

        MergeSortArray mergeSort = new MergeSortArray();
        mergeSort.testMergeSort(array1);
        mergeSort.testMergeSort(array2);
        mergeSort.testMergeSort(array3);
        mergeSort.testMergeSort(array4);  // [1, 1, 3, 4, 7, 8]
        mergeSort.testMergeSort(array5);  // [1, 4, 5, 6, 7, 8]
        mergeSort.testMergeSort(array6);
        mergeSort.testMergeSort(array7);  // [4, 5, 6, 7, 8, 10]
        mergeSort.testMergeSort(array8);  // [4, 5, 6, 7, 8, 10]
    }
}