package graph;

import java.util.*;

/**
 * Title: 最短路径算法（最短路径树 SPT: Shortest Path Tree）
 * Desc: Dijkstra算法（单源，无负权）（朴素、堆优化版）, Floyd算法（多源，无负权）， Bellman-Ford算法（单源，可以判断负权回路）（队列优化：SPFA）
 * Created by Myth-Lab on 10/25/2019
 */
public class ShortestPath {
    // Dijkstra：单源最短路径：src到其他各点的最短距离
    // 使用邻接矩阵表示图(无负权)
    // 返回值: {各个顶点编号, 对应距离}}
    public HashMap<Integer, Integer> dijkstra(int[][] graph, int src) {
        int vertexNum = graph.length;
        PriorityQueue<int[]> distanceHeap = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);  // 需要每次取最小的，使用最小堆
        boolean[] visited = new boolean[vertexNum];  // 已经加入 SPT 的顶点
        HashMap<Integer, Integer> ret = new HashMap<>();
        visited[src] = true;
        ret.put(src, 0);
        for (int i = 0; i < vertexNum; i++) {
            if (graph[src][i] != 0) distanceHeap.add(new int[]{i, graph[src][i]});
            else distanceHeap.add(new int[]{i, Integer.MAX_VALUE});
        }
        while (!distanceHeap.isEmpty()) {
            int[] minVertex = distanceHeap.poll();
            if (visited[minVertex[0]]) continue;
            ret.put(minVertex[0], minVertex[1]);
            visited[minVertex[0]] = true;
            // 更新distanceHeap
            for (int[] vertex : distanceHeap) {
                if (graph[minVertex[0]][vertex[0]] != 0) {  // 结果中不带0
                    vertex[1] = Math.min(vertex[1], minVertex[1]+graph[minVertex[0]][vertex[0]]);
                }
            }
        }
        return ret;
    }


    public static void main(String[] args) {
        //对应的图 https://user-images.githubusercontent.com/19643013/67555628-3d4ffd80-f744-11e9-9ce3-2eac2ca3fc9b.jpg
        int[][] graph = new int[][] {
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        ShortestPath shortestPath = new ShortestPath();
        System.out.println(shortestPath.dijkstra(graph, 8).toString());
    }
}
