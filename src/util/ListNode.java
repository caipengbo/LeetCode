package util;

/**
 * Title: Singly-Linked List
 * Desc: Definition for singly-linked list.
 * Created by Myth on 5/12/2019
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
    }
    public ListNode(int x, ListNode nextNode) {
        val = x;
        next = nextNode;
    }
}