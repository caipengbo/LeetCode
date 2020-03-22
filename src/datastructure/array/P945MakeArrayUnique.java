package datastructure.array;

import java.util.Arrays;

/**
 * Title: 945. 使数组唯一的最小增量 
 * Desc: 
 * Created by Myth-MBP on 22/03/2020 in VSCode
 */

public class P945MakeArrayUnique {
    // Hash(计数法)
    // 0 <= A.length <= 40000       0 <= A[i] < 40000
    // 操作的过程中如何改变Hash值
    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length <= 1) return 0;
        int[] map = new int[80000];
        for (int a : A) {
            map[a]++;
        }
        // map[i]找到 >1的位置， 然后 j 往后找 == 0的位置
        // 改变 map[i]--     count+= j-i    map[j]=1   j++
        int i, j, count = 0;
        for (i = 0; i < 80000; i++) {
            j = i+1;
            while (map[i] > 1) {
                while (map[j] != 0) j++;
                map[i]--;
                count += j-i;
                map[j] = 1;
            }
        }
        return count;
    }
    // 上面的做法是定位前面的重复元素，然后往后找位置，线性扫描（最坏情况达到了O(L^2)）
    // 优化（j不用每次都移动到i的后面一个位置，如果比i大的话就可以直接复用了）
    public int minIncrementForUnique2(int[] A) {
        if (A == null || A.length <= 1) return 0;
        int[] map = new int[80000];
        int max = 0;
        for (int a : A) {
            map[a]++;
            max = Math.max(max, a);
        }
    
        int i, j = 0, count = 0;
        for (i = 0; i < 80000; i++) {
            if (i > max) break;
            while (map[i] > 1) {
                if (j < i) j = i+1;
                while (map[j] != 0) j++;
                map[i]--;
                count += j-i;
                map[j] = 1;
            }
        }
        return count;
    }
    // 排序做法
    public int minIncrementForUnique3(int[] A) {
        Arrays.sort(A);
        int n = A.length, count = 0;
        for (int i = 1; i < n; i++) {
            if (A[i] <= A[i-1]) {
                count += A[i-1] + 1 - A[i];
                A[i] = A[i-1] + 1;
            }
        }
        return count;
    }

}