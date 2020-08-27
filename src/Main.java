
import java.util.*;


public class Main
{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            max = Math.max(max, arr[i]);
        }
        System.out.println(level(arr, n, max));
    }
    
    private static int level(int[] arr, int n, int max) {
        if (n == 1) {
            return 1;
        }
        int sum = 0;
        for (int i = 1; i <= max; i++) {
            int l = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] < i) {
                    l++;
                }
            }
            if (arr[n-1] >= i) {
                l++;
            }
            // System.out.println("==" + l);
            sum += l;
        }
        return sum;
    }   
}