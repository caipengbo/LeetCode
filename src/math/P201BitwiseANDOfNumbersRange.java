package math;

/**
 * Title: 201. 数字范围按位与
 * Desc: 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * Created by Myth-Lab on 11/7/2019
 */
public class P201BitwiseANDOfNumbersRange {
    // 判断哪些位没出现过0(以下代码超时)
    public int rangeBitwiseAndErr(int m, int n) {
        int ret = 0, mask;
        boolean flag;
        for (int i = 0; i < 32; i++) {
            mask = (1<<i);
            flag = true;
            for (int j = m; j <= n; j++) {
                if ((mask&j) == 0) {
                    flag = false;  // i 位出现 0
                    break;
                }
            }
            if (flag) ret |= mask;
        }
        return ret;
    }
    // 去最高位无变化的部分：对于5 6 7 ：0101   0110   0111  显然是 前边 01 部分未曾变换，后面的部分肯定是2^(len-前len)个数字
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}
