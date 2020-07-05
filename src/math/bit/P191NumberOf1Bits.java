package math.bit;

/**
 * Title: 191. 位1的个数
 * Desc: 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 * Created by Myth-Lab on 11/7/2019
 */
public class P191NumberOf1Bits {
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += (n&1);
            n >>>= 1;  // >>> 最高位补 0  >> 最高位补1
        }
        return cnt;
    }
    // P231题
    // 数字-1与该数字相与，会将n二进制位的最后一个1变为0
    public int hammingWeight2(int n) {
        int cnt = 0;
        while (n != 0) {
            n &= (n-1);
            cnt++;
        }
        return cnt;
    }
}
