package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: 399. 除法求值
 * Desc: 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。
 * 根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
 * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
 * Created by Myth-Lab on 10/26/2019
 */
public class P399EvaluateDivision {
    // 方法1: 构造图，然后使用 DFS 搜索
    //  略
    // 方法2：并查集(但是这里是一个有向有权图，所以需要储存子节点与父节点的倍数关系,还有方向性)
    // a/b  储存在parent中为： b:a
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
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
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

    }
}
