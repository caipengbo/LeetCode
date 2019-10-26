package graph;

/**
 * Title: 并查集
 * Desc: 并查集的基本概念，并查集的基本操作，并查集的优化：路径压缩和按秩合并
 * Created by Myth-Lab on 10/26/2019
 */
public class DisjointSet {
     // 并查集的初始化
    private final int N = 100;  // 假设100个点
    private int[] parent = new int[N];
    private int[] rank = new int[N];

    public void init() {
        for (int i = 0; i < N; i++) {
            parent[i] = i;  // 每个结点的父结点都是他自己
            rank[i] = 0;  // 秩为 0
        }
    }
    public int find(int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }
    // 带路径压缩的find(每一个节点指向他的祖先节点)
    // 使用递归，容易栈溢出
    public int find2(int i) {
        while (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return i;
    }
    public boolean union(int i, int j) {
        int iParent = find(i);
        int jParent = find(j);
        if (iParent != jParent) {
            parent[iParent] = jParent;
            return true;
        }
        return false;
    }
    // 按秩合并
    public boolean union2(int i, int j) {
        int iParent = find(i);
        int jParent = find(j);
        if (iParent == jParent) return false;
        if (rank[iParent] > rank[jParent]) {
            parent[jParent] = iParent;
        } else {
            parent[iParent] = jParent;
            if (rank[iParent] == rank[jParent]) rank[jParent]++;
        }
        return true;
    }


}
