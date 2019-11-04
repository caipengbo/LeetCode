package math;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Title:  202. 快乐数
 * Desc:
 * Created by Myth-Lab on 11/4/2019
 */
public class P202HappyNumber {
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        // 肯定存在循环
        while (!set.contains(n)) {
            if (n == 1) return true;
            set.add(n);
            n = repalce(n);
        }
        return false;
    }
    private int repalce(int n) {
        int sum = 0, last;
        while (n != 0) {
            last = n % 10;
            sum += last * last;
            n = n / 10;
        }
        return sum;
    }
    // 使用快慢指针的思想，循环意味着出现了环！！！，那么只需要判断，这个环是不是由1组成的就可以啦！
    public boolean isHappy2(int n) {
        int slow = n, fast= n;
        // 肯定存在循环
        do {
            slow = repalce(slow);
            fast = repalce(fast);
            fast = repalce(fast);
        } while (slow != fast);
        return fast == 1;
    }

    public static void main(String[] args) {
        P202HappyNumber p202 = new P202HappyNumber();
        System.out.println(p202.isHappy2(2));
    }
}
