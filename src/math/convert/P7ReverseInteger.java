package math.convert;

/**
 * Title: 7. 整数反转
 * Desc: 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * Created by Myth-Lab on 10/30/2019
 */
public class P7ReverseInteger {
    // 求最后一位，然后每次*10
    // 难点：如何判断溢出
    public int reverse(int x) {
        int symbol;
        if (x > 0) {
            symbol = 1;
        } else {
            symbol = -1;
            x = -x;
        }
        int ret = 0;
        while (x != 0) {
            int last = x % 10;
            x = x / 10;
            // 错误的地方：正负的最后一位是不一样的，一个7 一个8，并且也没必要识别符号
            if (ret > Integer.MAX_VALUE/10 || (ret == Integer.MAX_VALUE/10 && last > 7)) return 0;
            ret = ret * 10 + last;
        }
        return symbol * ret;
    }
    public int reverse2(int x) {
        int ret = 0;
        while (x != 0) {
            int last = x % 10;
            x = x / 10;
            if (ret > Integer.MAX_VALUE/10 || (ret == Integer.MAX_VALUE/10 && last > 7)) return 0;
            if (ret < Integer.MIN_VALUE/10 || (ret == Integer.MIN_VALUE/10 && last < -8)) return 0;
            ret = ret * 10 + last;
        }
        return ret;
    }

    public static void main(String[] args) {

    }
}
