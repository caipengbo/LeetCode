package math.bit;

/**
 * Title: 268. 缺失的数字
 * Desc: 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * Created by Myth-Lab on 10/30/2019
 */
public class P268MissingNumber {
    public int missingNumber(int[] nums) {
        boolean[] counts = new boolean[nums.length+1];
        for (int num : nums) {
            counts[num] = true;
        }
        for (int i = 0; i < counts.length; i++) {
            if (!counts[i]) return i;
        }
        return 0;
    }
    // 下标 1和1对应，说明异或的时候会等于0，落单的那个会等于最后返回
    // n+1个数字中挑选n个，未缺失的数在 [0..n]和数组中各出现一次，因此异或后得到 0。
    // 而缺失的数字只在 [0..n]中出现了一次，在数组中没有出现，因此最终的异或结果即为这个缺失的数字。
    public int missingNumber2(int[] nums) {
        int ret = nums.length;  // 注意初值

        for (int i = 0; i < nums.length; i++) {
            ret = (nums[i] ^ i ^ ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(999^999);
    }
}
