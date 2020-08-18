package contest;

import java.util.Scanner;

public class Ali731 {    
    public static void main(String[] args) {        
        Scanner in = new Scanner(System.in);        
        while (in.hasNextInt()) {            
            int n = in.nextInt();            
            int m = in.nextInt();            
            System.out.printf("%.0f\n", slice(m + 1, n));        
        }    
    }    
    public static double slice(int m, int n) {        
        if (n == 1) {            
            return m;        
        }        
        double temp = slice(m, n / 2);        
        return ((n % 2 == 0 ? 1 : m) * temp * temp) % 1000000007;    
    }
    
}

/*
4 4 2
CCCS
SSSS
CSCS
SSCC
1 1 3 4
3 1 1 3

输出
13
14
*/
class Main2 {
    private static boolean[][] isVisit;    
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};    
    private static int n, m, q, bx, by, ex, ey, nextX, nextY, currVal, result;    
    private static String[] input;    
    public static void main(String[] args) {        
        Scanner sc = new Scanner(System.in);        
        n = sc.nextInt();        
        m = sc.nextInt();        
        q = sc.nextInt();        
        input = new String[n];        
        for (int i = 0; i < n; i++) {            
            input[i] = sc.next();        
        }       
        while (q-- > 0) {            
            isVisit = new boolean[n][m];            
            result = Integer.MAX_VALUE;            
            bx = sc.nextInt();            
            by = sc.nextInt();            
            ex = sc.nextInt();            
            ey = sc.nextInt();            
            currVal = 0;            
            BackTrace(bx, by);           
            System.out.println(result);       
        }    
    }    
    public static boolean isOk(int x, int y) {        
            return x >= 0 && x < n && y >= 0 && y < m && !isVisit[x][y];   
    }    
    public static void BackTrace(int x, int y) {       
        if (currVal >= result) {            
            return;        
        }        
        if (x == ex && y == ey) {           
            if (currVal < result) {                
                result = currVal;            
            }            
            return;        
        }
                
        for (int i = 0; i < 4; i++) {            
            nextX = x + direction[i][0];            
            nextY = y + direction[i][1];            
            if (isOk(nextX, nextY)) {                
                int add = 2;                
                if (input[x].charAt(y) != input[nextX].charAt(nextY)) {                   
                    add = 5;                
                } else if (input[nextX].charAt(nextY) == 'C') {                    
                    add = 3;                
                }                
                isVisit[x][y] = true;                
                currVal += add;                
                BackTrace(nextX, nextY);                
                currVal -= add;                
                isVisit[x][y] = false;            
            }        
        }    
    }
}
