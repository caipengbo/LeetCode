package contest;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * Title:
 * Desc:
 * Created by Myth on 9/24/2019
 */
public class FallContest2019 {
    public int game(int[] guess, int[] answer) {
        int len = guess.length;
        if (len == 0) return 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (guess[i] == answer[i]) count++;
        }
        return count;
    }
    public int[] fraction(int[] cont) {
        int len = cont.length;
        if (len == 1) return new int[]{cont[0], 1};
        int pre = 0;
        int[] ret = new int[]{0, 1};
        int temp;
        for (int i = len-1; i >= 0; i--) {
             // 通分
            temp = ret[1];
            ret[1] = ret[1]*cont[i] + ret[0];
            ret[0] = temp;
        }
        temp = ret[0];
        ret[0] = ret[1];
        ret[1] = temp;
        // 辗转相除
        int m = divisor(ret[0], ret[1]);
        ret[0] = ret[0] / m;
        ret[1] = ret[1] / m;
        return ret;
    }
    int divisor(int m,int n)
    {
        if (m % n == 0) {
            return n;
        }
        else {
            return divisor(n,m % n);
        }
    }
    class Pos {
        public int x;
        public int y;
        Pos(){
        }
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void set(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj instanceof Pos) return (this.x == ((Pos) obj).x && this.y == ((Pos) obj).y);
            return false;
        }
    }
    // 第三题
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        Set<Pair> set = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            set.add(new Pair(obstacles[i][0], obstacles[i][1]));
        }
        int curX = 0, curY = 0;
        while (true) {
            for (int i = 0; i < command.length(); i++) {
                if (command.charAt(i) == 'U') curY += 1;
                if (command.charAt(i) == 'R') curX += 1;
                if (curX == x && curY == y) return true;
                if (set.contains(new Pair(curX, curY))) return false;
                if (curX > x && curY > y) return false;  // 出界
            }
        }
    }
    public void test() {
        Set<Pair> set = new HashSet<>();
        set.add(new Pair(0,1));
        set.add(new Pair(0,1));
        set.add(new Pair(0,2));
        return;
    }
    // 第4题
    int maxNum;
    public int domino(int n, int m, int[][] broken) {
        boolean[][] bro = new boolean[n][m];
        boolean[][] used = new boolean[n][m];
        for (int i = 0; i < broken.length; i++) {
            bro[broken[i][0]][broken[i][1]] = true;
        }
        maxNum = 0;
        backtracking(n, m, bro, used, 0, 0, 0);
        return maxNum;
    }
    public void backtracking(int n, int m, boolean[][] broken, boolean[][] used, int x, int y, int count) {
        if (count > maxNum) maxNum = count;
        for (int i = x; i < n; i++) {
            for (int j = y; j < m; j++) {
                if (broken[i][j] || used[i][j]) continue;
                used[i][j] = true;
                // 横着放
                if (j < m-1 && !broken[i][j+1] && !used[i][j+1]) {
                    used[i][j+1] = true;
                    backtracking(n, m, broken, used, x, y+2, count+1);
                    used[i][j+1] = false;
                }
                // 竖着放
                if (i < n-1 && !broken[i+1][j] && !used[i+1][j]) {
                    used[i+1][j] = true;
                    backtracking(n, m, broken, used, x, y+1, count+1);
                    used[i+1][j] = false;
                }
                used[i][j] = false;
            }
            y = 0;
        }
    }


    public static void main(String[] args) {
        FallContest2019 p = new FallContest2019();
        int n = 2, m = 3;
        int[][] broken = {{1,0}, {1,1}};
        int[][] broken2 = {};
    }
}
