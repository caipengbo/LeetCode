package search;

import java.util.*;

/**
 * Title: 752. 打开转盘锁
 * Desc: 和127、 433题类似, 转轮可以正向和逆向旋转，目标target不会再deadends里面出现！
 * Created by Myth on 7/29/2019
 */
public class P752OpenTheLock {
    private int bfs(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains(target)) return -1;
        Queue<String> queue = new LinkedList<>();
        int level = -1;
        char temp;
        queue.add("0000");
        while (!queue.isEmpty()) {
            level++;
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                String node = queue.poll();
                if (node.equals(target)) return level;
                if (deadSet.contains(node)) continue;
                deadSet.add(node);
                // 扩展节点
                char[] chars = node.toCharArray();
                for (int j = 0; j < 4; j++) { // 改变每一位
                    // 正向扩展
                    temp = chars[j];
                    chars[j] = (temp == '9' ? '0' : (char) (temp + 1));
                    queue.add(new String(chars));
                    // 逆向扩展
                    chars[j] = temp;
                    chars[j] = (temp == '0' ? '9' : (char) (temp - 1));
                    queue.add(new String(chars));
                    chars[j] = temp;
//                    char c = node.charAt(j);
//                    String s1 = node.substring(0, j) + (c == '9' ? 0 : c - '0' + 1) + node.substring(j + 1);
//                    String s2 = node.substring(0, j) + (c == '0' ? 9 : c - '0' - 1) + node.substring(j + 1);
//                    queue.add(s1);
//                    queue.add(s2);
                }
            }
        }
        return -1;
    }

    public int openLock(String[] deadends, String target) {
        return bfs(deadends, target);
    }

    public static void main(String[] args) {
        P752OpenTheLock p752 = new P752OpenTheLock();
        String[] deadends1 = {"0201","0101","0102","1212","2002"};
        String target1 = "0202";
        String target2 = "0009";
        String target3 = "1100";
        System.out.println(p752.openLock(deadends1, target1));
    }
}
