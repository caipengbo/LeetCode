import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final static String target = "Hello";
    private final static String target2 = "Hello";
    public static String get(){
        return "Hello";
    }
    public static void main(String[] args) {
        
        System.out.println(minimumOperations("yry"));
        System.out.println(minimumOperations("ryr"));
        
    }
    // 红 黄 红
    public static int minimumOperations(String leaves) {
        char[] arr = leaves.toCharArray();
        int n = leaves.length();
        int min = Integer.MAX_VALUE;
        // 每个区间红黄
        int[] leftYellow = new int[n];
        int[] leftRed = new int[n];
        int[] rightYellow = new int[n];
        int y = 0, r = 0;;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'y') {
                y++;
            } else {
                r++;
            }
            leftYellow[i] = y;
            leftRed[i] = r;
        }
        y = 0;
        for (int i = n-1; i >= 0; i--) {
            if (arr[i] == 'y') {
                y++;
            }
            rightYellow[i] = y;
        }
        System.out.println(Arrays.toString(leftYellow));
        System.out.println(Arrays.toString(leftRed));
        System.out.println(Arrays.toString(rightYellow));

        for (int i = 0; i < n; i++) {
            for (int j = i+2; j < n; j++) {
                int temp = leftYellow[i];  // 左边
                temp += rightYellow[j];  // 右边

                temp += (leftRed[j-1] - leftRed[i]);
                
                min = Math.min(min, temp);
            }
           
        }
        return min;
    }
}