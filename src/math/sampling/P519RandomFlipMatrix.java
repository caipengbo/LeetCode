package math.sampling;

import java.util.HashMap;
import java.util.Random;

/**
 * Title: 519. 随机翻转矩阵(随机抽样问题的改编版)
 * Desc: 题中给出一个 n 行 n 列的二维矩阵 (n_rows,n_cols)，且所有值被初始化为 0。
 * 要求编写一个 flip 函数，均匀随机的将矩阵中的 0 变为 1，并返回该值的位置下标 [row_id,col_id]；同样编写一个 reset 函数，将所有的值都重新置为 0。
 * 尽量最少调用随机函数 Math.random()，并且优化时间和空间复杂度。
 *
 * Created by Myth-Lab on 11/9/2019
 */
public class P519RandomFlipMatrix {

    private int row, col, n;
    private HashMap<Integer, Integer> posMap;
    private Random random;
    public P519RandomFlipMatrix(int n_rows, int n_cols) {
        row = n_rows;
        col = n_cols;
        n = row * col;
        posMap = new HashMap<>();
        random = new Random();
    }
    // 本题可以使用一个HashMap，来交换位置，本质上还是将坐标划分为两部分（已经变成1的和未变成1的）
    // 可以画图查看如何实现交换的！！！
    public int[] flip() {
        if (n < 0) return null;
        //随机选择一个下标
        int r = random.nextInt(n--);  // 位置
        int x = posMap.getOrDefault(r, r);  // 查看是否存在交换关系  // 实际内容
        // 原下标与尾部下标交换（使用map记录origin->tail交换关系）
        posMap.put(r, posMap.getOrDefault(n, n));
        return new int[]{x/col, x%col};
    }

    public void reset() {
        posMap = new HashMap<>();
        n = row * col;
    }
}
