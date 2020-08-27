package list.fastslow;

import util.ListNode;

/**
 * Title:  160. 相交链表
 * Desc: 编写一个程序，找到两个单链表相交的起始节点
 *      - 如果两个链表没有交点，返回 null.
 *      - 在返回结果后，两个链表仍须保持原有的结构。
 *      - 可假定整个链表结构中没有循环。
 *      - 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。如果使用 hash 很简单
 *
 * Created by Myth on 9/11/2019
 */
public class P160IntersectionTwoList {

    // 方法2：如何找到两个链表的差距？ 快的指针走到头后，回到长的链表头部；慢的指针走到头，回到短的链表头部，这样就抵消了 差距
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p = headA, q = headB;
        while (p != q) {
            p = (p == null) ? headB : p.next;
            q = (q == null) ? headA : q.next;
        }
        return p;
    }

    // 方法1：两个链表的数目之差 m - n 是 前半截不相交部分的差， 让长链表先走 m - n 步，然后同步走，然后判断哪一点相交
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        int m = 0, n = 0;
        while (p != null) {
            m++;
            p = p.next;
        }
        while (q != null) {
            n++;
            q = q.next;
        }
        if (m > n) {
            p = headA;
            q = headB;
        } else {
            p = headB;
            q = headA;
        }
        int abs = Math.abs(m- n);
        for (int i = 0; i < abs; i++) {
            p = p.next;
        }
        while (p != null && q != null) {
            if (p == q) return p;
            p = p.next;
            q = q.next;
        }
        return null;
    }
    
}
