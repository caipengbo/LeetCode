package math.sampling;

import java.util.Random;

/**
 * Title: 398. 随机数索引
 * Desc: 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 * 注意: 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 *
 * Created by Myth-Lab on 11/8/2019
 */
public class P398RandomPickIndex {
    private int[] nums;
    private Random random;
    P398RandomPickIndex(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    public int pick(int target) {
        int ret = 0, cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (cnt == 0) ret = i;
                else if (random.nextInt(cnt+1) == 0) ret = i;
                cnt++;
            }
        }
        return ret;
    }
}
