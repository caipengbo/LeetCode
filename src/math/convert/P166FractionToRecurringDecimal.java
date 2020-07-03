package math.convert;

import java.util.HashMap;

/**
 * Title: 166. 分数到小数
 * Desc: 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * Created by Myth-Lab on 11/2/2019
 */
public class P166FractionToRecurringDecimal {
    // 思路：模拟除法，如果出现和以前出现过的数字一致的，就出现循环了
    // 难点：流程、顺序, 溢出
    public String fractionToDecimal(int numerator, int denominator) {
        long divided = (long) numerator;
        long divisor = (long) denominator;
        // quo 存放出现过的商， map存放每一个除数对应的商在quo中的下标
        StringBuilder quos  = new StringBuilder();
        HashMap<Long, Integer> map = new HashMap<>();
        // p 整数部分， m 中间部分   q: 循环部分
        String p, m = "", q = "";
        int index = -1;  // 循环部分在quos中的下标
        // 判断符号
        String symbol = "";
        if ((divided < 0 && divisor > 0) || (divided > 0 && divisor < 0)) {
            symbol = "-";
            divided = Math.abs(divided);
            divisor = Math.abs(divisor);
        }
        long quo = divided / divisor;
        p = String.valueOf(quo);  // 整数部分
        map.put(divided, quos.length());
        quos.append(quo);
        divided = divided % divisor * 10;
        while (divided != 0) {
            if (map.containsKey(divided)) {
                index = map.get(divided);
                break;
            }
            quo = divided / divisor;
            map.put(divided, quos.length());
            quos.append(quo);
            divided = divided % divisor * 10;
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
    // 精简代码
    public String fractionToDecimal2(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder ret  = new StringBuilder();
        HashMap<Long, Integer> map = new HashMap<>();
        if ((numerator < 0 ) ^ (denominator < 0)) ret.append("-");  // 使用异或判断异号
        long n = Math.abs((long) numerator);
        long d = Math.abs((long) denominator);
        ret.append(n/d);
        n = n % d;
        if (n == 0) return ret.toString();  // 可以整除
        ret.append(".");
        while (!map.containsKey(n)) {
            map.put(n, ret.length());  // 注意此处
            n = n * 10;
            ret.append(n/d);
            n = n % d;
            if (n == 0) return ret.toString();
        }
        // 存在循环部分(找到出现循环的位置，添加括号)
        ret = ret.insert(map.get(n), "(").append(")");
        return ret.toString();
    }

    public static void main(String[] args) {
        P166FractionToRecurringDecimal p166 = new P166FractionToRecurringDecimal();
        System.out.println(p166.fractionToDecimal(-4, -333));
        System.out.println(p166.fractionToDecimal(-0, 333));
        System.out.println(p166.fractionToDecimal(-2, 33));
        System.out.println(p166.fractionToDecimal(1, -3));
        System.out.println(p166.fractionToDecimal(-2, 1));
        System.out.println(p166.fractionToDecimal(7, 12));
        System.out.println(p166.fractionToDecimal(-1, -2147483648));
    }
}
