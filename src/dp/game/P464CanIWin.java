package dp.game;

import java.util.HashMap;

/**
* Title: 464. 我能赢吗
* Desc: 不放回 [1, maxInteger]  第一个>= total
* Created by Myth on 01/01/2020 in VSCode
*/

public class P464CanIWin {
    // 如果不放回，就是Nim游戏：相当初始值total, 每次取1-m, 谁最后取完

    // 不放回，也就是取的次数不一致了(直接想自底向上DP不好想，就自顶向下记忆化搜索)
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger * (1 + maxChoosableInteger) / 2 < desiredTotal) return false;
        if (desiredTotal == 0 || maxChoosableInteger >= desiredTotal) return true;
        boolean[] dp = new boolean[maxChoosableInteger+1];
        int dict = 0;
        HashMap<Integer, Boolean> mem = new HashMap<>();
        return canWin(dict, mem, maxChoosableInteger, desiredTotal);
    }
    // 递归判断先手是否会赢
    public boolean canWin(int dict, HashMap<Integer, Boolean> mem,int maxChoosableInteger, int total) {
        if (mem.containsKey(dict)) return mem.get(dict); 
        // if (total <= 0) return true;
        boolean win = false;
        int mask;
        for (int i = maxChoosableInteger; i >= 1; i--) {
            mask = (1<<i);
            if ((dict&mask) == 0) {
                if (i >= total || !canWin((dict|mask), mem,maxChoosableInteger, total-i)) {
                    win = true;
                    break;
                }
            }
        }
        mem.put(dict, win);
        return win;
    }
    public static void main(String[] args) {
        P464CanIWin p464 = new P464CanIWin();
        System.out.println(p464.canIWin(10, 40)); // false
    }
}