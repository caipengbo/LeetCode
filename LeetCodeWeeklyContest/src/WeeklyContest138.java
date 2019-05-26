import java.util.Arrays;

/**
 * Title:
 * Desc:
 * Created by Myth on 5/26/2019
 */
public class WeeklyContest138 {
    public int heightChecker(int[] heights) {
        int[] origin = heights.clone();
        Arrays.sort(heights);
        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (origin[i] != heights[i]) count++;
        }
        return count;
    }
    // customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
    // 1 <= X <= customers.length == grumpy.length <= 20000
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sum = 0, maxWindow = 0, currentWindow = 0;

        for (int i = 0; i < customers.length; ++i) {
            sum += grumpy[i] == 0 ? customers[i] : 0;
            // 以下两行代码是实现窗口的滑动(窗口内只计算grumpy为1的值)
            currentWindow += grumpy[i] == 1 ? customers[i] : 0;
            if (i >= X) currentWindow -= grumpy[i - X] == 1 ? customers[i - X] : 0;
            maxWindow = Math.max(maxWindow, currentWindow);
        }
        return sum + maxWindow;
    }
    // 交换一个整数数字，让它比以前的数组 按字典顺序小，但是是所有可能性中最大的
    public static int[] prevPermOpt1(int[] A) {
        int len = A.length;
        if (len < 2) return A;
        int left = len-1;
        // 从后往前找到突然变大的数字位置 left
        for (int i = len - 1; i > 0; i--) {
            if (A[i-1] > A[i]) {
                left = i-1;
                break;
            }
        }
        //  在left的右边，找到第一个小于A[left]的（重复的话，选择最left的）m ,看下面的别人的解法很简介！！！
        int right = len-1;
        for (int i = len-1; i > left; i--) {
            if (A[i] < A[left] && (A[i]!=A[i-1])) { // A[i]!=A[i-1]作用就是为了重复时候选择最left的位置
                right = i;
                break;
            }
        }

        // System.out.println("Left: " + left + ", Right: " + right);
        int temp = A[left];
        A[left] = A[right];
        A[right] = temp;
        return A;
    }
    public int[] prevPermOpt2(int[] A) {
        int n = A.length, left = n - 2, right = n - 1, tmp;
        while (left >= 0 && A[left] <= A[left + 1]) left--;
        if (left < 0) return A;
        while (A[left] <= A[right]) right--;
        while (A[right - 1] == A[right]) right--;
        tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
        return A;
    }

    public static void main(String[] args) {
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy = {0,1,0,1,0,1,0,1};
        int X = 3;
        int[] customers1 = {4,10,10};
        int[] grumpy1 = {1,1,0};
        int X1 = 2;
        int[] customers2 = {4,10,10,12,2,3,4,22};
        int[] grumpy2 = {1,1,1,1,1,1,1,1};
        int X2 = 2;
        // System.out.println(WeeklyContest138.maxSatisfied(customers2, grumpy2, X2));
        int[] A = {39,90,11,27,36,57,87};
        A = WeeklyContest138.prevPermOpt1(A);
        System.out.println(Arrays.toString(A));
    }
}
