package list;

import util.ListNode;
import util.ListUtil;

/**
 * Title: 2. 两数相加
 * Desc:  给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 445题的链表不是逆序方式存储的，该如何去做。
 * Created by Myth on 9/4/2019
 */
public class P2AddTwoNumbers {
    // 个人第一次写的代码（用了三个循环、链表初始为空处理的不太合适）
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2, r = null;
        ListNode head = null;
        int sum, carry = 0;
        while (p != null && q != null) {
            sum = (p.val + q.val + carry) > 9 ? (p.val + q.val + carry - 10) : (p.val + q.val + carry);
            carry = (p.val + q.val + carry) > 9 ? 1 : 0;
            if (r == null) {  // 链表初始为空，此处就会多很多判断
                r = new ListNode(sum);
                head = r;
            } else {
                r.next = new ListNode(sum);
                r = r.next;
            }
            p = p.next;
            q = q.next;
        }
        while (p != null) {
            sum = (p.val + carry) > 9 ? (p.val + carry - 10) : (p.val + carry);
            carry = (p.val + carry) > 9 ? 1 : 0;
            r.next = new ListNode(sum);
            r = r.next;
            p = p.next;
        }
        while (q != null) {
            sum = (q.val + carry) > 9 ? (q.val + carry - 10) : (q.val + carry);
            carry = (q.val + carry) > 9 ? 1 : 0;
            r.next = new ListNode(sum);
            r = r.next;
            q = q.next;
        }
        if (carry != 0) {
            r.next = new ListNode(carry);
            r = r.next;
        }
        return head;
    }
    // 修改的精炼代码
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);  // 初始创建一个头指针，就可以避免判断当前指针是否为空
        ListNode cur = head;
        int sum = 0;  // 余数、和使用一个变量来表示
        while (l1 != null || l2 != null || sum != 0) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10);  // 使用取余和取整
            cur = cur.next;
            sum = sum / 10;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = ListUtil.stringToListNode("[2,4,3]");
        ListNode l2 = ListUtil.stringToListNode("[5,6,4]");

        ListNode l3 = ListUtil.stringToListNode("[1,8]");
        ListNode l4 = ListUtil.stringToListNode("[0]");
        ListNode l5 = ListUtil.stringToListNode("[9,9,9]");
        P2AddTwoNumbers p2 = new P2AddTwoNumbers();
        ListUtil.prettyPrintLinkedList(p2.addTwoNumbers(l1, l2));
        ListUtil.prettyPrintLinkedList(p2.addTwoNumbers(l3, l4));
        ListUtil.prettyPrintLinkedList(p2.addTwoNumbers(l2, l5));
    }
}
