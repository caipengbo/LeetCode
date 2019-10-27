package graph;

import util.IOUtil;

import java.util.*;

/**
 * Title: 399. 除法求值
 * Desc: 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。
 * 根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
 * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
 * Created by Myth-Lab on 10/26/2019
 */
public class P399EvaluateDivision {
    // 方法1: 构造图，然后使用 DFS 搜索
    private class Node {
        String var;
        Double mul;
        Node(String var, Double mul) {
            this.var = var;
            this.mul = mul;
        }
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 构建邻接表
        Map<String, List<Node>> adjMap = new HashMap<>();
        Map<String, Boolean> visitedMap = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            if (!visitedMap.containsKey(a)) visitedMap.put(a, false);
            if (!visitedMap.containsKey(b)) visitedMap.put(b, false);
            List<Node> adjList = adjMap.getOrDefault(a, new LinkedList<>());
            adjList.add(new Node(b, values[i]));
            adjMap.put(a, adjList);
            adjList = adjMap.getOrDefault(b, new LinkedList<>());
            adjList.add(new Node(a, 1.0 / values[i]));
            adjMap.put(b, adjList);
        }
        // DFS
        double[] ret = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            ret[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), adjMap, 1.0, visitedMap);
        }
        return ret;
    }
    private Double dfs(String var1, String var2, Map<String, List<Node>> adjMap, Double mul, Map<String, Boolean> visitedMap) {
        if (!adjMap.containsKey(var1) || !adjMap.containsKey(var2) || visitedMap.get(var1)) return -1.0;
        if (var1.equals(var2)) return mul;  // 注意此处
        visitedMap.put(var1, true);  // 记忆返回的位置
        Double ret = -1.0;
        for (Node node : adjMap.get(var1)) {
            // 难点：此处应该返回什么？？
            ret = dfs(node.var, var2, adjMap, mul*node.mul, visitedMap);
            if (!ret.equals(-1.0)) break;
        }
        visitedMap.put(var1, false);
        return ret;
    }
    // 方法2：多源最短路径问题
    // Floyd算法

    // 方法3：并查集(但是这里是一个有向有权图，所以需要储存子节点与父节点的倍数关系)
    // a/b  储存在parent中为： b:a
    // TODO:以下并查集代码错误
    private String find(String node, Map<String, String> parent) {
        if (!parent.containsKey(node)) {
            parent.put(node, node);
            return node;
        }
        while (!node.equals(parent.get(node))) {
            node = parent.get(node);
        }
        return node;
    }
    private Double getVal(String node, Map<String, String> parent, Map<String, Double> multiple) {
        Double ret = 1.0;
        while (!node.equals(parent.get(node))) {
            ret = ret * multiple.get(node);
            node = parent.get(node);
        }
        return ret;
    }
    private Double cal(String a, String b, Map<String, String> parent, Map<String, Double> multiple) {
        if (!parent.containsKey(a) || !parent.containsKey(b)) return -1.0;
        // 判断是否有相同的祖先
        // 没有相同祖先，返回-1.0
        if (!find(a, parent).equals(find(b, parent))) return -1.0;
        // 有相同祖先，求倍数(难点)，
        return getVal(b, parent, multiple)/ getVal(a, parent, multiple);
    }
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 节点的父节点
        Map<String, String> parent = new HashMap<>();
        // 节点到父节点的倍数
        Map<String, Double> multiple = new HashMap<>();
        // 储存关系
        // a/b代表 b的父节点是a,倍数是 2.0
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            if (!parent.containsKey(a)) {  // 保存所有出现过的节点
                parent.put(a, a);
                multiple.put(a, 1.0);
            }
            parent.put(b, a);
            multiple.put(b, values[i]);
        }
        double[] ret = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0), b = queries.get(i).get(1);
            ret[i] = cal(a, b, parent, multiple);
        }
        return ret;
    }
    /* 输入
    equations(方程式) = [ ["a", "b"], ["b", "c"] ],
    values(方程式结果) = [2.0, 3.0],
    queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     */
    public static void main(String[] args) {
        List<String> equa1 = Arrays.asList("a", "b");
        List<String> equa2 = Arrays.asList("b", "c");
        List<List<String>> equations =  new LinkedList<>();
        equations.add(equa1);
        equations.add(equa2);
        double[] values = {2.0, 3.0};
        List<String> query1 = Arrays.asList("a", "c");
        List<String> query2 = Arrays.asList("b", "a");
        List<String> query3 = Arrays.asList("a", "e");
        List<String> query4 = Arrays.asList("a", "a");
        List<List<String>> queries = new LinkedList<>();
        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        P399EvaluateDivision p399 = new P399EvaluateDivision();
        System.out.println(Arrays.toString(p399.calcEquation(equations, values, queries)));
    }
}
