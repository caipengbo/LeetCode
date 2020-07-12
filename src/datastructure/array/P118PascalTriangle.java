package datastructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P118PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<Integer>(Arrays.asList(1)));
        for (int i = 1; i < numRows; i++) {
            List<Integer> last = ret.get(i-1);
            List<Integer> cur = new ArrayList<>(i+1);
            for (int j = 0; j < last.size(); j++) {
                int a = j == 0 ? 0 : last.get(j-1);
                cur.set(j, a + last.get(j));
            }
            ret.add(cur);
        }
        return ret;
    }
}