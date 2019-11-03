package math;

/**
 * Title: 793. 阶乘函数后K个零（在172题的基础之上）
 * Desc: f(x) 是 x! 末尾是0的数量。（回想一下 x! = 1 * 2 * 3 * ... * x，且0! = 1）
 *
 * 例如， f(3) = 0 ，因为3! = 6的末尾没有0；而 f(11) = 2 ，因为11!= 39916800末端有2个0。给定 K，找出多少个非负整数x ，有 f(x) = K 的性质。
 * Created by Myth-Lab on 11/3/2019
 */
public class P793PreimageSizeFactorialZeroesFunction {
    // 二分查找
    // K 的取值可以为0 1 2 3 4 6 7 8 9 10 12（结合172题，想一想为什么没有5 和 11）
    // 本题的重点就是求这些无效值，如果K是无效值，那么就返回0，是有效值就返回5（只有0和5两个答案）
    // 二分查找
    public int preimageSizeFZF(int K) {
        long l = 0, r = 5 * (K + 1);  // Why use K+1?
        long k = -1;
        while (l < r) {
            long m = l + (r - l) / 2;
            k = countTrailingZeroes(m);
            if (k > K) {
                r = m;
            } else if (k < K) {
                l = m + 1;
            } else return 5;
        }
        return 0;
    }
    public long countTrailingZeroes(long n) {
        long ret = 0;
        while (n >= 5) {
            ret += n / 5;
            n /= 5;
        }
        return ret;
    }

    public static void main(String[] args) {
        int a = 1000000000;
        long b = 5 * (a+1);  // 溢出之后才转换long
        long c = 5L * (a+1);
        System.out.println(b);
        System.out.println(c);
    }
}
