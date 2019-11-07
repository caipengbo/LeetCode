package math;

/**
 * Title: 231. 2的幂
 * Desc: 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * Created by Myth-Lab on 11/7/2019
 */
public class P231PowerOfTwo {
    // 判断是否仅有一位为1: n减1 & n 会把末尾的1变成0
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return  (((n-1)&n) == 0);
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
    }
}
