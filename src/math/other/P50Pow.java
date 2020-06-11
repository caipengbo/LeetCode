package math.other;

/**
 * Title: 50. Pow(x,n)
 * Desc: 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * Created by Myth-Lab on 10/31/2019
 */
public class P50Pow {
    // 二分，注意N正负  —— 快速幂算法
    public double myPow(double x, int n) {
        // 边界一定要注意！！
        if (x == 1.0 || n == 0) return 1;
        if (x == -1.0) return ((n&1)==0) ? 1 : -1;
        if (n == (1<<31)) return 0;
        if (n > 0) return pow(x, n);
        else return 1.0/pow(x, -n);
    }
    // 递归会导致栈溢出
    private double pow(double x, int posN) {
        if (posN == 0) return 1.0;
        if (posN == 1) return x;
        double temp = 0;
        if ((posN&1) == 0) {
            temp = pow(x, posN/2);
            return temp*temp;
        } else {
            temp = pow(x, (posN-1)/2);
            return temp*temp*x;
        }
    }
    // 如何转化成 循环？
    // 指数每次减半，底数每次增加一倍，指数是奇数的时候最终结果要乘一个（落单的）当前底数
    private double powIter(double x, int posN) {
        double ret = 1.0;
        while (posN > 0) {
            if ((posN&1)==0) {  // 偶数
                posN = (posN >> 2);
                x = x * x;  // 底数每次增加
            } else {
                ret *= x;
                posN = ((posN - 1) >> 2);
                x = x * x;
            }
        }
        return ret;
    }

}
