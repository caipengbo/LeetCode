package datastructure;

import java.util.Arrays;

/**
* Title: 820. 单词的压缩编码
* Desc: 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
那么成功对给定单词列表进行编码的最小字符串长度是多少呢？

* Created by Myth-MBP on 28/03/2020 in VSCode
*/

public class P820ShortEncodingOfWords {
    // Tie解法，每个word反转就是前缀
    // 犯的错误: 没有对单词进行排序，先插入长的，这样短的就插入不进去了，就包含在长单词里面了
    private class TrieNode {
        char val;
        // boolean isWord = false;
        TrieNode[] children = new TrieNode[26];
        TrieNode(char ch) {
            this.val = ch;
        }

    }
    public int minimumLengthEncodingError(String[] words) {
        TrieNode root = new TrieNode(' ');
        TrieNode p = null;
        int count = 0;
        Arrays.sort(words, (s1,s2) -> (s2.length()-s1.length()));
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            p = root;
            for (int j = word.length()-1; j >= 0; j--) {
                if (p.children[word.charAt(j)-'a'] == null) {
                    p.children[word.charAt(j)-'a'] = new TrieNode(word.charAt(j));
                    count++;
                }
                p = p.children[word.charAt(j)-'a'];
            } 
        }
        // 最后统计
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) count++;
        }
        return count;
    }
    // ============== 修改后的做法 =========
    private class Trie {
        private TrieNode root;
        Trie() {
            this.root = new TrieNode(' ');
        }
        public int insert(String word) {
            TrieNode p = this.root;
            boolean flag = false;
            for (int j = word.length()-1; j >= 0; j--) {
                if (p.children[word.charAt(j)-'a'] == null) {
                    p.children[word.charAt(j)-'a'] = new TrieNode(word.charAt(j));
                    flag = true;
                }
                p = p.children[word.charAt(j)-'a'];
            }
            return flag ? word.length()+1 : 0;
        }
    }
    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        int count = 0;
        Arrays.sort(words, (s1,s2) -> (s2.length()-s1.length()));
        for (int i = 0; i < words.length; i++) {
            count += trie.insert(words[i]);
        }
        return count;
    }
    public static void main(String[] args) {
        P820ShortEncodingOfWords p820 = new P820ShortEncodingOfWords();
        String[] words0 = {"time", "me", "bell"};
        String[] words1 = {"me","time","bell"};
        String[] words2 = {"m"};
        String[] words3 = {"time", "atime", "btime"};  // 如果不先插入长的单词，此Testcase就会出错
        System.out.println(p820.minimumLengthEncoding(words0));  // 10
        System.out.println(p820.minimumLengthEncoding(words1));  // 10
        System.out.println(p820.minimumLengthEncoding(words2));  // 2  
        System.out.println(p820.minimumLengthEncoding(words3));  // 12
    }
}