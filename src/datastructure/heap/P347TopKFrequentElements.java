package datastructure.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Title: 347. 前 K 个高频元素 
 * Desc: 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
 * Created by Myth on
 * 01/16/2020 in VSCode
 */

public class P347TopKFrequentElements {
    // 难点：heap里面存的是什么
    // 第一次实现的时候，在heap中储存key,导致错误！！
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 注意
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, (o1, o2)->o1[1]-o2[1]);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        // System.out.println(map);
        int i = 0;
        for (Integer key : map.keySet()) {
            if (i < k) heap.add(new int[]{key, map.get(key)});
            else if (map.get(key) >= heap.peek()[1]) {
                heap.poll();
                heap.add(new int[]{key, map.get(key)});
            }
            i++;
        }
        LinkedList<Integer> ret = new LinkedList<>();
        while (!heap.isEmpty()) {
            ret.add(heap.poll()[0]);
        }
        return ret;
    }
    
    public static void main(String[] args) {
        P347TopKFrequentElements p347 = new P347TopKFrequentElements();
        int[] nums = {1,1,1,2,2,3};
        int[] nums2 = {4,1,-1,2,-1,2,3};
        System.out.println(p347.topKFrequent(nums2, 2));
    }
}