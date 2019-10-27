package graph;

/**
 * Title: 547. 朋友圈（本题和200题一模一样）
 * Desc: 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
 * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。
 * 你必须输出所有学生中的已知的朋友圈总数。
 *
 * Created by Myth-Lab on 10/27/2019
 */
public class P547FriendCircles {
    // 并查集解法
    public int find2(int i, int[] disjoint) {
        while (disjoint[i] != i) {
            i = disjoint[i];
        }
        return i;
    }
    // 路径压缩
    public int find(int i, int[] disjoint) {
        while (disjoint[i] != i) {
            disjoint[i] = disjoint[disjoint[i]];  // 路径压缩
            i = disjoint[i];
        }
        return i;
    }
    public boolean union(int i, int j, int[] disjoint) {
        int iParent = find(i, disjoint);
        int jParent = find(j, disjoint);
        if (iParent != jParent) {
            disjoint[iParent] = jParent;
            return true;
        }
        return false;
    }
    public int findCircleNum(int[][] M) {
        if (M.length == 0 || M[0].length == 0) return 0;
        int n = M.length, m = M[0].length;
        int[] disjoint = new int[n];
        for (int i = 0; i < n; i++) {
            disjoint[i] = i;  // 每个结点的父结点都是他自己
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i != j && M[i][j] != 0) union(i, j, disjoint);
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (disjoint[i] == i) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        P547FriendCircles p547 = new P547FriendCircles();
        int[][] M = {{1,1,0},
                    {1,1,0},
                     {0,0,1}};
        System.out.println(p547.findCircleNum(M));
    }
}
