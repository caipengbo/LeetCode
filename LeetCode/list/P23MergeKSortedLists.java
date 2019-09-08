package list;

import util.ListNode;
import util.ListUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Title: 23. 合并K个排序链表
 * Desc: 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * Created by Myth on 9/8/2019
 */
public class P23MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        if (k == 0) return null;
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<ListNode>(k, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        ListNode head = new ListNode(-1);
        ListNode p = head, temp;
        boolean hasNode = true;
        while (hasNode) {
            hasNode = false;
            for (int i = 0; i < k; i++) {
                if (lists[i] != null) {
                    //  这里也要注意
                    temp = lists[i];
                    lists[i] = lists[i].next;
                    priorityQueue.add(temp);
                    hasNode = true;
                }
            }
        }
        while (!priorityQueue.isEmpty()) {
            p.next = priorityQueue.poll();
            p = p.next;
        }
        p.next = null;  // 必须加这个封尾操作，有可能会出现环
        return head.next;
    }
    // [[-2,-1,-1,-1],[]]
    public static void main(String[] args) {
        P23MergeKSortedLists p23 = new P23MergeKSortedLists();
        ListNode[] listNodes = new ListNode[2];
        listNodes[0] = ListUtil.stringToListNode("[-2,-1,-1,-1]");
        listNodes[1] = ListUtil.stringToListNode("[]");
        ListUtil.prettyPrintLinkedList(p23.mergeKLists(listNodes));
    }
}
