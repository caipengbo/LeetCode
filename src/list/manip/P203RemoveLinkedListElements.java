package list.manip;

import util.ListNode;

/**
 * Title: 203. 移除链表元素
 * Desc: 删除链表中等于给定值 val 的所有节点。
 * Created by Myth-Lab on 10/9/2019
 */
public class P203RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        int temp = (val == -1) ? -2 : -1;
        ListNode dummy = new ListNode(temp);
        dummy.next = head;
        ListNode p = dummy;
        while(p.next != null) {
            if(p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return dummy.next;
    }
}