package datastructure.hash;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
* Title:  460. LFU缓存
* Desc: 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put
* Created by Myth-PC on 26/01/2020 in VSCode
*/

class LFUCache {
    class Node {
        int key;
        int value;
        int freq = 1;
    
        public Node() {}
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> cache;  // 存储缓存的内容
    Map<Integer, LinkedHashSet<Node>> freqMap; // 存储每个频次对应的双向链表
    int size;
    int capacity;
    int min; // 存储当前最小频次

    public LFUCache(int capacity) {
        cache = new HashMap<> (capacity);
        freqMap = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        freqInc(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            freqInc(node);
        } else {
            if (size == capacity) {
                Node deadNode = removeNode();
                cache.remove(deadNode.key);
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            size++;     
        }
    }

    void freqInc(Node node) {
        // 从原freq对应的链表里移除, 并更新min
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        set.remove(node);
        if (freq == min && set.size() == 0) { 
            min = freq + 1;
        }
        // 加入新freq对应的链表
        node.freq++;
        LinkedHashSet<Node> newSet = freqMap.get(freq + 1);
        if (newSet == null) {
            newSet = new LinkedHashSet<>();
            freqMap.put(freq + 1, newSet);
        }
        newSet.add(node);
    }

    void addNode(Node node) {
        LinkedHashSet<Node> set = freqMap.get(1);
        if (set == null) {
            set = new LinkedHashSet<>();
            freqMap.put(1, set);
        } 
        set.add(node); 
        min = 1;
    }

    Node removeNode() {
        LinkedHashSet<Node> set = freqMap.get(min);
        Node deadNode = set.iterator().next();
        set.remove(deadNode);
        return deadNode;
    }
}
// =======  自己实现的 有Bug=========
class LFUNode {
    int key;
    int val;
    int freq;
    LFUNode(int k, int v) {
        key = k;
        val = v;
        freq = 0;
    }
}
class LFUCache2 {
    HashMap<Integer, LFUNode> cache;
    // freq - Set
    HashMap<Integer, LinkedHashSet<LFUNode>> freqMap;

    int minFrequency;

    int size, capacity;
    public LFUCache2(int capacity) {
        cache = new HashMap<>();
        freqMap = new HashMap<>();
        minFrequency = 0;
        this.size = 0;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (capacity <= 0) return -1;
        LFUNode node = cache.getOrDefault(key, null);
        if (node == null) {
            System.out.println(-1);
            return -1;
        }
        // 更新频率
        LinkedHashSet<LFUNode> nodeSet = freqMap.get(node.freq);
        nodeSet.remove(node); 
        if (minFrequency == node.freq && nodeSet.size() == 0) minFrequency += 1;  // 更新最小频率
        node.freq += 1;
        addFreqMap(node.freq, node);
        
        System.out.println(node.val);
        return node.val;
    }
    // size 和 重复之间 关系
    public void put(int key, int value) {
        if (capacity <= 0) return;
        LFUNode node = cache.getOrDefault(key, null);
        if (node != null) {  // 存在，从原freqMap中删除
            LinkedHashSet<LFUNode> nodeSet = freqMap.get(node.freq);
            cache.remove(node.key);
            nodeSet.remove(node); 
            size--;
        }
        if (size >= capacity) {
            LinkedHashSet<LFUNode> nodeSet = freqMap.get(minFrequency);
            LFUNode oldNode = nodeSet.iterator().next();
            cache.remove(oldNode.key);
            nodeSet.remove(oldNode);  // 删除最小频率Set中的第一个元素
            size--;
        }
        node = new LFUNode(key, value);
        cache.put(key, node);
        addFreqMap(0, node);
        minFrequency = 0;
        size++;
    }

    private void addFreqMap(int freq, LFUNode node) {
        LinkedHashSet<LFUNode> newNodeSet = freqMap.getOrDefault(freq, new LinkedHashSet<>());
        newNodeSet.add(node);
        freqMap.put(freq, newNodeSet);
    }
}

public class P460LFUCache {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        cache.get(2);       // 返回 -1 (未找到key 2)
        cache.get(3);       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        cache.get(1);       // 返回 -1 (未找到 key 1)
        cache.get(3);       // 返回 3
        cache.get(4);       // 返回 4
        //-----
        // cache.put(3, 1);
        // cache.put(2, 1);
        // cache.put(2, 2);
        // cache.put(4, 4);
        // cache.get(2); 
    }
    
}