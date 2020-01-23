package greedy;

/**
 * Title: 55. 跳跃游戏（贪心入门题目）
 * Desc: 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。（剑指offer 青蛙跳台阶问题）
 * Created by Myth-Lab on 10/15/2019
 */
public class P55JumpGame {
    // 记下来可以跳的范围，不断去更新这个范围，如果范围比 当前位置还要大，说明跳不过去
    public boolean canJump(int[] nums) {
        int d = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > d) return false;
            d = Math.max(d, nums[i]+i);
        }
        return true;
    }
    // 错误的写法
    // [0,2,3]
    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length, maxDistance = nums[0];
        for (int i = 0; i < n; i++) {
            if (maxDistance >= n-1) return true;
            maxDistance = Math.max(maxDistance, i+nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        P55JumpGame p55 = new P55JumpGame();

    }
    // TODO 如何跳最快，方式最优，DP
}
