package math.numbertheory;

import java.util.HashMap;

/**
* Title: 914. 卡牌分组
* Desc: N个数的最大公约数
* Created by Myth-MBP on 27/03/2020 in VSCode
*/
// https://www.bbsmax.com/A/Ae5R84NzQ9/
public class P914DeckOfCards {
    // 方法1： 每次用最小的去对每个数取模，一直取到只剩下一个数，这个数就是最大公约数
    public static boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length <=1) return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int d : deck) {
            map.put(d, map.getOrDefault(d, 0)+1);
        }
        int n = map.size(), min = -1;
        if (n == 1) return true;
        while (n > 1) {
            for (Integer key : map.keySet()) {
                if (min == -1) min = map.get(key);
                else {
                    if (map.get(key) > min) map.put(key, map.get(key) % min);
                    if (map.get(key) == 0) n--;
                    else min = map.get(key);
                }
            }    
        }
        return min >= 2;
    }
    // 使用欧几里得算法:a b c 的最大公约数  a b 的最大公约数(a,b) 与 c的最大公约数   ((a,b),c)   n-1次辗转相除
    public  boolean hasGroupsSizeX2(int[] deck) {
        if (deck == null || deck.length <=1) return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int d : deck) {
            map.put(d, map.getOrDefault(d, 0)+1);
        }
        int n = map.size(), d = -1;

        for (Integer key : map.keySet()) {
            if (d == -1) d = map.get(key);
            else {
                d = gcd(map.get(key), d);
            }
        }

        return d >= 2;
    }
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }
    // 多个数的最小公倍数： 和求n个数的最大公约数类似，比如a b c的，先求ab的最小公倍数(a,b)，然后求 ((a,b),c)
    public static void main(String[] args) {
        int[] deck = {1,1,1,2,2,2,3,3};
        System.out.println(P914DeckOfCards.hasGroupsSizeX(deck));
    }
}