package sort;

import java.util.Arrays;

/**
 * Title: 1122. 数组的相对排序
 * Desc: 给你两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * 计数排序
 * Created by Myth-Lab on 10/20/2019
 */
public class P1122RelativeSortArray {
    // 自己的笨方法
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] ret = new int[arr1.length];
        boolean[] used = new boolean[arr1.length];
        int cur = 0;
        Arrays.sort(arr1);
        for(int i = 0; i < arr2.length; i++) {
            for(int j = 0; j < arr1.length; j++) {
                if(!used[j] && arr1[j] == arr2[i]) {
                    used[j] = true;
                    ret[cur++] = arr2[i];
                }
            }
        }
        for(int j = 0; j < arr1.length; j++) {
            if(!used[j]) {
                used[j] = true;
                ret[cur++] = arr1[j];
            }
        }
        return ret;
    }
    // arr1.length, arr2.length <= 1000
    // 0 <= arr1[i], arr2[i] <= 1000
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int[] map = new int[1001];
        for (int i = 0; i < arr1.length; i++) {
            map[arr1[i]]++;
        }
        int j = 0;
        for (int i = 0; i < arr2.length; i++) {
            for (int k = 0; k < map[arr2[i]]; k++) {
                arr1[j++] = arr2[i];
            }
            map[arr2[i]] = 0;
        }
        for (int i = 0; i < 1001; i++) {
            for (int k = 0; k < map[i]; k++) {
                arr1[j++] = i;
            }
        }
        return arr1;
    }
}
