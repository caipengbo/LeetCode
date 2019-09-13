package list.manip;

import util.ListNode;
import util.ListUtil;

/**
 * Title: 147. 对链表进行插入排序
 * Desc:
 * Created by Myth on 9/10/2019
 */
public class P147InsertionSortList {
    // 增序
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode cur = head, p, next;
        while (cur != null) {
            p = dummy;  // 该处可以优化
            next = cur.next;
            while (p.next != null && cur.val > p.next.val) {
                p = p.next;
            }
            // 注意以下两行代码
            cur.next = p.next;
            p.next = cur;
            cur = next;
        }
        return dummy.next;
    }
    // 优化
    public ListNode insertionSortListOpt(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode cur = head, p = null, next;
        while (cur != null) {
            // p = dummy; 每次都把 p 置在了头部位置
            if (p == null || p.val >= cur.val) p = dummy;  // 优化： 有时候不必将 p 移动至 头部位置
            next = cur.next;
            while (p.next != null && cur.val > p.next.val) {
                p = p.next;
            }
            // 注意以下两行代码
            cur.next = p.next;
            p.next = cur;
            cur = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head1 = ListUtil.stringToListNode("[]");
        ListNode head2 = ListUtil.stringToListNode("[-1,5,3,4,0]");
        P147InsertionSortList p147 = new P147InsertionSortList();
        ListUtil.prettyPrintLinkedList(p147.insertionSortList(head2));

    }
}
