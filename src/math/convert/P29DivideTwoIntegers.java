package math.convert;

/**
 * Title: 29. 两数相除
 * Desc: 要求不使用乘法、除法和 mod 运算符,假设我们的环境只能存储 32 位有符号整数(不能使用long)
 * Created by Myth-Lab on 11/5/2019
 */
public class P29DivideTwoIntegers {
    // 只取整数部分
    // 思路，每次移位 * 2
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;  // 溢出返回最大值
        int n = Math.abs(dividend), b = Math.abs(divisor);
        int ret = 0, i;
        // 为何用n-b ?  边界：-2147483648/1
        while (n - b >= 0) {
            // 每次 b <<= 1
            i = 0;
            // 为何用n-(b<<i<<1): 2147483647 / 1
            // 注意里面是 (b << i << 1)  如何换成 b<<(i+1) 会出现问题 （i==31的时候）,因为左移大于32位的时候，先取余再移动
            while (0 <= n - (b<<i<<1)) i++;
            ret += (1 << i);
            n -= (b << i);
        }
        if ((dividend<0)^(divisor<0)) return -ret;
        return ret;
    }
    // 方法2
    public int divide2(int A, int B) {
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;
        int a = Math.abs(A), b = Math.abs(B), res = 0;
        for (int x = 31; x >= 0; x--)
            if ((a >>> x) - b >= 0) {
                res += 1 << x;
                a -= b << x;
            }
        return (A > 0) == (B > 0) ? res : -res;
    }

    public static void main(String[] args) {
        P29DivideTwoIntegers p29 = new P29DivideTwoIntegers();
        // System.out.println(p29.divide(-2147483648, 1));
        int a = Integer.MIN_VALUE - 2;
        int n = -2147483648;
        System.out.println(n-(1<<29<<1));
        System.out.println(n-(1<<30<<1));
        System.out.println(n-(1<<31<<1));
        System.out.println("****");
        System.out.println(n-(1<<(29+1)));
        System.out.println(n-(1<<(30+1)));
        System.out.println(n-(1<<(31+1)));
        System.out.println(n<<1);
        System.out.println(1 << 32);
        System.out.println(1 << 33);
        System.out.println(1 << 34);
    }
}
