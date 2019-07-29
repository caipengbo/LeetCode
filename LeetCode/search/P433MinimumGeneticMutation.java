package search;

import java.util.*;

/**
 * Title: 433. 最小基因变化
 * Desc: 和127题一模一样, BFS 总是优先搜索距离根节点近的节点，因此它搜索到的路径就是最短路径
 * Created by Myth on 7/29/2019
 */
public class P433MinimumGeneticMutation {
    private int bfs(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int level = 0;
        int curSize;
        char[] geneticChars = {'A', 'C', 'G', 'T'};
        while (!queue.isEmpty()) {  // 每一层
            level++;
            // 扩展每一层
            curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                String ele = queue.poll();
                char[] chars = ele.toCharArray();
                // 处理每一个位置（扩展当前层的节点）
                for (int j = 0; j < 8; j++) {
                    char ch = chars[j];
                    for (char c : geneticChars) {
                        if (ch == c) continue;
                        chars[j] = c;
                        String newGenetic = new String(chars);
                        if (newGenetic.equals(end)) return level;
                        if (!bankSet.contains(newGenetic)) continue;
                        queue.add(newGenetic);
                        bankSet.remove(newGenetic);
                    }
                    chars[j] = ch;
                }
            }
        }
        return -1;

    }
    public int minMutation(String start, String end, String[] bank) {
       return bfs(start, end, bank);
    }
    // TODO: 双向队列写法
    private int bibfs(String start, String end, String[] bank) {
        return -1;
    }

    public static void main(String[] args) {
        P433MinimumGeneticMutation p433 = new P433MinimumGeneticMutation();
        String[] bank1 = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        String[] bank2 = {"AACCGGTA"};
        System.out.println(p433.minMutation("AACCGGTT", "AAACGGTA", bank1));
        System.out.println(p433.minMutation("AACCGGTT", "AACCGGTA", bank2));
    }
}
