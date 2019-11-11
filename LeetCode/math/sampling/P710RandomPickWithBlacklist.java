package math.sampling;

import java.util.HashMap;
import java.util.Random;

/**
 * Title: 710. 黑名单中的随机数
 * Desc: 给定一个包含 [0，n ) 中独特的整数的黑名单 B，写一个函数从 [ 0，n ) 中返回一个不在 B 中的随机整数。
 *
 * 对它进行优化使其尽量少调用系统方法 Math.random() 。
 *
 * Created by Myth-Lab on 11/11/2019
 */
public class P710RandomPickWithBlacklist {
    private Random random;
    private HashMap<Integer, Integer> map;
    int line;
    public P710RandomPickWithBlacklist(int N, int[] blacklist) {
        random = new Random();
        line = N - blacklist.length;
        map = new HashMap<>();
        for (int b : blacklist) {
            map.put(b, b);
        }
        N = N - 1;
        for (int b : blacklist) {
            if (b < line) {
                while (map.containsKey(N)) N--;  // 找到白名单的末尾
                map.put(b, N--);
            }
        }
    }

    public int pick() {
        int rand = random.nextInt(line);
        return map.getOrDefault(rand, rand);
    }
}
