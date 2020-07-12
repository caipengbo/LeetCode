package math.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 89. 格雷码 
 * Desc: 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * 
 * 格雷编码序列必须以 0 开头。
 * 
 * Created by Myth-MBP on 12/07/2020 in VSCode
 */
public class P89GrayCode {
    // 格雷序列只要求一位不同，所以格雷序列是收尾相应的，也叫反射码
    // 所以给一个格雷序列，下一个格雷序列的构造方法是：
    // 以前的序列前面补0  U  以前的序列倒序，前面补1
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int head = 0;
        for (int i = 0; i < n; i++) {
            head = (1 << i);  // 头部补0
            for (int j = list.size()-1; j >=0; j--) {
                list.add(head + list.get(j));
            }
        }
        return list;
    }
}