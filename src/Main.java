
import java.util.*;

public class Main
{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        // System.out.println(ugly(n));
        String line = sc.nextLine();
        String[] numStrs = line.split(",");
        int[] nums = new int[numStrs.length];
        int i = 0;
        for (String numStr : numStrs) {
            nums[i++] = Integer.parseInt(numStr);
        }
        System.out.println(p3(nums));
    }
    private static int p3(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i-1]);
        }
        return dp[nums.length];
    }

    
}