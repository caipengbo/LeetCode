package sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 350. 两数组的交集 2
 * Desc:
 * Created by Myth-PC on 2019-10-19
 */
public class P350IntersectionOfTwoArrays2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new LinkedList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            }
            else if (nums1[i] > nums2[j]) j++;
            else i++;
        }
        int[] ret = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            ret[k] = list.get(k);
        }
        return ret;
    }
}
