package bisearch;

/**
 * Title: 69. x 的平方根
 * Desc: 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 注意：
 *      整数的溢出 需要强制转化成 Long
 * Created by Myth on 8/24/2019
 */
public class P69SqrtX {
    public int mySqrt(int x) {
        int l = 0;
        int r = x / 2 + 1;
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            // System.out.println(m);
            if ((long)m*m <= x) l = m + 1;
            else r = m;
        }
//        System.out.println(l);
        if ((long)l * l > x) return  l - 1;
        else return l;
    }

    public static void main(String[] args) {
        P69SqrtX p69 = new P69SqrtX();
//        System.out.println(p69.mySqrt(1));
//        System.out.println(p69.mySqrt(2));
//        System.out.println(p69.mySqrt(3));
//        System.out.println(p69.mySqrt(4));
//        System.out.println(p69.mySqrt(8));
//        System.out.println(p69.mySqrt(9));
//        System.out.println(p69.mySqrt(100));
//        System.out.println(p69.mySqrt(101));
        System.out.println(p69.mySqrt(2147395599));  // 46339
        System.out.println(p69.mySqrt(2147395600));  // 46340
        System.out.println(p69.mySqrt(2147483647));  // 46340
    }
}
