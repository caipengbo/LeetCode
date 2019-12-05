package math.sampling;

import java.util.Random;

/**
 * Title: 使用rand7生成rand10(拒绝采样先做478题)
 * Desc: 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 *不要使用系统的 Math.random() 方法。
 *
 * Created by Myth-Lab on 11/9/2019
 */
public class P470Rand10UsingRand7 {
    public int rand7() {
        Random random = new Random();
        return random.nextInt(7)+1;
    }
    // 初次方法：该思路肯定不行：只能取整数，就少了很多数：return (int)((rand7()-1)/6.0 * 9.0 + 1.0);
    // 拒绝采样，条件概率，贝叶斯定理
    /*
    int seed7[7][7] = {
    {1 , 2 , 3 , 4 , 5 , 6 , 7},
    {8 , 9 , 10, 1 , 2 , 3 , 4},
    {5 , 6 , 7 , 8 , 9 , 10, 1},
    {2 , 3 , 4 , 5 , 6 , 7 , 8},
    {9 , 10, 1 , 2 , 3 , 4 , 5},
    {6 , 7 , 8 , 9 , 10, 0 , 0},
    {0 , 0 , 0 , 0 , 0 , 0 , 0}
    };
    // 如果在前40以内，1-10每一个数被取到的概率为1/10, 很典型的拒绝采样问题
     */
    public int rand10() {
        int i;
        do {
            i = (rand7()-1) * 7 + rand7();
        } while (i > 40);
        return i % 10 + 1;
    }
    // 拒绝采样：构造均匀分布，采样符合要求的，本题符合要求的就是可以映射到1-10的
}
