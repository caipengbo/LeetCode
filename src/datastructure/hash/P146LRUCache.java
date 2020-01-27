package datastructure.hash;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Title: 146. LRU (最近最少使用) 缓存机制 
 * Desc: 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。 写入数据 put(key, value)
 * - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时， 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。 
 * Created by Myth-PC on 26/01/2020 in VSCode
 */
// 按访问时间
    // Hash + 双链表实现 LinkedHashMap
class Node {
    int key;
    int val;
    Node before;
    Node after;
    Node(int key, int val, Node before, Node after) {
        this.key = key;
        this.val = val;
        this.before = before;
        this.after = after;
    }
} 
class LRUCache {
    Map<Integer, Node> cache;
    Node head, tail;
    int empty;
    public LRUCache(int capacity) {
        cache = new HashMap<>(capacity);
        empty = capacity;
        head = new Node(-1, -1, null, tail);
        tail = new Node(-1, -1, head, null);
    }
    
    public int get(int key) {
        Node node = cache.getOrDefault(key, null);
        if (node == null) {
            System.out.println(-1);
            return -1;
        }
        // 将刚访问的节点移动到尾部
        node.before.after = node.after;
        node.after.before = node.before;
        tail.before.after = node;
        node.before = tail.before;
        node.after = tail;
        tail.before = node;
        System.out.println(node.val);
        return node.val;
    }
    
    public void put(int key, int value) {
        Node node = null;
        if (cache.containsKey(key)) {  // 本来就有, 在双链表中去除
            node = cache.get(key);
            node.before.after = node.after;
            node.after.before = node.before;
            empty++;
        } 
        // 去除头部元素
        if (empty <= 0 && head.after != tail) {
            cache.remove(head.after.key);  // Node中要存放key
            head.after.after.before = head;
            head.after = head.after.after;
            empty++;
        }
        if (empty <= 0) return;
        // 尾部追加节点
        if (node == null) {
            node = new Node(key, value, null, null);
            cache.put(key, node);
        }
        tail.before.after = node;
        node.before = tail.before;
        node.after = tail;
        node.val = value;
        tail.before = node;
        empty--;
    }
}
class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private int capacity;

    LRULinkedHashMap(int capacity) {
        // 初始大小，0.75是装载因子，true是表示按照访问时间排序
        super(capacity, 0.75f, true);
        //传入指定的缓存最大容量
        this.capacity = capacity;
    }

    /**
     * 实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

// 使用 LinkedHashMap
class LRUCache2 {
    LRULinkedHashMap<Integer, Integer> cache;
    public LRUCache2(int capacity) {
        cache = new LRULinkedHashMap<>(capacity);
    }
    
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        cache.put(key, value);
    }
}
public class P146LRUCache {
    
    public static void main(String[] args) {
      
        LRUCache cache = new LRUCache(1);
        cache.put(2, 1);
        cache.get(2);       
        cache.put(3, 2);    
        cache.get(2);      
        cache.get(3);  
    }
}