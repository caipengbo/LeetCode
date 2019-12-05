package list.merge;

import util.ListNode;

/**
 * Title: 148. 排序链表（归并排序）
 * Desc: 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 注意：
 *      递归版本使用到了 系统栈，所以空间复杂度不是是lg(n)
 *      链表的归并需要移动指针找到链表的中点
 * Created by Myth on 9/9/2019
 */
public class P148SortList {
    // 递归版本 （自顶向下）
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 将链表分为两段
        ListNode p = head, q = head, pre = head;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }

        pre.next = null;  // 截断链表
        ListNode left = sortList(head);
        ListNode right = sortList(p);
        return mergeTwoListsRecursive(left, right);
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

    // 非递归版本（自底向上）
}
