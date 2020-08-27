package list.rotate;

import util.ListNode;
import util.ListUtil;

/**
* Title: 61. 旋转链表
* Desc: 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
* Created by Myth-PC on 10/02/2020 in VSCode
*/
public class P61RotateList {
    // 快指针先走k-1步， 若快指针先为null说明链表长度小于k, 则快指针走 k%n
    // 最终慢指针为新头结点，快指针.next = head
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode slow = head, fast = head, ret = null;
        int i = 0;
        for (i = 0; i < k; i++) {
            if (fast == null) break;
            fast = fast.next;
        }
        // k == 链表长度
        if (i == k && fast == null) return head;
        if (i < k) {
            k = k % i;
            fast = head;
            for (i = 0; i < k; i++) {
                fast = fast.next;
            }    
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = head;
        ret = slow.next;
        slow.next = null;
        return ret;
    }
    public static void main(String[] args) {
        ListNode head = ListUtil.stringToListNode("[1,2,3,4,5]");
        P61RotateList p61 = new P61RotateList();
        ListUtil.prettyPrintLinkedList(p61.rotateRight(head, 7));

    }
}