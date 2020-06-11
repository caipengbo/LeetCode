package math.geometry;

import java.util.HashMap;

/**
 * Title: 149. 直线上最多的点数
 * Desc: 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * Created by Myth-Lab on 11/1/2019
 */
public class P149MaxPointsOnLine {
    // 点斜式
    // 思路：对于每一个点：统计其他点和这个点的斜率，斜率相同（或者是同一个点）的肯定是在同一个点上
    private int getGCD(int a, int b) {
        if (b == 0) return a;
        return getGCD(b, a%b);
    }
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            // 只与之后的点进行(使用Double会有精度的问题，斜率使用最简分数的形式存：转化成string 分子/分母)
            HashMap<String, Integer> hashMap = new HashMap<>();
            int sameCount = 0;
            for (int j = i+1; j < n; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    sameCount++;
                } else if (points[i][0] == points[j][0]) {  // 斜率为无限大, key为INF
                    hashMap.put("INF", hashMap.getOrDefault("INF", 1)+1);
                } else if ((points[i][1] == points[j][1])) {  // 斜率为0的时候
                    hashMap.put("0", hashMap.getOrDefault("0", 1)+1);
                } else {
                    int x = points[i][0] - points[j][0], y = points[i][1] - points[j][1];
                    int gcd = getGCD(x, y);
                    // 注意此处：无论正负（一正一负，正在上，正在下），key的结果都会保持一致
                    String key = y/gcd + "/" + x/gcd;
                    hashMap.put(key, hashMap.getOrDefault(key, 1)+1);
                }
            }
            int curMaxSameLine = sameCount+1;
            for (String key : hashMap.keySet()) {
                curMaxSameLine = Math.max(curMaxSameLine, hashMap.get(key)+sameCount);
            }
            ret = Math.max(ret, curMaxSameLine);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] points = {{2,3},{3,3},{-5,3}};
        int[][] points2 = {{1,1},{1,1},{1,1}};
        int[][] points3 = {{0,0},{94911151,94911150},{94911152,94911151}};
        P149MaxPointsOnLine p149 = new P149MaxPointsOnLine();
        System.out.println(p149.maxPoints(points3));
        double a = 0, b = -1;
        System.out.println(a/b);
    }
}
