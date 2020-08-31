package tree.bst;


/**
* Title: 96. 不同的二叉搜索树
* Desc: 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
* Created by Myth-MBP on 12/07/2020 in VSCode
*/
public class P96UniqueBST {
    // 二叉树的个数和序列内容无关，只和数列长度有关
    // G(n) = f(i, n) i = 0 -> n-1  // 代表以i为根，n代表长度
    // f(i,n) = G(i-1) + G(n-i)
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
    
        for (int i = 2; i <= n; ++i) {
          for (int j = 1; j <= i; ++j) {
            G[i] += G[j - 1] * G[i - j];
          }
        }
        return G[n];
      }
} 