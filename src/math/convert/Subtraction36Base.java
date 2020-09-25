package math.convert;

import java.util.*;

/**
* Title: 三十六进制减法
* Desc: 10A  11B    35Z
* Created by Myth-MBP on 06/09/2020 in VSCode
*/

public class Subtraction36Base {
    
    // a > b
    private static String sub(String a, String b) {
        Map<Integer, Character> num2Ch = new HashMap<>(36);
        Map<Character, Integer> ch2Num = new HashMap<>(36);
        for (int i = 0; i < 36; i++) {
            char ch;
            if (i < 10) {
                ch = (char) ('0' + i);
            } else {
                ch = (char) ('A' + i - 10);
            }
            num2Ch.put(i, ch);
        }
        for (Integer num : num2Ch.keySet()) {
            ch2Num.put(num2Ch.get(num), num);
        }
        // 转化成数字，逆序存储
        int n = a.length(), m = b.length();
        int[] aArr = new int[n];
        int[] bArr = new int[m];
        for (int j = 0; j < n; j++) {
            aArr[j] = ch2Num.get(a.charAt(n-1-j));
        }
        for (int j = 0; j < m; j++) {
            bArr[j] = ch2Num.get(b.charAt(m-1-j));
        }

        int i = 0, borrow = 0;
        StringBuilder ret = new StringBuilder();
        // 相减
        while (i < n && i < m) {
            if (aArr[i] - borrow < bArr[i]) {
                ret.append(num2Ch.get(aArr[i] - borrow + 36 - bArr[i]));
                borrow = 1;
            } else {
                
                ret.append(num2Ch.get(aArr[i] - borrow - bArr[i]));
                borrow = 0;
            }
            i++;
        }
        // 因为n >= m，所以检查a是否还有多余的
        while (i < n) {
            if (aArr[i] - borrow < 0) {
                ret.append(num2Ch.get(aArr[i] - borrow + 36));
                borrow = 1;
            } else {
                ret.append(num2Ch.get(aArr[i] - borrow));
                borrow = 0;
            }
            i++;
        }
        return ret.reverse().toString();
    }
    /*
        DD-67 = 76
        82-3= 7Z
    */
    public static void main(String[] args) {
        System.out.println(sub("DD", "67"));
        System.out.println(sub("82", "3"));
    }
}
