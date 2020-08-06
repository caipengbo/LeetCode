package search.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Title: 505. 迷宫2 
 * Desc: 修改题意版： 棋盘上1是障碍物，0是陆地，问如何能最小的步数到达终点(start -> destination)
 * Created by Myth-MBP on 06/08/2020 in VSCode
 */

public class P505TheMaze2 {

    public static int shortestDistance(int[][] board, int[] start, int[] end) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return -1;
        }
        int n = board.length, m = board[0].length;
        if (start[0] < 0 || start[0] >= n || start[1] < 0 || start[1] >= m) {
            return -1;
        }
        if (end[0] < 0 || end[0] >= n || end[1] < 0 || end[1] >= m) {
            return -1;
        }
        // 起始点都不在障碍物上哦
        int[][] dis = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        dis[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == end[0] && cur[1] == end[1]) {
                break;
            }
            board[cur[0]][cur[1]] = 1;
            if (cur[0]-1 >= 0 && board[cur[0]-1][cur[1]] != 1) {
                queue.add(new int[]{cur[0]-1, cur[1]});
                dis[cur[0]-1][cur[1]] = dis[cur[0]][cur[1]] + 1;
                board[cur[0]-1][cur[1]] = 1;
            }
            if (cur[0]+1 < n && board[cur[0]+1][cur[1]] != 1) {
                queue.add(new int[]{cur[0]+1, cur[1]});
                dis[cur[0]+1][cur[1]] = dis[cur[0]][cur[1]] + 1;
                board[cur[0]+1][cur[1]] = 1;
            }
            if (cur[1]-1 >= 0 && board[cur[0]][cur[1]-1] != 1) {
                queue.add(new int[]{cur[0], cur[1]-1});
                dis[cur[0]][cur[1]-1] = dis[cur[0]][cur[1]] + 1;
                board[cur[0]][cur[1]-1] = 1;
            }
            if (cur[1]+1 < m && board[cur[0]][cur[1]+1] != 1) {
                queue.add(new int[]{cur[0], cur[1]+1});
                dis[cur[0]][cur[1]+1] = dis[cur[0]][cur[1]] + 1;
                board[cur[0]][cur[1]+1] = 1;
            }
        }
        return dis[end[0]][end[1]] == Integer.MAX_VALUE ? -1 : dis[end[0]][end[1]];
    }
    public static void main(String[] args) {
        int[][] board = {{0,0,1,0,0}, {0,0,0,0,0}, {0,0,0,1,0}, {1,1,0,1,1}, {0,0,0,0,0}};
        System.out.println(P505TheMaze2.shortestDistance(board, new int[]{0, 4}, new int[]{4, 4}));
    }
}