package datastructure.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

public class P85MaximalRectangle {

    // 1. 转换成84题
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length, m = matrix[0].length;
        int max = 0;
        int[][] heights = new int[n][m];
        // 转化成从上到下的连续高度数组
        for (int j = 0; j < m; j++) {
            if (matrix[0][j] == '1') {
                heights[0][j] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    heights[i][j] = heights[i-1][j]+1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(max, largestRectangleArea(heights[i]));
        }
        return max;
    }
    // 
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, max = 0;
        Stack<Integer> increaseStack = new Stack<>();  // 保存下标
        // 递增栈，While peek > cur, loop pop, update max
        int cur, peek, w;
        for (int i = 0; i <= n; i++) {
            cur = (i == n ? 0 : heights[i]);  // Add 0 in last step
            while (!increaseStack.empty() && heights[increaseStack.peek()] > cur) {
                peek = increaseStack.pop();
                
                w = increaseStack.isEmpty() ? i : (i-increaseStack.peek()-1);  // 举例【4 2】
                max = Math.max(max, w * heights[peek]);  // 一减小就计算
            }
            increaseStack.push(i);
        }

        return max;
    }

    // 2. DP: 求每个点位的 h, l, r
    public int maximalRectangle2(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        // 滚动数组
        int[] left = new int[n]; // initialize left as the leftmost boundary possible
        int[] right = new int[n];
        int[] height = new int[n];

        Arrays.fill(right, n); // initialize right as the rightmost boundary possible

        int maxarea = 0;
        for(int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            // update height
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            // update left
            for(int j=0; j<n; j++) {
                if(matrix[i][j]=='1') left[j]=Math.max(left[j],cur_left);
                else {left[j]=0; cur_left=j+1;}
            }
            // update right
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                else {right[j] = n; cur_right = j;}    
            }
            // update area
            for(int j = 0; j < n; j++) {
                maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxarea;
    }
}