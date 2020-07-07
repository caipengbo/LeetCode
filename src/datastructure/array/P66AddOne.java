package datastructure.array;


/**
* Title: 66. 加1
* Desc: 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
你可以假设除了整数 0 之外，这个整数不会以零开头。

输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
* Created by Myth-MBP on 07/07/2020 in VSCode
*/
public class P66AddOne {

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        if (n == 0) {
            return digits;
        }
        int carry = 1;
        for (int i = n-1; i >= 0; i--) {
            int temp = digits[i] + carry;
            digits[i] =  temp % 10;
            carry = temp / 10;
            if (carry == 0) {
                break;
            }
        }
        // 说明位数增加  有进位，后面肯定是0啊
        if (carry != 0) {
            int[] newDigits = new int[n+1];
            System.arraycopy(digits, 0, newDigits, 1, n);
            newDigits[0] = carry;
            digits = newDigits;
        }
        return digits;
    }
    // 修改版
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
        String.
    }

}