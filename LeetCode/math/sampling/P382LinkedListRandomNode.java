package math.sampling;

import util.ListNode;

import java.util.Random;

/**
 * Title: 382. 链表随机节点（Reservoir Sampling）
 * Desc: 定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
 *
 * 进阶:
 * 如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
 *
 * Created by Myth-Lab on 11/8/2019
 */
public class P382LinkedListRandomNode {
    private ListNode head;
    P382LinkedListRandomNode(ListNode head){
        this.head = head;
    }
    public int getRandom() {
        ListNode p = head;
        int reservoir = p.val;
        p = p.next;
        int i = 1;
        Random random = new Random();
        while (p != null) {
            int rand = random.nextInt((i++)+1);
            if (rand == 0) reservoir = p.val;
            p = p.next;
        }
        return reservoir;
    }
}
