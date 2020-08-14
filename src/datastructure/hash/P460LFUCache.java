package datastructure.hash;

import java.util.HashMap;
import java.util.Map;;

/**
* Title:  460. LFU缓存
* Desc: 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put
* Created by Myth-PC on 26/01/2020 in VSCode
*/
/**
 * HashMap<Integer, Node> cache 存缓存的内容; 将写法 1 写法 2 中的 freqMap 不再用 HashMap 来表示，
 * 而是直接用双向链表 DoublyLinkedList firstLinkedList; DoublyLinkedList lastLinkedList，
 * 省去了一些哈希相关的耗时，也不需要用 min 来存储最小频次了，lastLinkedList.pre 这条 DoublyLinkedList 
 * 即为最小频次对应的 Node 双向链表，lastLinkedList.pre.tail.pre 
 * 这个 Node 即为最小频次的双向链表中的所有 Node 中最先访问的 Node，即容量满了后要删除的 Node。
 */
class FNode {
    int key;
    int value;
    int freq = 1;
    FNode pre;
    FNode post;

    public FNode() {}
    
    public FNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class DoublyLinkedList {
    // 两个傀儡节点
    FNode head;
    FNode tail;

    public DoublyLinkedList() {
        head = new FNode();
        tail = new FNode();
        head.post = tail;
        tail.pre = head;
    }
    // 删除任意Nod
    void removeNode(FNode node) {
        node.pre.post = node.post;
        node.post.pre = node.pre;
    }
    // 插入到head到后面第一个位置（头插法）
    void addNode(FNode node) {
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
        node.pre = head;
    }
}

class LFUCache {
    Map<Integer, FNode> cache; // 存储缓存的内容
    Map<Integer, DoublyLinkedList> freqMap; // 存储每个频次对应的双向链表
    int size;
    int capacity;
    int min; // 存储当前最小频次

    public LFUCache(int capacity) {
        cache = new HashMap<> (capacity);
        freqMap = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        FNode node = cache.get(key);
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
        FNode node = cache.get(key);
        if (node != null) {
            node.value = value;  // 覆盖值
            freqInc(node);  // 增加频率
        } else {
            // 满了、淘汰
            if (size == capacity) {
                DoublyLinkedList minFreqLinkedList = freqMap.get(min);  // 获取最低频次对应到双向链表
                cache.remove(minFreqLinkedList.tail.pre.key);  // 相同频率，去除最老到那个
                minFreqLinkedList.removeNode(minFreqLinkedList.tail.pre); // 这里不需要维护min, 因为下面add了newNode后min肯定是1.
                size--;
            }
            // 插入新元素
            FNode newNode = new FNode(key, value);
            cache.put(key, newNode);
            DoublyLinkedList linkedList = freqMap.get(1);
            if (linkedList == null) {
                linkedList = new DoublyLinkedList();
                freqMap.put(1, linkedList);  // 插入到频率为1到元素里
            }
            linkedList.addNode(newNode);
            size++;  
            min = 1;   
        }
    }
    // 访问次数++
    void freqInc(FNode node) {
        // 从原freq对应的链表里移除, 并更新min
        int freq = node.freq;
        DoublyLinkedList linkedList = freqMap.get(freq);
        linkedList.removeNode(node);  // 原链表中删除
        // 当前linkedList没有节点（更新min）
        if (freq == min && linkedList.head.post == linkedList.tail) { 
            min = freq + 1;
        }
        // 加入新freq对应的链表
        node.freq++;
        linkedList = freqMap.get(freq + 1);
        if (linkedList == null) {
            linkedList = new DoublyLinkedList();
            freqMap.put(freq + 1, linkedList);
        }
        linkedList.addNode(node);
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