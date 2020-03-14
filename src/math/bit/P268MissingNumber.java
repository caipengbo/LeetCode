package math.bit;

/**
 * Title: 268. 缺失的数字
 * Desc:
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
    // 3 0 1        0 1 3
    //            3 0 1 2
    public int missingNumber2(int[] nums) {
        int ret = nums.length;  //

        for (int i = 0; i < nums.length; i++) {
            ret = (nums[i] ^ i ^ ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(999^999);
    }
}
