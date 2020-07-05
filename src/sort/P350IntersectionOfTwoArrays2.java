package sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 350. 两数组的交集 2
 * Desc: 给定两个数组，编写一个函数来计算它们的交集。
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。我们可以不考虑输出结果的顺序。
 * Created by Myth-PC on 2019-10-19
 */
public class P350IntersectionOfTwoArrays2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // 排好序就用双指针
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
    // 没拍好序就用Hash,将数目较小的数组存入Hash

    // 内存较小 —— 外排序
    // 对应进阶问题三，如果内存十分小，不足以将数组全部载入内存，
    // 那么必然也不能使用哈希这类费空间的算法，只能选用空间复杂度最小的算法，即解法一。
    // 但是解法一中需要改造，一般说排序算法都是针对于内部排序，
    // 一旦涉及到跟磁盘打交道（外部排序），则需要特殊的考虑。
    // 归并排序是天然适合外部排序的算法，可以将分割后的子数组写到单个文件中，
    // 归并时将小文件合并为更大的文件。当两个数组均排序完成生成两个大文件后，
    // 即可使用双指针遍历两个文件，如此可以使空间复杂度最低。


}
