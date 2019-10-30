package math;

/**
 * Title: 最大公约数Greatest Common Divisor与最小公倍数Least Common Multiple
 * Desc: 常用算法：短除法、辗转相除法，更相减损法
 * Created by Myth-Lab on 10/29/2019
 */
public class GCD_LCM {
    // 暴力算法，从最小的数开始递减，直到找到最大公约数
    // 短除法每次使用公约数，直到所有公约数互为质数
    // 辗转相除法, 欧几里得算法 gcd(m,n) = gcd(n, m%n)
    public int euclid(int m, int n) {
        int mod = m % n;
        if (mod == 0) return n;
        else return euclid(n, mod);
    }
    public int chineseGCD(int m, int n) {
        if (m == n) return m;
        int twoCnt = 1;
        // 可半者半之
        while (m  % 2 == 0 && n % 2 == 0) {
            m = m / 2;
            n = n / 2;
            twoCnt *= 2;  // 2的次数
        }
        // 大 减 小， 差与小比较，相等最大公约数就是min*一开始除以的2的积；不相等，差和减数 迭代
        int min = Math.min(m, n), max = Math.max(m, n);
        while ((max - min) != min) {
            int val = max - min;
            if (val > min) {
                max = val;
            } else {
                max = min;
                min = val;
            }
        }
        return min*twoCnt;
    }

    // 最小公倍数
    // 两个数的乘积等于这两个数的最大公约数与最小公倍数的积 (m/gcd)*(n/gcd)=lcm
    private int lcm(int m, int n) {
        return (m*n)/(euclid(m, n));
    }

    public static void main(String[] args) {
        GCD_LCM gcdLcm = new GCD_LCM();
        System.out.println(gcdLcm.chineseGCD(221, 221));  // 13
        System.out.println(gcdLcm.lcm(5,7));
    }
}
