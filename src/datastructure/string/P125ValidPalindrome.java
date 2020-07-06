package datastructure.string;

/**
* Title: 125. 验证回文串
* Desc: 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
说明：本题中，我们将空字符串定义为有效的回文串。

输入: "A man, a plan, a canal: Panama"
输出: true
* Created by Myth-MBP on 06/07/2020 in VSCode
*/
public class P125ValidPalindrome {
    // 重点：Character的使用
    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while(i < j) {
            if (!Character.isLetter(s.charAt(i)) && !Character.isDigit(s.charAt(i))) {
                i++;
                continue;
            }
            if (!Character.isLetter(s.charAt(j)) && !Character.isDigit(s.charAt(j))) {
                j--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(P125ValidPalindrome.isPalindrome("0P"));
    }
}