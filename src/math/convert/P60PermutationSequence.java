package math.convert;

import java.util.ArrayList;

/**
 * Title: 60. 第k个排列 
 * Desc: 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。 给定 n 和 k，返回第 k 个排列。
 * 
 * 说明：
 * 
 * 给定 n 的范围是 [1, 9]。 给定 k 的范围是[1, n!]。 从1开始的，所以要减去1 
 * Created by Myth-MBP on06/08/2020 in VSCode
 */
public class P60PermutationSequence {
    public String getPermutation(int n, int k) {
        if(n == 1){
            return "1";
        }
        // 保存阶乘
        int[] fac = new int[n];
        fac[0] = 1;
        for (int i = 1; i < n; i++) {
            fac[i] = i * fac[i-1];
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i+1);
        }
        k--;
        while (k != 0) {
            int i = k / fac[list.size()-1];
            k = k % fac[list.size()-1];
            sb.append(list.get(i));
            list.remove(i);
        }
        for (Integer integer : list) {
            sb.append(integer);
        }
        return sb.toString();

    }
}