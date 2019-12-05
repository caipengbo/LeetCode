package graph;

/**
 * Title: 684. 冗余连接
 * Desc: 无向图，去除一个环上的边，使得图变成树
 * 如果有多个答案，则返回二维数组中最后出现的边。
 * Created by Myth-Lab on 10/27/2019
 */
public class P684RedundantConnection {
    // 并查集
    // 如何确保是最后的点
    public int find(int i, int[] disjoint) {
        while (disjoint[i] != i) {
            i = find(disjoint[i], disjoint);
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
    public int[] findRedundantConnection(int[][] edges) {
        // 输入的二维数组大小在 3 到 1000。
        int n = edges.length, m = edges[0].length;
        int[] disjoint = new int[n+1];
        for (int i = 1; i <= n; i++) {
            disjoint[i] = i;  // 每个结点的父结点都是他自己
        }
        // 如何确保是最后出现的边
        for (int i = 0; i < n; i++) {
            int[]uv = edges[i];
            if (!union(uv[0], uv[1], disjoint)) return uv;
        }
        return new int[0];
    }
}
