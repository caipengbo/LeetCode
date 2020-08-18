package datastructure.hash;

import java.util.*;


public class P692TopKFrequencyWord {

    public static void main(String[] args) {
        
    }

    class Node {
        String name;
        int freq = 0;
        Node(String name, int freq) {
            this.name = name;
            this.freq = freq;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Node> map = new HashMap<>();
        for (String word : words) {
            Node node = map.getOrDefault(word, new Node(word, 0));
            node.freq += 1;
            map.put(word, node);
        }
        
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((o1, o2) -> (o1.freq == o2.freq ? o1.name.compareTo(o2.name) : o2.freq - o1.freq));
        for (String key : map.keySet()){
            maxHeap.add(map.get(key));
        }
        List<String> ret = new ArrayList<>();

        while (!maxHeap.isEmpty() && k > 0) {
            k--;
            ret.add(maxHeap.poll().name);
        }
        return ret;
    }

}