package greedy;

/**
* Title: 45. 跳跃游戏2
* Desc: 给定一个非负整数数组，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
你的目标是使用最少的跳跃次数到达数组的最后一个位置。
* Created by Myth-PC on 22/01/2020 in VSCode
*/
public class P45JumpGame2 {
    //  假设你总是可以到达数组的最后一个位置
    public int jump(int[] nums) {
        int n = nums.length, k = 0, cur = 0, next;
        // 求每个范围的最值
        while (cur < n-1) {  // next >= n-1 说明下一步可以达到数组尾部，注意此处
            next = nums[cur] + cur;
            for (int i = cur; i <= next && i < n; i++) {
                // 可以将当前的范围扩大，就跳到那个扩的最大的位置
                if (i == n-1 || nums[i]+i > nums[cur]+cur) {
                    cur = i;
                }
            }
            // 不确定是否可以到达边界
            // if (cur <= oldCur) return -1;
            k++;
        }
        return k;
    }
    public static void main(String[] args) {
        P45JumpGame2 p45 = new P45JumpGame2();
        int[] nums0 = {2,3,1,1,4};
        int[] nums1 = {2,1};
        int[] nums2 = {1,1,1,1,1};
        System.out.println(p45.jump(nums0));
        System.out.println(p45.jump(nums1));
        System.out.println(p45.jump(nums2));
    }
}