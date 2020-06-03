package list.rotate;

import util.ListNode;
import util.ListUtil;

/**
 * Title: 25. K个一组反转链表 
 * Desc: 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
 * Created by Myth-PC on 08/02/2020 in VSCode
 */
public class P25ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode p = head, q = head;
        // 找到 第 k-1个结点p
        for (int i = 0; i < k; i++) {
            if (p == null) return head;
            q = p;
            p = p.next;
        }
        q.next = null;
        ListNode newHead = reverse(head);
        head.next = reverseKGroup(p, k);
        return newHead;
    }
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode ret = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return ret;
    }
    public static void main(String[] args) {
        ListNode list = ListUtil.stringToListNode("[1,2,3,4,5]");
        P25ReverseNodesInKGroup p25 = new P25ReverseNodesInKGroup();
        ListNode head = p25.reverseKGroup(list, 2);
        System.out.println(list.val);
        ListUtil.prettyPrintLinkedList(head);
    }
}