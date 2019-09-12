package list;

import util.ListNode;
import util.ListUtil;

/**
 * Title: 328. 奇偶链表（链表的拆分）
 * Desc: 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 *      请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 *      应当保持奇数节点和偶数节点的相对顺序。
 *      链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * Created by Myth on 9/11/2019
 */
public class P328OddEvenNode {
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode p = head, q = head.next;
        ListNode evenHead = head.next;
        while (p.next != null && p.next.next != null) {  // 判断条件是 p.next,也就是说，是 到 链表的最后一个元素，这个时候需要进行封尾操作
            p.next = p.next.next;
            q.next = q.next.next;
            p = p.next;
            q = p.next;
        }
        p.next = evenHead;  // 封尾操作(该题比较特殊，如果是单纯的将一个链表拆成一个，需要进行封尾)
        // 因为 p.next == null 跳出循环， p
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListUtil.stringToListNode("[1,2,3,4,5,6,7,8]");
        ListUtil.prettyPrintLinkedList(P328OddEvenNode.oddEvenList(head));
    }
}
