package contest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Title:
 * Desc:
 * Created by Myth-Lab on 10/20/2019
 */
public class WeeklyContest159 {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2) return true;
        if (coordinates[1][0] - coordinates[0][0] == 0) {
            for (int i = 2; i < coordinates.length; i++) {
                if (coordinates[i][0] - coordinates[0][0] != 0) return false;
            }
        }
        double f = (coordinates[1][1] - coordinates[0][1]) / (double)(coordinates[1][0] - coordinates[0][0]);
        for (int i = 2; i < coordinates.length; i++) {
            if (coordinates[i][0] - coordinates[0][0] == 0) return false;
            if (((coordinates[i][1] - coordinates[0][1]) / (double)(coordinates[i][0] - coordinates[0][0])) != f) return false;
        }
        return true;
    }
    // 删除重复前缀
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
        List<String> ret = new LinkedList<>();
        for (int i = 0; i < folder.length; i++) {
            if (folder[i].equals("")) continue;
            for (int j = i+1; j < folder.length; j++) {
                if (folder[j].equals("") || folder[j].length() <= folder[i].length()) continue;
                if (folder[j].startsWith(folder[i]) && folder[j].charAt(folder[i].length()) == '/') folder[j] = "";
            }
        }
        for (int i = 0; i < folder.length; i++) {
            if (!folder[i].equals("")) ret.add(folder[i]);
        }
        return ret;
    }
    // 滑动窗口如何滑？？
    public int balancedString(String s) {
        // 统计每个单词的数目
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            count[s.charAt(i)-'A']++;
        }
        int left = 0, right = 0;
        int ret = n;
        for (right = 0; right < n; right++) {
            count[s.charAt(right)-'A']--;
            // 注意此处 left是小于 n（而不是小于right）;因为窗口最小可以为0，这个时候left是大于right的
            while (left < n && count['Q'-'A'] <= n/4 && count['W'-'A'] <= n/4 && count['E'-'A'] <= n/4 && count['R'-'A'] <= n/4) {
                ret = Math.min(ret, right-left+1);
                count[s.charAt(left)-'A']++;
                left++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        WeeklyContest159 weeklyContest159 = new WeeklyContest159();
        System.out.println(weeklyContest159.balancedString("QQQW"));
    }

}
