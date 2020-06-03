package list.rotate;

import util.ListNode;
import util.ListUtil;

/**
 * Title: 24. 两两交换链表中的节点(和拆分链表很像)
 * Desc: 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * Created by Myth on 9/6/2019
 */
public class P24SwapNodesinPairs {
    // 节点的移动（三个节点）
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy, swap1, swap2;
        while (cur.next != null && cur.next.next != null) {
            swap1 = cur.next;
            swap2 = cur.next.next;
            cur.next = swap2;
            swap1.next = swap2.next;
            swap2.next = swap1;
            cur = swap1;
        }
        return dummy.next;
    }
    // 递归写法(考虑两个节点)
    public ListNode swapPairsRecursive(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode nextNode = head.next;
        head.next = swapPairsRecursive(nextNode.next);
        nextNode.next = head;
        return nextNode;
    }
    // 第二遍
    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode before = dummy, cur = head, after;
        
        while (cur != null) {
            if (cur.next == null) {
                before.next = cur;
                before = cur;
                break;
            } else {
                after = cur.next.next;
                before.next = cur.next;
                cur.next.next = cur;
                before = cur;
                cur = after;
            }
        }
        before.next = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = ListUtil.stringToListNode("[1,2,3,4]");
        P24SwapNodesinPairs p24 = new P24SwapNodesinPairs();
        ListUtil.prettyPrintLinkedList(p24.swapPairs(head));
    }
}
