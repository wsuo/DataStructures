package top.wsuo.algorithm;

/**
 * @Author shuo wang
 * @Date 2020/4/3 0003 9:05
 * @Version 1.0
 * 有环链表
 */
public class LoopedLinkedList {
    /**
     * 通过两个指针来判断,就是追及问题,一个指针移动一步,另一个指针移动两步
     * 在java中其实就是一个节点对象
     *
     * @param head 头结点
     * @return 但会是否是有环的
     */
    public static boolean isCycle(Node head) {
        Node p1 = head;
        Node p2 = head;

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }

        return false;
    }

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
