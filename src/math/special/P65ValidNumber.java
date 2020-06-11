package math.special;

/**
* Title: 65. 有效数字
* Desc: 数字 0-9    指数 - "e"     正/负号 - "+"/"-"         小数点 - "."
* Created by Myth-PC on 02/02/2020 in VSCode
*/
public class P65ValidNumber {
    // 字符串遵循模式 A[.B][e|EC], A 和 C 是有符号整数（可以有+ - 号）， B是无符号整数
    // 画出状态机（图）
    // 实现状态机
    // 映射（边）
    private int op(char ch) {
        if (ch == '+' || ch == '-') return 0;
        if (ch >= '0' && ch <= '9') return 1;
        if (ch == 'e' || ch == 'E') return 2;
        if (ch == '.') return 3;
        return -1;
    }
    // 结点是状态
    public boolean isNumber(String s) { 
        // 8个状态, 5种操作  => 为了 +.8   .8e1 成立增加了 最后两个状态（自动机不是最简）
        int[][] automaton = {{1, 2, -1, 8},
                            {-1, 2, -1, 8}, 
                            {-1, 2, 5, 3},
                            {-1, 4, 5, -1},
                            {-1, 4, 5 , -1},
                            {6, 7, -1, -1},
                            {-1, 7, -1, -1},
                            {-1, 7, -1, -1},
                            {-1, 9, -1, -1},
                            {-1, 9, 5, -1}};
        char[] chars = s.trim().toCharArray();
        int states = 0;
        for (int i = 0; i < chars.length; i++) {
            int trans = op(chars[i]);
            if (trans == -1) return false;
            states = automaton[states][trans];
            if (states == -1) return false;
        }
        return (states == 2 || states == 3 || states == 4 || states == 7 || states == 9);
    }
    public static void main(String[] args) {
        P65ValidNumber p65 = new P65ValidNumber();
        // .1、 3.  3.e    
        System.out.println(p65.isNumber(".1 "));
        System.out.println(p65.isNumber("1."));
        System.out.println(p65.isNumber("3.e-10"));
        System.out.println(p65.isNumber(".e1"));  // false
        System.out.println(p65.isNumber("."));  // false
        System.out.println(p65.isNumber("1.e"));  // false
        System.out.println(p65.isNumber("+.e1"));  // false
        System.out.println(p65.isNumber("+.1"));  // false
    }
}