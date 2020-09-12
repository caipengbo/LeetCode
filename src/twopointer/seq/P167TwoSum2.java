package twopointer.seq;

import java.util.*;

/**
 * Title: 167. 两数之和 II - 输入有序数组（1. 两数之和） 
 * Desc: 有序数组，求两数之和等于target的下标 
 * Created by Myth-MBP on 31/08/2020 in VSCode
 */

public class P167TwoSum2 {


    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length-1;
        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                break;
            }
        }
        int[] ret = new int[2];
        ret[0] = i+1;   // 本题要求下标从1开始
        ret[1] = j+1; 
        return ret;
    }
    // 进阶：求对数，重复的不算
    public int twoSum2(int[] numbers, int target) {
        int i = 0, j = numbers.length-1;
        // int count = 0;
        Set<String> set = new HashSet<>();
        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                String key = Integer.toString(numbers[i]) + "-" + Integer.toString(numbers[j]);
                if (!set.contains(key)) {
                    set.add(key);
                    // count++;
                }
                i++;
                j--;
            }
        }
        return set.size();
    }

}