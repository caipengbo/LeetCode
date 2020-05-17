package graph.unionfind;

/**
 * Title: 990.等式方程的可满足性
 * Desc: 并查集
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
