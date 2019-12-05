package sort;

import java.util.*;

/**
 * Title: 179. 最大数
 * Desc: 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * Created by Myth-Lab on 10/17/2019
 */
public class P179LargestNumber {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length < 1) return "0";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        // 如何写规则
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        if ("0".equals(strs[0])) return "0";
        StringBuilder stringBuilder = new StringBuilder();
        for (String numString : strs) {
            stringBuilder.append(numString);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        P179LargestNumber p179 = new P179LargestNumber();
        int[] nums = {0, 0};
        System.out.println(p179.largestNumber(nums));
    }
}
