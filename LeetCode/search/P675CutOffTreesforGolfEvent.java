package search;

import java.util.*;

/**
 * Title: 675. 为高尔夫比赛砍树
 * Desc: 在一个二维数组上寻找一条增序的砍树路径（0表示障碍，1表示地面，>1的表示树的高度）
 * https://leetcode-cn.com/problems/cut-off-trees-for-golf-event/
 * Created by Myth on 7/30/2019
 */
public class P675CutOffTreesforGolfEvent {
    // 思路，使用堆（PriorityQueue）找到所有要砍的树，然后使用BFS计算每一次砍树要走的步数
    private int bfs(List<List<Integer>> forest, int m, int n, int[] start, int[] goal) {
        boolean[][] used = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        used[start[0]][start[1]] = true;
        int step = -1; // step 放在前面赋初值为-1，放在后面赋初值为0
        int size;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int r, c;
        while (!queue.isEmpty()) {
            step++;
            size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == goal[0] && cur[1] == goal[1]) return step;
                for (int[] dir : dirs) {
                    r = cur[0] + dir[0];
                    c = cur[1] + dir[1];
                    if (r < 0 || r >= m || c < 0 || c >= n || used[r][c] || forest.get(r).get(c) == 0) continue;
                    used[r][c] = true;
                    queue.add(new int[]{r, c});
                }
            }
        }
        return -1;
    }
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size(), n = forest.get(0).size();
        // 使用lambda表达式最小堆, 其中数组 (x, y, height)
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    priorityQueue.add(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        int[] start = new int[]{0, 0};
        int step;
        int stepSum = 0;
        while (!priorityQueue.isEmpty()) {
            int[] next = priorityQueue.poll();
            step = bfs(forest, m, n, start, next);
            if (step == -1) return -1;
            stepSum += step;
            start[0] = next[0];
            start[1] = next[1];
        }
        return stepSum;
    }

    public static void main(String[] args) {
        P675CutOffTreesforGolfEvent p675 = new P675CutOffTreesforGolfEvent();
        List<List<Integer>> forest = new ArrayList<>();
        List<Integer> row1 = Arrays.asList(1, 2, 3);
        List<Integer> row2 = Arrays.asList(0, 0, 0);
        List<Integer> row3 = Arrays.asList(7, 6, 5);
        forest.add(row1);
        forest.add(row2);
        forest.add(row3);
        System.out.println(p675.cutOffTree(forest));
    }
}
