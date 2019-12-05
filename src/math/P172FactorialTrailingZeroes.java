package math;

/**
 * Title: 172. 阶乘后的零(进阶793题)
 * Desc: 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * Created by Myth-Lab on 11/3/2019
 */
public class P172FactorialTrailingZeroes {
    // 难点：思维转换
    // 为什么会产生0, 有10: 2*5, 所有有多少<2,5>对，就有多少0。2出现的次数肯定比5多，所以只算5出现的次数就行，但是注意25 和 25 * 25均出现不只一次5
    public int trailingZeroes(int n) {
        int ret = 0;
        while (n >= 5) {
            ret += n / 5;
            n /= 5;
        }
        return ret;
    }

}
