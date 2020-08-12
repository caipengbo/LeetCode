package datastructure.advanced;

import java.util.BitSet;
 
/**
* Title: 简单的Bloom过滤器实现
* Desc: 1. 一定没出现过  2. 很大概率出现
* Created by Myth-MBP on 12/08/2020 in VSCode
*/

public class BloomFilter {
    private static final int SIZE = 1 << 24;
    BitSet bitSet = new BitSet(SIZE);
    Hash[] hashFunction = new Hash[8];
    private static final int seeds[] = new int[] { 3, 5, 7, 9, 11, 13, 17, 19 };
 
    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            hashFunction[i] = new Hash(seeds[i]);
        }
    }
 
    public void add(String string) {
        for (Hash hash : hashFunction) {
            System.out.println(hash.getHash(string));
            bitSet.set(hash.getHash(string), true);
        }
    }
 
    public boolean contains(String string) {
        boolean have = true;
        for (Hash hash : hashFunction) {
            have &= bitSet.get(hash.getHash(string));
        }
        return have;
    }
 
    class Hash {
        private int seed = 0;
 
        public Hash(int seed) {
            this.seed = seed;
        }
 
        public int getHash(String string) {
            int val = 0;
            int len = string.length();
            for (int i = 0; i < len; i++) {
                val = val * seed + string.charAt(i);
            }
            return val & (SIZE - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(SIZE);
        String email = "https://blog.csdn.net/qq_40374604/article/details/88974732";
        BloomFilter bloomDemo = new BloomFilter();
        System.out.println(email + "是否在列表中： " + bloomDemo.contains(email));
        bloomDemo.add(email);
        System.out.println(email + "是否在列表中： " + bloomDemo.contains(email));
        email = "https://www.imsilkroad.com/news/category/siluyiliao";
        System.out.println(email + "是否在列表中： " + bloomDemo.contains(email));
    }

}