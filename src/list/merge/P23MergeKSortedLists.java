package list.merge;

import util.ListNode;
import util.ListUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Title: 23. 合并K个排序链表(使用堆和不使用堆)
 * Desc: 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * Created by Myth on 9/8/2019
 */
public class P23MergeKSortedLists {

    // 使用大小为K的堆，找到最小的点，链接到结果链表，然后此节点的下一个节点加入堆
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // init
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> (o1.val - o2.val));
        int len = lists.length;
        for (int i = 0; i < len; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
                // lists[i] = lists[i].next;
            }
        }
        // loop
        ListNode dummy = new ListNode(-1), p = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            // 重点，也是没想到的地方！
            if (node.next != null) {
                queue.add(node.next);
            }
        }
        return dummy.next;
    }
    // 使用堆
    public ListNode mergeKLists3(ListNode[] lists) {
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
    // 不使用堆
    public ListNode mergeKLists2(ListNode[] lists) {
        return mergeKLists2(lists, 0, lists.length - 1);
    }
    public ListNode mergeKLists2(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        else if (start < end) {
            int mid = start + (end - start) / 2;
            ListNode left = mergeKLists2(lists, start, mid);
            ListNode right = mergeKLists2(lists, mid+1, end);
            return mergeTwoListsRecursive(left, right);
        } else return null;
    }
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }
    // [[-2,-1,-1,-1],[]]
    public static void main(String[] args) {
        P23MergeKSortedLists p23 = new P23MergeKSortedLists();
        ListNode[] listNodes = new ListNode[2];
        listNodes[0] = ListUtil.stringToListNode("[-2,-1,-1,-1]");
        listNodes[1] = ListUtil.stringToListNode("[]");
       // ListUtil.prettyPrintLinkedList(p23.mergeKLists(listNodes));
    }
}
