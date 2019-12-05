package graph;

/**
 * Title: 785. 判断二分图
 * Desc: 二部图：节点分成两部分，每一条表都是分别来自两个集合；完全二部图，两个集合中的所有点两两都有边
 * Created by Myth-Lab on 10/28/2019
 */
public class P785IsGraphBipartite {
    // 染色法：相邻的节点未被染色，就染成不一样的颜色，如果被染色，判断是否是相同颜色（相同就跳过，不相同就证明不能分成二分图）
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) return false;
        int v = graph.length;
        int[] colors = new int[v];  // 0未被染色， 1黑  2白
        // 要考虑非连通图
        for (int i = 0; i < v; i++) {
            if (!dfs(graph, i, colors, 0)) return false;
        }
        return true;
    }
    private boolean dfs(int[][] graph, int i, int[] colors, int lastColor) {
        // 注意，被染色的就不要继续染色了（因为这是自底向上的，被染色的点，其相连的节点肯定被染色了）
        // 如果继续对被染色的节点染色，就会导致死循环
        if (colors[i] != 0) return colors[i] != lastColor;
        colors[i] = lastColor == 1 ? 2 : 1;
        for (int j = 0; j < graph[i].length; j++) {
            if (!dfs(graph, graph[i][j], colors, colors[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{},{3},{},{1},{}};
        P785IsGraphBipartite p785 = new P785IsGraphBipartite();
        System.out.println(p785.isBipartite(graph));
    }
}
