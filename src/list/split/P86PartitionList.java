package list.split;

import util.ListNode;


/**
* Title: 86. 分隔链表 
* Desc: 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
* 你应当保留两个分区中每个节点的初始相对位置。
* Created by Myth-MBP on 21/08/2020 in VSCode
*/

public class P86PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode p = dummy1, q = dummy2, cur = head;
        while (cur != null) {
            if (cur.val < x) {
                p.next = cur;
                p = p.next;
            } else {
                q.next = cur;
                q = q.next;
            }
            cur = cur.next;
        }
        p.next = dummy2.next;
        q.next = null;
        return dummy1.next;
    }
}