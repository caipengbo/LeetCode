package math.sampling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Title:  528. 按权重随机选择
 * Desc: 加权随机采样，给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，
 * 请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i] 成正比。
 * Created by Myth-Lab on 11/9/2019
 */
public class P528RandomPickWithWeight {
    private List<Integer> wSum;  // 保存前缀和
    private Random random;
    int sum;
    private int[] w;
    public P528RandomPickWithWeight(int[] w) {
        wSum = new ArrayList<>();
        sum = 0;
        for (int x : w) {
            sum += x;
            wSum.add(sum);
        }
        random = new Random();
        // A_res算法
        this.w = w;
    }
    // 简单方法，前缀和 = 随机的概率： 应对pick调用次数较多但数据量不大的情况
    public int pickIndex() {
        int x = random.nextInt(sum);  // 注意此处如何取随机
        int l = 0, r = wSum.size()-1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (wSum.get(m) > x) r = m;   // >= 会报错（相等时候往右取）
            else l = m + 1;
        }
        return l;
    }

    // A-Res 算法（蓄水池的加权版本）：应对pick调用次数较少但数据量特别大以及数据流的情况
    public int pickIndex2() {
        int i = 0;
        double max = -1.0;
        for (int j = 0; j < w.length; j++) {
            double k = Math.pow(random.nextDouble(), 1.0/(double)w[j]);
            if (k > max) {
                i = j;
                max = k;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] w = {1, 3};
        P528RandomPickWithWeight p525 = new P528RandomPickWithWeight(w);
        System.out.println(p525.pickIndex2());
        System.out.println(p525.pickIndex2());
        System.out.println(p525.pickIndex2());
    }
}
