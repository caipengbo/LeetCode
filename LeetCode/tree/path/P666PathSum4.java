package tree.path;

import java.util.HashMap;

/**
 * Title: 666.路径和（本题为会员题，题目描述见本文件最后）
 * Desc: 还是二叉树的路径之和，但树的存储方式变了，
 * 使用一个三位的数字来存的，百位是该结点的深度，十位是该结点在某一层中的位置（按全二叉树的位置算），个位是该结点的值。
 * Created by Myth on 9/24/2019
 */
public class P666PathSum4 {
    // 如何保存节点，如何快速遍历节点(计算节点位置也就是Key)？
    // 使用 HashMap 保存，Key是前两位，Value是 节点的值
    private int sum = 0;
    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num / 10, num % 10);
        }
        sum = 0;
        helper(nums[0]/10, 0, hashMap);
        return sum;
    }
    private void helper(int root, int curSum, HashMap<Integer, Integer> hashMap) {
        if (!hashMap.containsKey(root)) return;
        int level = root / 10;
        int pos = root % 10;
        // 完全二叉树节点的关系
        int left = (level + 1) * 10 + (2 * pos - 1);
        int right = (level + 1) * 10 + 2 * pos;
        curSum += hashMap.get(root);
        // 叶子节点
        if (!hashMap.containsKey(left) && !hashMap.containsKey(right)) {
            sum += curSum;
            return;
        }
        helper(left, curSum, hashMap);
        helper(right, curSum, hashMap);
    }

    public static void main(String[] args) {
        int[] nums1 = {113, 215, 221};
        int[] nums2 = {113, 221};
        P666PathSum4 p666 = new P666PathSum4();
        System.out.println(p666.pathSum(nums1));  // 12
        System.out.println(p666.pathSum(nums2));  // 4
    }
}
/*
If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:

The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

Example 1:

Input: [113, 215, 221]
Output: 12
Explanation:
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.
Example 2:

Input: [113, 221]
Output: 4
Explanation:
The tree that the list represents is:
    3
     \
      1

The path sum is (3 + 1) = 4.
 */
