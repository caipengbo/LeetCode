package math.other;

/**
* Title: 365. 水壶盛水问题
* Desc: 给定容量为 X 和 Y 的两个壶， 盛出来 Z 的水，无限多的水，如何盛？？？最终要用一个水壶或两个水壶把水盛出来
* Created by Myth-MBP on 21/03/2020 in VSCode
*/

public class P365WaterJugProblem {
    // GCD， 贝祖定理
    /*
        存在以下六种状态（注意不能半满） 当前（a,b）盛满 (x,y)：
            把 X 壶灌满；(a,b) -> (x, b)
            把 Y 壶灌满；(a,b) -> (a,y) 
            把 X 壶倒空；(a,b) -> (0,b)
            把 Y 壶倒空; (a,b) -> (a,0)
            把 X 壶的水灌进 Y 壶，直至灌满或倒空；(a,b) -> (a-(y-b),y) 或者 (0, a+b)
            把 Y 壶的水灌进 X 壶，直至灌满或倒空  (a,b) -> (x,b-(x-a))  或者 (a+b, 0)
    */
    // z 是 x 和 y 的 gcd的倍数就行()
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == 0 || y == 0) return (z == 0 || x+y == z);
        return z % gcd(x, y) == 0; 
    }
    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, b%a) : a; 
    }
}