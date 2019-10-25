package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: 802. 找到最终的安全状态
 * Desc:
 * Created by Myth-Lab on 10/24/2019
 */
public class P802FindEventualSafeStates {
    // graph 是邻接表
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ret = new LinkedList<>();
        int[] flag = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!dfs(graph, i, flag)) ret.add(i);
        }
        System.out.println(Arrays.toString(flag));
        return ret;
    }
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        List<Integer> ret = new LinkedList<>();
        int[] flag = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            dfs(graph, i, flag);
        }
        for (int i = 0; i < graph.length; i++) {
            if (flag[i] == 1) ret.add(i);
        }
        return ret;
    }
    private boolean dfs(int[][] graph, int i, int[] flag) {
        if (flag[i] == -1) return true;
        if (flag[i] == 1) return false;
        flag[i] = -1;
        for (int adj : graph[i]) {
            if(dfs(graph, adj, flag)) return true;
        }
        flag[i] = 1;
        return false;
    }

    public static void main(String[] args) {
        P802FindEventualSafeStates p802 = new P802FindEventualSafeStates();
        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        p802.eventualSafeNodes(graph);
    }
}
