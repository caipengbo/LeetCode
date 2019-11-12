package math;

/**
 * Title: 8. 字符串转换整数 (atoi)
 * Desc: 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
 * 如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * Created by Myth-Lab on 11/12/2019
 */
public class P8StringToInteger {
    // 难点：1. 判断是否有效； 2. 判断溢出
    public int myAtoi(String str) {
        int i = 0;
        // 判断开头是否为有效
        while (i < str.length() && str.charAt(i) == ' ') i++;
        if (i >= str.length()) return 0;
        // 开头直接为数字
        if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            int j;
            for (j = i+1; j < str.length(); j++) {
                if (str.charAt(j) < '0' || str.charAt(j) > '9') break;
            }
            return convert('+', str.substring(i, j));
        }
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            int j;
            for (j = i+1; j < str.length(); j++) {
                if (str.charAt(j) < '0' || str.charAt(j) > '9') break;
            }
            return convert(str.charAt(i), str.substring(i+1, j));
        }
        return 0;
    }
    // 将符号和数字转换成 32 位 int，上溢返回 INT_MAX 下溢 返回 INT_MIN
    private int convert(char sign, String numbers) {
        if ("".equals(numbers)) return 0;
        int ret = 0, newRet;
        int flowNumber = -(Integer.MIN_VALUE/10);
        int flowTailNumber = -(Integer.MIN_VALUE%10);
        for (int i = 0; i < numbers.length(); i++) {
            // 溢出判断
           if (ret > flowNumber) return (sign == '-') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
           if (ret == flowNumber &&  (numbers.charAt(i)-'0') >= flowTailNumber) return (sign == '-') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
           ret = ret * 10 + (numbers.charAt(i)-'0');
        }
        return (sign == '-') ? -ret : ret;
    }

    public static void main(String[] args) {
        P8StringToInteger p8 = new P8StringToInteger();
         System.out.println(p8.myAtoi("-6147483648"));
        System.out.println(p8.myAtoi("-42"));
        System.out.println(p8.myAtoi("  42"));
        System.out.println(p8.myAtoi("  -42"));
        System.out.println(p8.myAtoi("-91283472332"));
        System.out.println(p8.myAtoi("91283472332"));
        System.out.println(p8.myAtoi("2147483648"));
        System.out.println(p8.myAtoi("-2147483647"));
        System.out.println(p8.myAtoi("-2147483648"));
    }
}
