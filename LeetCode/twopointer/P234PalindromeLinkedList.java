package twopointer;

import util.ListNode;
import util.ListUtil;

/**
 * Title: 234. 回文链表
 * Desc: 请判断一个链表是否为回文链表。用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题
 * Created by Myth-Lab on 10/14/2019
 */
public class P234PalindromeLinkedList {
    // 寻找mid，翻转后半部分，最后比较
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 偶数时，slow指向后半部分的开头，奇数时，slow指向正中间(让slow再多走一个)
        if (fast != null) slow = slow.next;
        slow = reverse(slow);

        fast = head;
        while (slow != null) {
            if (fast.val != slow.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null, p = head, q;
        while (p != null) {
            q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }

    public static void main(String[] args) {
        P234PalindromeLinkedList p234 = new P234PalindromeLinkedList();
        ListNode listNode = ListUtil.stringToListNode("[1,1,2,1]");
        p234.isPalindrome(listNode);
    }
}
