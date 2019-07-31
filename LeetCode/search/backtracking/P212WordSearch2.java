package search.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 212. 单词搜索 II
 * Desc: 使用前缀树
 * Created by Myth on 7/26/2019
 */

public class P212WordSearch2 {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word; // 不使用Boolean，直接把整个word都保存下来
    }
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode p = root;
            for (char c : word.toCharArray()) {
                if (p.children[c-'a'] == null) p.children[c-'a'] = new TrieNode();
                p = p.children[c-'a'];
            }
            p.word = word;
        }
        return root;
    }
    private void backtracking(char[][] board, int i, int j, TrieNode p, List<String> ans) {
        char c = board[i][j];
        if (c == '#' || p.children[c-'a'] == null) return;
        p = p.children[c-'a'];
        if (p.word != null) {
            ans.add(p.word);
            p.word = null; // 防止重复前缀的word
        }
        board[i][j] = '#'; // 防止使用重复单词
        if (i > 0) backtracking(board, i-1, j, p, ans);
        if (j > 0) backtracking(board, i, j-1, p, ans);
        if (i < board.length - 1) backtracking(board, i+1, j, p, ans);
        if (j < board[0].length - 1) backtracking(board, i, j+1, p, ans);
        board[i][j] = c;
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtracking(board, i, j, root, ans);
            }
        }
        return ans;
    }
}
