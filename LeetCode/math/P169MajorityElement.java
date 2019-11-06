package math;

import java.util.HashMap;

/**
 * Title: 169. 求众数
 * Desc: 输入的数组一定存在众数
 * Created by Myth-Lab on 11/6/2019
 */
public class P169MajorityElement {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key)  > nums.length/2) return key;
        }
        return 0;
    }
    // Boyer-Moore投票算法（已知一定存在众数）
    public int majorityElement2(int[] nums) {
        int major = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == major) count++;
            else {
                count--;
                if (count <= 0) {
                    major = nums[i+1];
                    count = 0;
                }
            }
        }
        return major;
    }
    // 精简写法
    public int majorityElement21(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

}
