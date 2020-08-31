package graph.unionfind;

/**
 * Title: 990.等式方程的可满足性
 * Desc: 并查集，给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
 * 并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
 * Created by Myth-Lab on 10/27/2019
 */
public class P990SatisfiabilityOfEqualityEquations {
    // 思路：==在一个区域   != 不在一个集合
    public int find(int i, int[] disjoint) {
        while (disjoint[i] != i) {
            disjoint[i] = disjoint[disjoint[i]];  // 路径压缩
            i = disjoint[i];
        }
        return i;
    }
    public void union(int i, int j, int[] disjoint) {
        int iParent = find(i, disjoint);
        int jParent = find(j, disjoint);
        if (iParent != jParent) {
            disjoint[iParent] = jParent;
        }
    }
    public boolean equationsPossible(String[] equations) {
        int[] disjoint = new int[26];
        for (int i = 0; i < 26; i++) {
            disjoint[i] = i;
        }
        for (String eq : equations) {
            if (eq.charAt(1) == '=') union((int)eq.charAt(0)-'a', (int)eq.charAt(3)-'a', disjoint);
        }
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                int iParent = find((int)eq.charAt(0)-'a', disjoint);
                int jParent = find((int)eq.charAt(3)-'a', disjoint);
                if (iParent == jParent) return false;
            }
        }
        return true;
    }
}
