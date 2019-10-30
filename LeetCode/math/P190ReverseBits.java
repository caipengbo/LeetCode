package math;

/**
 * Title: 190. 颠倒二进制位
 * Desc:
 * Created by Myth-Lab on 10/30/2019
 */
public class P190ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int mask = 1;  // mask左移32次
        int ret = 0, last;
        for (int i = 0; i < 32; i++) {
            last = (mask & n) == 0 ? 0 : 1;
            ret = (ret << 1) + last;
            mask = (mask << 1);
        }
        return ret;
    }
    public int reverseBits2(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            ret = (ret << 1) + (1 & n);
            n = (n >> 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        P190ReverseBits p190 = new P190ReverseBits();
        System.out.println(p190.reverseBits(5));
    }
}
