package math.sampling;

import java.util.Random;

/**
 * Title: 478. 在圆内随机生成点
 * Desc: 蒙特卡洛拒绝采样
 * Created by Myth-Lab on 11/9/2019
 */
public class P478GenerateRandomPointInCircle {
    private double r;
    private double x;
    private double y;
    private Random random;
    public P478GenerateRandomPointInCircle(double radius, double x_center, double y_center) {
        r = radius;
        x = x_center;
        y = y_center;
        random = new Random();
    }
    public double[] randPoint() {
        // [x-r, x+r]   [y-r, y+r]
        double simpleX = random.nextDouble() * (2.0 * r) + (x - r);
        double simpleY = random.nextDouble() * (2.0 * r) + (y - r);
        if ((simpleX-x)*(simpleX-x)+(simpleY-y)*(simpleY-y) <= r * r) return new double[]{simpleX, simpleY};
        else return randPoint();
    }
}
