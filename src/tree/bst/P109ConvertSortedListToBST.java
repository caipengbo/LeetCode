package tree.bst;

import util.ListNode;
import util.ListUtil;
import util.TreeNode;
import util.TreeUtil;

/**
* Title: 109. 有序链表转换二叉搜索树
* Desc: 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
* Created by Myth-PC on 09/02/2020 in VSCode
*/
public class P109ConvertSortedListToBST {
    // 链表，一个每次走1步，一个每次走2步
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode before = head, cur = head, after = head;
        while (after.next != null && after.next.next != null) {
            before = cur;
            cur = cur.next;
            after = after.next.next;
        }
        TreeNode root = null;
        if (after.next == null) {
            before.next = null;  // 左半部分（截断）
            root = new TreeNode(cur.val);
            root.left = sortedListToBST(head);
            root.right = sortedListToBST(cur.next);  // 右半部分
        } else if (after.next.next == null) {
            root = new TreeNode(cur.next.val);
            ListNode right = cur.next.next;
            cur.next = null;
            root.left = sortedListToBST(head);
            root.right = sortedListToBST(right);  // 右半部分
        }
        return root;
    }

    // 不用截断链表，优化写法
    public TreeNode sortedListToBST2(ListNode head) {
        // while (tail.next != null) tail = tail.next;
        return create(head, null);
    }
    private TreeNode create(ListNode head, ListNode tail) {  // 左闭右开
        if (head == tail) return null;
        if (head.next == tail) return new TreeNode(head.val);
        ListNode slow = head, fast = head;
        
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = create(head, slow); 
        root.right = create(slow.next, tail);
        return root;
    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        P109ConvertSortedListToBST p109 = new P109ConvertSortedListToBST();
        TreeUtil.prettyPrintTree(p109.sortedListToBST2(node0));

    }
}