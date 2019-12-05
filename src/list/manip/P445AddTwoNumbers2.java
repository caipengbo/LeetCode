package list.manip;

import util.ListNode;
import util.ListUtil;

import java.util.Stack;

/**
 * Title: 445. 两数相加 II
 * Desc: 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * 当然可以将链表翻转然后使用2题的思路
 * 进阶:
 *      如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * Created by Myth on 9/5/2019
 */
public class P445AddTwoNumbers2 {
    // 不修改输入链表
    // 难点：1. 预先不只是位数 2. 进位不清楚  3. 计算顺序是从后往前的
    // 使用栈 时间和空间复杂度 O(m+n)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode head= null, cur;
        int sum = 0;
        while (!stack1.empty() || !stack2.empty() || sum != 0) {
            if (!stack1.empty()) sum += stack1.pop();
            if (!stack2.empty()) sum += stack2.pop();
            cur = new ListNode(sum % 10);
            cur.next = head;
            head = cur;
            sum = sum / 10;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = ListUtil.stringToListNode("[7,2,4,3]");
        ListNode l2 = ListUtil.stringToListNode("[5,6,4]");
        P445AddTwoNumbers2 p445 = new P445AddTwoNumbers2();
        ListUtil.prettyPrintLinkedList(p445.addTwoNumbers(l1, l2));
    }
}
