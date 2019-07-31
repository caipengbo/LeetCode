package search.bfs;

import java.util.*;

/**
 * Title: 127. 单词接龙
 * Desc: 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *      如果不存在这样的转换序列，返回 0。
 *      所有单词具有相同的长度。
 *      所有单词只由小写字母组成。
 *      字典中不存在重复的单词。
 *      你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * Created by Myth on 7/27/2019
 */
// 广度优先遍历 & 双向广度优先
public class P127WordLadder {
    // BFS
    private int bfs(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<String>();
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0; // endWord不在list中，直接返回
        queue.add(beginWord);
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size(); i > 0; i--) {
                String word = queue.poll();
                // 扩展新的一层节点 Wordlist = expand(word)
                char[] wordChars = word.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char ch = wordChars[j];
                    for (char k = 'a'; k <= 'z'; k++) { // 替换每一位， 产生新的单词比较一下，是否在wordList中
                        if (ch == k) continue;
                        wordChars[j] = k;
                        String newWord = new String(wordChars);
                        // 如果最终结果在 新获得的节点中 ，返回
                        if (newWord.equals(endWord)) return step+1;
                        if (!set.contains(newWord)) continue;
                        set.remove(newWord); // 从wordList中移除，放置重复操作
                        queue.add(newWord);
                    }
                    wordChars[j] = ch; // 恢复被替换的位置
                }
            }
        }
        // 没找到
        return 0;
    }
    private int bibfs(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add(beginWord);
        q2.add(endWord);
        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            ++step;
            // 让元素少的的作q1
            if (q1.size() > q2.size()) {
                Set<String> temp = q1;
                q1 = q2;
                q2 = temp;
            }
            Set<String> q = new HashSet<>();
            for (String word : q1) {
                char[] wordChars = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char ch = wordChars[i];
                    for (char c = 'a'; c < 'z'; c++) {
                        if (ch == c) continue;
                        wordChars[i] = c;
                        String newWord = new String(wordChars);
                        if (q2.contains(newWord)) return step+1;
                        if (!wordSet.contains(newWord)) continue;
                        wordSet.remove(newWord);
                        q.add(newWord);
                    }
                    wordChars[i] = ch;
                }
            }
            q1 = q;
        }
        return 0;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return bibfs(beginWord, endWord, wordList);
    }

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        P127WordLadder p127 = new P127WordLadder();
        System.out.println(p127.ladderLength("hit", "cog", wordList));
    }
}
