package datastructure.heap;

import java.util.Arrays;

/**
 * Title:
 * Desc:
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

}
