package top.wsuo.linkedList;

import java.util.Queue;

/**
 * @Author shoo wang
 * @Date 2020/3/31 0031 16:33
 * @Version 1.0
 */
public class MyLinkedList {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node last;
    private int size;

    /**
     * 链表插入元素
     *
     * @param data  插入值
     * @param index 插入下标
     */
    public void insert(int data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表的实际范围");
        }
        // 插入操作分为 4 种情况
        Node insertNode = new Node(data);

        // 空链表
        if (size == 0) {
            head = insertNode;
            last = insertNode;
        } else if (index == 0) {
            // 在头部插入
            insertNode.next = head;
            head = insertNode;
        } else if (index == size) {
            // 在尾部插入
            last.next = insertNode;
            // insertNode 作为新的 last
            last = insertNode;
        } else {
            // 在中间插入，问题是我不知道怎么获取它的位置
            Node preNode = get(index - 1);
            // 从右往左思考
            insertNode.next = preNode.next;
            preNode.next = insertNode;
        }

        size++;
    }


    /**
     * 链表删除元素
     *
     * @param index 删除元素的位置
     */
    public Node remove(int index) {

        // 差错检测
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表的实际范围");
        }

        Node removeNode = null;

        if (index == 0) {
            // 删除头部元素
            removeNode = head;
            head = head.next;
        } else if (index == size) {
            // 删除尾元素, 要先得到 尾节点的前一个元素
            Node lastNode = get(size - 1);
            removeNode = last;
            last = lastNode;
            lastNode = null;
        } else {
            // 删除中间元素
            Node preNode = get(index - 1);
            removeNode = preNode.next;
            preNode.next = preNode.next.next;
        }

        size--;

        return removeNode;
    }

    /**
     * 查找元素
     *
     * @param index 查找元素的位置
     * @return 返回节点
     */
    public Node get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表的实际范围");
        }

        Node temp = head;

        // 查找需要遍历
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp;

    }

    /**
     * 输出链表
     */
    public void output() {
        Node temp = head;

        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insert(3, 0);
        myLinkedList.insert(7, 1);
        myLinkedList.insert(9, 2);
        myLinkedList.insert(5, 3);
        myLinkedList.insert(6, 1);
        myLinkedList.remove(0);
        myLinkedList.output();
    }


}
