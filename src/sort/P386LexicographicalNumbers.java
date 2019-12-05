package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Title: 386. 字典序排数
 * Desc: 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 * Created by Myth-Lab on 10/18/2019
 */
public class P386LexicographicalNumbers {
     // 使用自定义 排序规则（太慢了，）
    public List<Integer> lexicalOrder(int n) {
        TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        for (int i = 1; i <= n; i++) {
            set.add(i);
        }
        return new ArrayList<>(set);
    }
}
