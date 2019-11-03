package math;

/**
 * Title: 171. Excel表列序号
 * Desc:
 * Created by Myth-Lab on 11/3/2019
 */
public class P171ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int sum = 0;
        for (int i=s.length()-1; i >= 0; i--) {
            sum += Math.pow(26, s.length()-1-i) * (s.charAt(i)-'A'+1);
        }
        return sum;
    }
}
