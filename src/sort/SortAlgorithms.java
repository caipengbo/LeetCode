package sort;

import java.util.Arrays;

import util.ListNode;

/**
 * Title: 重要的(从小到大)排序算法（要求会手写）
 * Desc: 面试重要排序算法
 * Created by Myth-Lab on 10/11/2019
 */
public class SortAlgorithms {
    // 冒泡排序(交换相邻元素,将最大元素交换到最后)
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private void bubbleSort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-i-1; j++) {  // 随着i的增大j的范围不断缩小
                if (array[j] > array[j+1]) {
                    swap(array, j, j+1);
                }
            }
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
    
    // 简单的插入排序(有序区在前)  适用于 —— 基本有序的
    public void insertSort(int[] array) {  
        int i, j, temp;
        // for (i = 0; i < array.length; i++) {
        // 因为初始时候array[0] 只有一个元素自成一个有序区，所以让 i 起始为 1
        for (i = 1; i < array.length; i++) {
            temp = array[i];
            // 在左侧有序区腾出来空
            for (j = i-1; j >= 0 && temp < array[j]; j--) {  // 已经有序，就不会移动
                array[j+1] = array[j];   // 往后移动，腾出来空
            }
            array[j+1] = temp;   // 插入
        }
    }
    // 折半插入排序
    public void insertSortBiSearch(int[] array) {  
        int i, j, temp;
        // for (i = 0; i < array.length; i++) {
        // 因为初始时候array[0] 只有一个元素自成一个有序区，所以让 i 起始为 1
        for (i = 1; i < array.length; i++) {
            temp = array[i];
            // 二分查找要插入的位置
            int l = 0;
            int r = i-1;
            while (l < r) {
                int m = (l + r) / 2;
                if (temp < array[m]) {
                    r = m;  // 左侧
                } else {
                    l = m+1;  // 右侧
                }
            }
            for (j = i-1; j >= 0 && temp < array[j]; j--) {
                array[j+1] = array[j];
            }
            array[j+1] = temp;   // 插入
        }
    }
    
    // 希尔排序，缩小增量排序：缩小规模   
    // 简单的插入排序在数据有序性越高效率越高，规模越小效率越高
    public void shellSort(int[] array) {
        int n = array.length;
        // 分组插入排序
        for (int gap = n/2; gap > 0; gap/=2) {
            insertSort(array, gap);
        }
    }
    // 每一组进行插入排序： gap：间距增量;  i:起始位置
    private void insertSort(int[] array, int gap) {
        int i, j, temp;
        // 注意此处为何初始为gap?  gap个一组，进行分组插入排序
        for (i = gap; i < array.length; i++) {
            temp = array[i];
            for (j = i-gap; j >= 0 && temp < array[j]; j-=gap) {
                array[j+gap] = array[j];
            }
            array[j+gap] = temp;
        }
    }
    
    // 简单选择排序，每次从无序区选择一个最值追加到有序区的末尾
    private void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[min] > array[j]) min = j;
            }
            if (min != i) swap(array, i, min);
        }
    }
    
    // 堆排序
    // 利用完全二叉树的结构来维护的一维数组
    // 从小到大的顺序排序 —— 大顶堆
    public void heapSort(int[] array) {
        int size = array.length;
        // 1. 构建大顶堆
        bulidMaxHeap(array, size);
        for (int i = array.length-1; i > 0; i--) {
            // 2. 每次讲堆顶的元素和堆末尾的元素交换
            swap(array, 0, i);
            size--;
            // 3. 重建堆
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
    // 链表的归并排序
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 将链表分为两段
        ListNode p = head, q = head, pre = head;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }

        pre.next = null;  // 截断链表
        ListNode left = sortList(head);
        ListNode right = sortList(p);
        return mergeTwoListsRecursive(left, right);
    }
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }
    // 非递归版本（自底向上）
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(-1), cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        } 
        return dummy.next;
    }
    

    // ========================== Test ===========================
    public void testMergeSort(int[] array) {
        mergeSort(array,0, array.length-1);
        System.out.println(Arrays.toString(array));
    }
    private void testHeapSort(int[] array) {
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
    private void testSelectSort(int[] array) {
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }
    private void testShellSort(int[] array) {
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }
    private void testInsertSort(int[] array) {
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    private void testInsertSortBiSearch(int[] array) {
        insertSortBiSearch(array);
        System.out.println(Arrays.toString(array));
    }

    public void testBubbleSort(int[] array) {
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
    private void testPartition() {
        // int[] arr = {5,2,1,9,4,8,0,6};
        int[] arr = {6,4,7,1,2};
        partition(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public void testQuickSort(int[] array) {
        quickSort(array, 0, array.length-1);
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
        SortAlgorithms sortAlgorithms = new SortAlgorithms();
        sortAlgorithms.testInsertSortBiSearch(array1);
        sortAlgorithms.testInsertSortBiSearch(array2);
        sortAlgorithms.testInsertSortBiSearch(array3);
        sortAlgorithms.testInsertSortBiSearch(array4);
        sortAlgorithms.testInsertSortBiSearch(array5);
        sortAlgorithms.testInsertSortBiSearch(array6);
        sortAlgorithms.testInsertSortBiSearch(array7);
    }

}
