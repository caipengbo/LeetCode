package math;

/**
 * Title: 223. 矩形面积
 * Desc: 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
 * Created by Myth-Lab on 11/1/2019
 */
public class P223RectangleArea {
    // 求 s1-e1  和 s2-e2的重叠长度
    private int getOverlap(int s1, int e1, int s2, int e2) {
        if (s1 >= e2 || s2 >= e1) return 0;  // 不交叉
        return Math.max(e1, e2) - Math.min(s1, s2);
    }
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // 重叠面积 = AC和EG的重叠 * BD和FH重叠部分
        // 容斥原理 : 面积和 - 重叠面积
        System.out.println(getOverlap(A, C, E, G));
        System.out.println(getOverlap(B, D, F, H));
        return (C-A)*(D-B) + (G-E)*(H-F) - getOverlap(A, C, E, G) * getOverlap(B, D, F, H);
    }
}
