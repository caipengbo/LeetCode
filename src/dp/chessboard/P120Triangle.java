package dp.chessboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 120. 三角形最小路径和
 * Desc: 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

 * Created by Myth on 12/06/2019 in VSCode
 */


public class P120Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size(), m;
        int[] dp = new int[n];  // 使用滚动数组优化
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            m = triangle.get(i).size();
            
            for (int j = m-1; j >= 0; j--) {
                if (j == 0) dp[j] = dp[0] + triangle.get(i).get(j);
                else if (j == m-1) dp[j] = dp[j-1] + triangle.get(i).get(j);
                else dp[j] = Math.min(dp[j], dp[j-1]) + triangle.get(i).get(j);
            }
            // For Debug
            for (int j = 0; j < n; j++) {
                System.out.print(dp[j] + ", ");
            }
            System.out.println();
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }
    public static void main(String[] args) {
        P120Triangle p120 = new P120Triangle();
        List<Integer> list0 = Arrays.asList(2);
        List<Integer> list1 = Arrays.asList(3, 4);
        List<Integer> list2 = Arrays.asList(6, 5, 7);
        List<Integer> list3 = Arrays.asList(4, 1, 8, 3);
        List<List<Integer>> triangle = new LinkedList<>();
        triangle.add(list0);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        System.out.println(p120.minimumTotal(triangle));
    }
}