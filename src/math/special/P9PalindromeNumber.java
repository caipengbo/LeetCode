package math.special;

/**
* Title: 9. 判断回文数
* Desc: 判断一个数是不是回文数，不将数字转换成字符串！
* Created by Myth-MBP on 13/03/2020 in VSCode
*/

public class P9PalindromeNumber {
    // 如何把数字翻过来？？？
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int reversed = 0, origin = x;
        while (x != 0) {
            reversed = reversed * 10 + (x % 10);  // 把数字翻过来
            x /= 10;
        }
        return reversed == origin;
    }
}