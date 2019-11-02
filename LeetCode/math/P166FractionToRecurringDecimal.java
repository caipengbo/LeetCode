package math;

import java.util.HashMap;

/**
 * Title: 166. 分数到小数
 * Desc: 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * Created by Myth-Lab on 11/2/2019
 */
public class P166FractionToRecurringDecimal {
    // 思路：模拟除法，如果出现和以前出现过的数字一致的，就出现循环了
    // 难点：流程、顺序
    public String fractionToDecimal(int numerator, int denominator) {
        // quo 存放出现过的商， map存放每一个除数对应的商在quo中的下标
        StringBuilder quos  = new StringBuilder();
        HashMap<Integer, Integer> map = new HashMap<>();
        // p 整数部分， m 中间部分   q: 循环部分
        String p, m = "", q = "";
        int index = -1;  // 循环部分在quos中的下标
        // 判断符号
        String symbol = "";
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            symbol = "-";
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        }
        int quo = numerator / denominator;
        p = String.valueOf(quo);  // 整数部分
        map.put(numerator, quos.length());
        quos.append(quo);
        numerator = numerator % denominator * 10;
        while (numerator != 0) {
            if (map.containsKey(numerator)) {
                index = map.get(numerator);
                break;
            }
            quo = numerator / denominator;
            map.put(numerator, quos.length());
            quos.append(quo);
            numerator = numerator % denominator * 10;
        }
        if (index != -1) {  // 说明存在循环
            q = quos.substring(index);  // 循环部分是从当前重复的地方开始一直到最后
            if (index >= p.length()) {
                m = quos.substring(p.length(), index);  // 小数点后，循环前的部分
            } else {
                m = quos.substring(p.length());
            }
        } else {  // 不存在循环
            m = quos.substring(p.length());
        }
        p = symbol + p;

        if ("".equals(q)) {  // 没有循环部分
            if (!"0".equals(m) && !"".equals(m)) p = p + "." + m;
        } else p = p + "." +  m + "(" + q + ")";

        return p;
    }

    public static void main(String[] args) {
        P166FractionToRecurringDecimal p166 = new P166FractionToRecurringDecimal();
//        System.out.println(p166.fractionToDecimal(-4, -333));
//        System.out.println(p166.fractionToDecimal(-0, 333));
//        System.out.println(p166.fractionToDecimal(-2, 33));
//        System.out.println(p166.fractionToDecimal(1, -3));
//        System.out.println(p166.fractionToDecimal(-2, 1));
//        System.out.println(p166.fractionToDecimal(7, 12));
        int a = -1;
        int b = -2147483648;
        System.out.println(Math.abs(b));

    }
}
