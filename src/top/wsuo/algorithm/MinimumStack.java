package top.wsuo.algorithm;

import java.util.Stack;

/**
 * 最小栈的线性实现
 *
 * @Author shuo wang
 * @Date 2020/4/3 0003 9:31
 * @Version 1.0
 */
public class MinimumStack {
    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    /**
     * 入栈操作
     * @param element 插入的元素
     */
    private void push(int element) {
        mainStack.push(element);
        // 如果辅助栈为空并且小于辅助栈的栈顶元素则出栈
        if (minStack.isEmpty() || minStack.peek() >= element) {
            minStack.push(element);
        }
    }

    /**
     * 出栈操作
     * @return 返回栈顶元素
     */
    private Integer pop() {
        // 如果出栈元素和辅助栈顶元素相等则出栈
        if (mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }

        return mainStack.pop();
    }

    /**
     * 获取栈的最小元素
     * @return 返回最小栈
     */
    private int getMin() throws Exception {
        if (mainStack.isEmpty()) {
            throw new Exception("stack is empty");
        }

        return minStack.peek();
    }

    public static void main(String[] args) throws Exception {
        MinimumStack stack = new MinimumStack();
        stack.push(4);
        stack.push(9);
        stack.push(7);
        stack.push(3);
        stack.push(8);
        stack.push(5);
        System.out.println(stack.toString());
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.toString());
        System.out.println(stack.getMin());
    }

    @Override
    public String toString() {
        return "MinimumStack{" +
                "mainStack=" + mainStack +
                ", minStack=" + minStack +
                '}';
    }
}
