package datastructure.string;


/**
* Title: 
* Desc: 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
* 如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
* 如果不存在最后一个单词，请返回 0 。
* Created by Myth-MBP on 13/05/2020 in VSCode
*/

public class P58LastWord {
    // 从前往后，太麻烦了
    public int lengthOfLastWord2(String s) {
        StringBuilder sb = new StringBuilder();
        String last = "";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            } else {
                if (sb.length() != 0) last = sb.toString();
                sb.delete(0, sb.length());
            }
        }
        if (sb.length() != 0) last = sb.toString();
        return last.length();
    }
    // 从后往前
    public int lengthOfLastWord(String s) {
        int len = s.length();
        int end = len-1;
        StringBuilder sb = new StringBuilder();
        // 去除前导空格
        for (int i = len-1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                end = i;
                break;
            } 
        }
        for (int i = end; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            } 
            sb.append(s.charAt(i));
        }
        return sb.length();
    }
}