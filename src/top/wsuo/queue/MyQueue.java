package top.wsuo.queue;

public class MyQueue {
    // 用数组实现循环队列
    private int[] array;
    private int front;
    private int rear;

    private MyQueue(int capacity) {
        this.array = new int[capacity];
    }

    /**
     * 入队操作
     *
     * @param element 入队元素
     * @throws Exception 异常
     */
    private void enQueue(int element) throws Exception {
        if ((rear + 1) % array.length == front) {
            throw new Exception("队列已满");
        }
        array[rear] = element;
        rear = (rear + 1) % array.length;
    }

    /**
     * 出队
     *
     * @return 返回出队的值
     * @throws Exception 异常
     */
    private int deQueue() throws Exception {
        if (rear == front) {
            throw new Exception("队列为空");
        }

        int deQueueElement = array[front];
        front = (front + 1) % array.length;
        return deQueueElement;
    }

    public void output() {
        for (int i = front; i != rear; i = (i + 1) % array.length) {
            System.out.print(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        MyQueue myQueue = new MyQueue(6);
        myQueue.enQueue(3);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(8);
        myQueue.enQueue(1);
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.enQueue(2);
        myQueue.enQueue(4);
        myQueue.enQueue(9);
        myQueue.output();
    }
}