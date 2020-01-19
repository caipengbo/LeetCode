package divideconquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: 315. 计算右侧小于当前元素的个数 
 * Desc: 
 * Created by Myth on 01/17/2020 in VSCode
 */

public class P315CountOfSmallerNumbersAfterSelf {
    // 难点，和求逆序对相比，原来的数组元素位置变了，那么如何统计？
    // 不改变原数组，使用索引数组，改变的是索引数组
    public void merge(int[] nums, int[] indexs, int l1, int r1, int l2, int r2, int[] counts) {
        if (l1 > r1 || l2 > r2) return;
        int[] copy = Arrays.copyOf(indexs, nums.length);
        int i = l1;
        int count = 0;  // Key!!! 计数(计算右侧的数目)
        while (l1 <= r1 && l2 <= r2) {
            if (nums[copy[l1]] <= nums[copy[l2]]) {
                counts[copy[l1]] += count; 
                indexs[i++] = copy[l1++];
            } else {
                count++;
                indexs[i++] = copy[l2++];
            }
        }
        while (l1 <= r1) {
            // counts[copy[l1]]++;
            counts[copy[l1]] += count;
            indexs[i++] = copy[l1++];
        }
        while (l2 <= r2) indexs[i++] = copy[l2++];
    }
    // 减少copy数组的长度
    public void merge2(int[] nums, int[] indexs, int l1, int r1, int l2, int r2, int[] counts) {
        if (l1 > r1 || l2 > r2) return;
        // r2 - r1 + 1
        int n = r2 - l1 + 1;
        int[] copy = new int[n];
        for (int i = 0; i < n; i++) {
            copy[i] = indexs[l1+i];
        }
        int i = l1, s = l1;
        int count = 0;  // Key!!! 计数(计算右侧的数目)
        while (l1 <= r1 && l2 <= r2) {
            if (nums[copy[l1-s]] <= nums[copy[l2-s]]) {
                counts[copy[l1-s]] += count; 
                indexs[i++] = copy[(l1++)-s];
            } else {
                count++;
                indexs[i++] = copy[(l2++)-s];
            }
        }
        while (l1 <= r1) {
            // counts[copy[l1]]++;
            counts[copy[l1-s]] += count;
            indexs[i++] = copy[(l1++)-s];
        }
        while (l2 <= r2) indexs[i++] = copy[(l2++)-s];
    }
    // small -> big
    public void mergeSort(int[]  nums, int[] indexs, int l, int r, int[] counts) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeSort(nums, indexs, l, m, counts);
        mergeSort(nums, indexs, m+1, r, counts);
        merge2(nums, indexs, l, m, m+1, r, counts);
    }
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] indexs = new int[n];
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }
        int[] counts = new int[n];
        mergeSort(nums, indexs, 0, n-1, counts);
        // System.out.println(Arrays.toString(indexs));
        List<Integer> ret = new ArrayList<>(n);
        for (int c : counts) {
            ret.add(c);
        }
        return ret;
    }
    public static void main(String[] args) {
        int[] nums1 = {5,2,6,1};  // 2 1 1 0
        int[] nums2 = {6,5,4,3};
        int[] nums3 = {5,2};
        int[] nums4 = {1,2};
        P315CountOfSmallerNumbersAfterSelf p315 = new P315CountOfSmallerNumbersAfterSelf();
        p315.countSmaller(nums1);
        p315.countSmaller(nums2);
        System.out.println(p315.countSmaller(nums1));
        System.out.println(p315.countSmaller(nums2));
        System.out.println(p315.countSmaller(nums3));
        System.out.println(p315.countSmaller(nums4));
    }
}