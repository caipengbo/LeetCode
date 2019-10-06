package contest;

import util.TreeNode;

import java.util.*;

/**
 * Title:
 * Desc:
 * Created by Myth-PC on 2019-10-05
 */
public class BiWeeklyContest10 {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        Map<Integer, Integer> map = new TreeMap<>();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr1.length; i++) {
            int num = map.getOrDefault(arr1[i], 0) + 1;
            map.put(arr1[i], num);
        }
        for (int i = 0; i < arr2.length; i++) {
            int num = map.getOrDefault(arr2[i], 0) + 1;
            map.put(arr2[i], num);
        }
        for (int i = 0; i < arr3.length; i++) {
            int num = map.getOrDefault(arr3[i], 0) + 1;
            map.put(arr3[i], num);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 3) list.add(key);
        }
        return list;
    }
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null) return false;
        if (find(root2, target-root1.val)) return true;
        return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1.right, root2, target);
    }
    private boolean find(TreeNode root, int target) {
        if (root == null) return false;
        if (root.val == target) return true;
        else if (root.val > target) return find(root.left, target);
        else return find(root.right, target);
    }
    public List<Integer> countSteppingNumbers2(int low, int high) {
        Set<Integer> set = new TreeSet<>();
        for (int i = low; i <= high; i++) {
            if (isStepNumbers(i)) set.add(i);
        }
        return new ArrayList<>(set);
    }
    private boolean isStepNumbers(Integer number) {
        int last = number % 10;
        number = number / 10;
        while (number != 0) {
            int curLast = number % 10;
            if (Math.abs(curLast-last) == 1) last = curLast;
            else return false;
            number = number / 10;
        }
        return true;
    }
    // 步进数
    private Set<Integer> set = new TreeSet<>();
    public List<Integer> countSteppingNumbers(int low, int high) {
        for (int i = 0; i <= 9; i++) {
            bfs(low, high, i);
        }
        return new ArrayList<>(set);
    }

    private void bfs(int n,int m, long num) {
        // 注意此处使用Long，使用Integer会出现溢出
        Queue<Long> q = new LinkedList<> ();
        q.add(num);
        while (!q.isEmpty())
        {
            Long stepNum = q.poll();
            // 满足要求的步进数
            if (stepNum <= m && stepNum >= n)
            {
                set.add(Math.toIntExact(stepNum));
            }
            // 不满足要求的步进数
            if (stepNum == 0 || stepNum > m) continue;
            // 获取最后一位
            int lastDigit = (int) (stepNum % 10);
            // 最后一位+1或者-1
            Long stepNumA = stepNum * 10 + (lastDigit- 1);
            Long stepNumB = stepNum * 10 + (lastDigit + 1);
            // 最后一位等于0的时候，新的步进数最后一位只能是1
            if (lastDigit == 0) q.add(stepNumB);
            else if (lastDigit == 9) q.add(stepNumA);
            else {
                q.add(stepNumA);
                q.add(stepNumB);
            }
        }
    }
}
