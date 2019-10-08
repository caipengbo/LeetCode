package twopointer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 18. 四数之和
 * Desc: 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ,
 * 使得 a + b + c + d 的值与 target 相等? 找出所有满足条件且不重复的四元组。
 * 和第15题一个思路
 * Created by Myth-Lab on 10/7/2019
 */
public class P18FourSum {
    // 双指针 + 去重
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        Integer aPre = null;
        Arrays.sort(nums);
        for (int a = 0; a < nums.length-3; a++) {
            if (nums[a] > 0 && nums[a] > target) break;  // 注意一定要加nums[a] > 0
            if (aPre != null && aPre == nums[a]) continue;
            aPre = nums[a];
            Integer bPre = null;
            for (int b = a+1; b < nums.length-2; b++) {
                if (nums[b] > 0 && nums[a] + nums[b] > target) break;
                if (bPre != null && bPre == nums[b]) continue;  // 去重
                bPre = nums[b];
                int c = b+1, d = nums.length-1, sum = target - (nums[a] + nums[b]);
                Integer cPre = null, dPre = null;
                while (c < d) {
                    if (nums[c] + nums[d] < sum) c++;
                    else if (nums[c] + nums[d] > sum) d--;
                    else {
                        if (cPre == null || (cPre != nums[c] && dPre != nums[d])) ret.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        cPre = nums[c];
                        dPre = nums[d];
                        c++;
                        d--;
                    }
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        P18FourSum p18 = new P18FourSum();
        int[] nums = {1,-2,-5,-4,-3,3,3,5};
        System.out.println(p18.fourSum(nums, -11));
    }
}
