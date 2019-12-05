package advanced;

/**
 * Title: 208. 实现 Trie (前缀树、字典树)
 * Desc: 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作
 * Created by Myth on 7/26/2019
 */
// Trie树的核心思想是空间换时间，利用字符串的公共前缀来减少无谓的字符串比较以达到提高查询效率的目的。
class TrieNode {
    public char val;
    public  boolean isWord;
    public TrieNode[] children = new TrieNode[26]; // a - z
    public TrieNode() {}
    public TrieNode(char c) {
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

class Trie {
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p.children[c - 'a'] == null) {
                p.children[c - 'a'] = new TrieNode(c);
            }
            p = p.children[c - 'a'];
        }
        p.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            if (p.children[word.charAt(i) - 'a'] == null) return false;
            p = p.children[word.charAt(i) - 'a'];
        }
        return p.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (p.children[prefix.charAt(i) - 'a'] == null) return false;
            p = p.children[prefix.charAt(i) - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

public class P208PrefixTreeCalledTrie {

}
