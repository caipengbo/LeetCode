package graph.dfs;

import java.util.*;

/**
 * Title: 133. 克隆图
 * Desc: 给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。
 * Created by Myth-Lab on 10/22/2019
 */
public class P133CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    // 图的遍历
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }
    // 如何终止？ 使用 Map<oldNode, newNode>
    private Node dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) return map.get(node);
        Node cloneNode = new Node(node.val, new ArrayList<>(node.neighbors.size()));
        // 先将节点加入 map（有可能是个环）
        map.put(node, cloneNode);
        for (int i = 0; i < node.neighbors.size(); i++) {
            cloneNode.neighbors.add(dfs(node.neighbors.get(i), map));
        }
        return cloneNode;
    }
    // BFS 一样，也需要 Map 存储节点
    // 难点：如何添加 neighbors
    private Node bfs(Node node, Map<Node, Node> map) {
        if (node == null) return null;
        Queue<Node> queue = new LinkedList<>();
        map.put(node, new Node(node.val, new ArrayList<>(node.neighbors.size())));
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            for (Node node1 : curNode.neighbors) {
                if (!map.containsKey(node1)) {
                    map.put(node1, new Node(node1.val, new ArrayList<>(node1.neighbors.size())));
                    queue.add(node1);
                }
                map.get(curNode).neighbors.add(map.get(node1));
            }
        }
        return map.get(node);
    }
}
