package math.bit;

/**
 * Title: 136.137 只出现一次的数字
 * Desc: 136.给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 137. 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * Created by Myth-Lab on 11/6/2019
 */
public class P136_137_260SingleNumber1_2_3 {
    // 除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
    // 异或
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret ^= nums[i];
        }
        return ret;
    }
    // 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
    // 出现三次，那么出现三次的位，和肯定能被3整除
    public int singleNumber2(int[] nums) {
        // 每一位
        int ret = 0;
        int mask, sum;
        for (int j = 0; j < 32; j++) {
            mask = (1 << j);
            sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += (nums[i] & mask) == 0 ? 0 : 1;
            }
            if (sum % 3 != 0) ret |= mask;  // 与掩码 相或 为某一位赋值
        }
        return ret;
    }
    // 其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素
    // 和136的进阶版，重点是如何把要求的这两个数字分开
    // 如果异或的话，最终结果是两个只出现一次的数的异或值（0和1异或才为1），找到某一个为1的位，然后根据这一位将数组中所有的元素分成两部分
    // 就转化成了136题
    public int[] singleNumber3(int[] nums) {
        int xor = 0, mask = 1;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }
        for (int i = 0; i < 32; i++) {
            mask <<= i;
            if ((xor & mask) != 0) break;  // 注意此处，一定不要用1 去判断，用0判断
        }
        int[] ret = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i]&mask) == 0) ret[0] ^= nums[i];
            else ret[1] ^= nums[i];
        }
        return ret;
    }

}
