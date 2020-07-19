package datastructure.string;

/**
* Title: 6.Z字形变换
* Desc: 将一个字符串按 Z 字形排列, 然后按行打印出来
* Created by Myth-MBP on 13/03/2020 in VSCode
*/

public class P6ZigZagConversion {
    // 精简版
    public String convert2(String s, int numRows) {
        if (numRows <= 2) return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        // Key: 巧用Flag控制方向
        int len = s.length(), i = 0, j = 0, flag = -1;
        while (i < len) {
            rows[j].append(s.charAt(i++));
            if (j == 0 || j == numRows-1) flag = -flag;
            j += flag;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
    }

    public String convert(String s, int numRows) {
        
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int len = s.length(), i = 0, j;
        while (i < len) {
            for (j = 0; j < numRows-1; j++) {
                if (i >= len) break;
                rows[j].append(s.charAt(i++));
            }
            for (j = numRows-1; j > 0; j--) {
                if (i >= len) break;
                rows[j].append(s.charAt(i++));
            }
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
    }
}