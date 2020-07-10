package twopointer.seq;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 15. 三数之和
 * Desc: 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a, b, c, 使得 a + b + c = 0 ?
 * 找出所有满足条件且不重复的三元组。
 * Created by Myth-Lab on 10/7/2019
 */
public class P15ThreeSum {
    // 难点: 三数之和 -> 两数之和(哈希、排序+双指针); 排除重复

    // 本题：排序+双指针 : val<target时low++; val>target时 high--; val==target时low++ & high--
    // 如何跳过重复
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new LinkedList<>();
        Integer iPre = null;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            if (nums[i] > 0) break;
            int j = i+1, k = nums.length-1, sum = 0 - nums[i];
            Integer jPre = null, kPre = null;
            if (iPre != null && iPre == nums[i]) {
                continue;  // 去重
            }
            iPre = nums[i];
            while (j < k) {
                if (nums[j] + nums[k] < sum) {
                    j++;
                }
                else if (nums[j] + nums[k] > sum) {
                    k--;
                } else {
                    if (jPre == null || (jPre != nums[j] && kPre != nums[k])) {
                        ret.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                    jPre = nums[j];
                    kPre = nums[k];
                    j++;
                    k--;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        P15ThreeSum p15 = new P15ThreeSum();
        int[] arr = {-2,0,1,1,2};
        int[] arr2 = {3,0,-2,-1,1,2};
        System.out.println(p15.threeSum(arr2));
    }
}
