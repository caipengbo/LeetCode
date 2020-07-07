package datastructure.heap;

import java.util.Arrays;

/**
 * Title: 堆的实现
 * Desc: 堆是完全二叉树，所以用数组表示，左结点 2*i+1  右节点 2*i+2
 * Created by Myth-Lab on 10/28/2019
 */
public class Heap {
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    // 堆排序
    // 利用完全二叉树的结构来维护的一维数组
    // 从小到大的顺序排序 —— 大顶堆(能确保栈顶index=0是最大的数,将最大的树挪到数组的末尾)
    public void heapSort(int[] array) {
        int size = array.length;
        bulidMaxHeap(array, size);
        for (int i = array.length-1; i > 0; i--) {
            swap(array, 0, i);   // 0代表栈顶
            size--;
            heapify(array, 0, size);  // 只调整发生交换的一侧
        }
    }
    // 构建最大堆（N）
    private void bulidMaxHeap(int[] array, int size) {
        // 从最后一个非叶节点开始调整size/2-1
        for (int i = size/2-1; i >= 0; i--) {
            heapify(array, i, size);
        }
    }
    // O(N log N)
    // 调整堆  i: 当前节点序号 len: 当前堆的长度
    // sift down
    private void heapify(int[] array, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && array[left] > array[largest]) largest = left;
        if (right < len && array[right] > array[largest]) largest = right;
        if (largest != i) {
            swap(array, i, largest);
            // 继续（只）调整发生交换的那个节点 的下部分
            heapify(array, largest, len);
        }
    }
    // 迭代版:priorityqueue源码
    private void siftDown(int[] array, int k) {
        int len = array.length;
        int half = len >>> 1;
        int val = array[k];
        while (k < half) {
            int child = k << 1 + 1;
            int right = child + 1;
            int largest = array[child];
            if (array[right] > val) {
                largest = array[right];
                child = right;
            }
            if (val >= largest) break;
            array[k] = largest;
            k = child;
        }
        array[k] = val;
    }

    private void testHeapSort(int[] array) {
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

}
