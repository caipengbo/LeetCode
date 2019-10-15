package contest;

/**
 * Title: 一些有意思的题目（个人手写、收藏）
 * Desc:
 * Created by Myth-Lab on 10/15/2019
 */
public class OtherProblem {
    // 给出高山的坐标，求第一个能看到最高山峰的坐标（斜率，视野）
    // 测试数据 double[][] mountain = {{535, 347.9}, {1513, 444.4}, {2404.1, 690.3}, {3005.2, 437.1},{3762, 393.7}, {4602.6, 832.5}}; Ans: 2404.1
    public double firstHigh(double[][] mountain) {
        //找到最高
        int maxHighX = 0;
        for (int i = 0; i < mountain.length; i++) {
            if (mountain[maxHighX][1] < mountain[i][1]) maxHighX = i;
        }
        if (maxHighX == 0) return mountain[0][1];
        double delta = Float.MAX_VALUE;
        int ret = 0;
        for (int i = maxHighX-1; i >= 0; i--) {
            double temp = (mountain[maxHighX][1] - mountain[i][1]) / (mountain[maxHighX][0] - mountain[i][0]);
            if (temp < delta) {
                delta = temp;
                ret = i;
            }
        }
        return mountain[ret][0];
    }

    public static void main(String[] args) {

    }
}
