package list.remove;

import util.ListNode;

/**
 * Title: 83. 删除排序链表中的重复元素
 * Desc: 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * Created by Myth-Lab on 10/9/2019
 */
public class P83RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = head;
        while(p != null && p.next != null) {
            if(p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return dummy.next;
    }
}
