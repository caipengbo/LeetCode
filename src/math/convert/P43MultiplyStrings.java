package math.convert;

/**
 * Title: 43. 字符串相乘
 * Desc: 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * Created by Myth-Lab on 11/7/2019
 */
public class P43MultiplyStrings {

    public String multiply(String num1, String num2) {
        // 注意此处
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int len1 = num1.length(), len2 = num2.length();
        int[] res = new int[len1+len2];
        for (int i = len1-1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = len2-1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = res[i+j+1] + (n1 * n2);
                res[i+j] += sum / 10;
                res[i+j+1] = sum % 10;
            }
        }
        boolean start = true;
        StringBuilder sb = new StringBuilder();
        // 注意此处求前导0的写法（注意不要写成了去除第一个0）
        for (int num : res) {
            if (start && num == 0) {
                continue;
            }
            start = false;
            sb.append(num);
        }
        return sb.toString();
    }


    private String add(String num1, String num2) {
        StringBuilder ret = new StringBuilder();
        int extra = 0;
        int i = num1.length()-1, j = num2.length()-1, sum;
        while (i >= 0 && j >= 0) {
            sum = num1.charAt(i) - '0' + num2.charAt(j) - '0' + extra;
            ret.insert(0, sum%10);
            extra = sum/10;
            i--;
            j--;
        }
        while (i >= 0) {
            sum = num1.charAt(i) - '0' + extra;
            ret.insert(0, sum%10);
            extra = sum/10;
            i--;
        }
        while (j >= 0) {
            sum = num2.charAt(j) - '0' + extra;
            ret.insert(0, sum%10);
            extra = sum/10;
            j--;
        }
        if (extra != 0) ret.insert(0, extra);
        return ret.toString();
    }
    //  精简后的代码：(循环使用或,使用append)
    private String add2(String num1, String num2) {
        StringBuilder ret = new StringBuilder();
        int extra = 0, a, b;
        int i = num1.length()-1, j = num2.length()-1, sum;
        while (i >= 0 || j >= 0) {
            a = (i >= 0) ? num1.charAt(i) - '0' : 0;
            b = (j >= 0) ? num2.charAt(j) - '0' : 0;
            sum = a + b + extra;
            ret.append(sum%10);
            extra = sum/10;
            i--;
            j--;
        }
        if (extra != 0) ret.append(extra);
        return ret.reverse().toString();
    }

    public String multiply2(String num1, String num2) {
        String ret = "0";
        int mul, extra;
        StringBuilder zero = new StringBuilder();
        for (int i = num1.length()-1; i >= 0; i--) {
            extra = 0;
            StringBuilder subMul = new StringBuilder();
            for (int j = num2.length()-1; j >= 0; j--) {
                mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0') + extra;
                subMul.insert(0, mul%10);
                extra = mul/10;
            }
            if (extra != 0) subMul.insert(0, extra);
            subMul.append(zero);
            // System.out.println("-----" + subMul);
            ret = add(ret, subMul.toString());
            zero.append("0");
        }
        // 去除前导0
        int z;
        for (z = 0; z < ret.length()-1; z++) {
            if (ret.charAt(z) != '0') break;
        }
        return ret.substring(z);
    }
    // 根据进位规律
    // 乘数 num1 位数为 MM，被乘数 num2 位数为 NN， num1 x num2 结果 res 最大总位数为 M+N
    //num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)，其第一位位于 res[i+j]，第二位位于 res[i+j+1]。
    public String multiply3(String num1, String num2) {
        // TODO Again
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);  // 只加res[i+j+1]
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }
    public static void main(String[] args) {
        P43MultiplyStrings p43 = new P43MultiplyStrings();
        System.out.println(p43.multiply("123", "0"));
        System.out.println(p43.multiply("0", "666"));
        System.out.println(p43.multiply("30", "0"));
        System.out.println(p43.multiply("0", "30"));
        System.out.println(p43.multiply("0", "0"));
        System.out.println(p43.multiply("999", "1"));
        System.out.println(p43.multiply("999", "999"));
    }
}
