package list;

/**
 * Title: 707. 设计链表
 * Desc:
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。
 * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 *
 * Created by Myth-PC on 2019-09-10
 */
class Node{
    int val;
    Node next;
    public Node(int val) {
        this.val = val;
        next = null;
    }
}
class MyLinkedList {

    /** Initialize your data structure here. */
    private int length;
    private Node dummyHead;
    public MyLinkedList() {
        length = 0;
        dummyHead = new Node(-1);
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (length == 0 || index < 0 || index >= length) return -1;
        Node p = dummyHead;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        newNode.next = dummyHead.next;
        dummyHead.next = newNode;
        length++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node p = dummyHead;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node(val);
        length++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > length) return;
        if (index == length) {
            addAtTail(val);
            return;
        }
        if (index < 0) {
            addAtHead(val);
            return;
        }
        Node p = dummyHead, pre = dummyHead;

        for (int i = 0; i <= index; i++) {
            pre = p;
            p = p.next;
        }
        Node newNode = new Node(val);
        newNode.next = p;
        pre.next = newNode;
        length++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (length == 0 || index < 0 || index >= length) return;
        Node p = dummyHead, pre = dummyHead;
        for (int i = 0; i <= index; i++) {
            pre = p;
            p = p.next;
        }
        pre.next = p.next;
        length--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
public class P707DesignLinkedList {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        System.out.println(linkedList.get(0));  // -1
        linkedList.addAtIndex(1, 2);
        System.out.println(linkedList.get(0)); // 1
        System.out.println(linkedList.get(1)); // -1
        linkedList.addAtIndex(0, 1);
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
    }
}
