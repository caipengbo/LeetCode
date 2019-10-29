package math;

/**
 * Title: 204. 计数质数
 * Desc: 统计所有小于非负整数 n 的质数的数量。
 * Created by Myth-Lab on 10/29/2019
 */
public class P204CountPrimes {
    // 判断一个数是否是素数
    private boolean isPrime2(int n) {
        if (n <= 1) return false;
        // for (int i = 2; i < n; i++) {
        // 优化 1. for (int i = 2; i < n/2; i++) {  // n不能被  number(>n/2的数整除)
        // if n is divisible by some number p, then n = p × q and since p ≤ q, we could derive that p ≤ √n.
        // for (int i = 2; i * i < n; i++) { n能被 p整除, 那n = p × q 因为 p ≤ q, 那么 p ≤ √n
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    public int countPrimes2(int n) {
        int count = 1;
        for (int i = 3; i < n; i+=2) {
            if (isPrime2(i)) count++;
        }
        return count;
    }
    // 埃拉托色尼筛选法
    // 素数筛法经常作为一道题的一部分用来打一定范围内素数表，（false代表是素数，true代表不是素数）
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i]) continue;
            // 是素数
            count++;
            // 优化：只筛选 比 i小的素数 与 i 的乘积
            for (int j = i*2; j < n; j+=i) {
                notPrime[j] = true;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        P204CountPrimes p204 = new P204CountPrimes();
        System.out.println(p204.countPrimes(1500000));
    }
}
