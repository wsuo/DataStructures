package top.wsuo.tree;

import org.jetbrains.annotations.Nullable;
import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author shuo wang
 * @Date 2020/4/1 0001 11:15
 * @Version 1.0
 */
public class MyTree {
    public static class TreeNode {
        int data;
        TreeNode leftNode;
        TreeNode rightNode;

        TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 递归的调用方法来创造树，传入一个列表不断的去除其中的元素
     *
     * @param inputList
     * @return
     */
    @Nullable
    private static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;

        if (inputList == null || inputList.isEmpty()) {
            return null;
        }

        Integer data = inputList.removeFirst();

        if (data != null) {
            node = new TreeNode(data);
            node.leftNode = createBinaryTree(inputList);
            node.rightNode = createBinaryTree(inputList);
        }

        return node;
    }


    /**
     * 二叉树的前序遍历
     *
     * @param node
     */
    private static void preOrderTraver(TreeNode node) {

        if (node == null) {
            return;
        }

        System.out.print(node.data + "->");
        preOrderTraver(node.leftNode);
        preOrderTraver(node.rightNode);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    private static void inOrderTraver(TreeNode node) {

        if (node == null) {
            return;
        }

        inOrderTraver(node.leftNode);
        System.out.print(node.data + "->");
        inOrderTraver(node.rightNode);
    }

    private static void postOrderTraver(TreeNode node) {

        if (node == null) {
            return;
        }

        postOrderTraver(node.leftNode);
        postOrderTraver(node.rightNode);
        System.out.print(node.data + "->");
    }

    /**
     * 使用栈来实现前序遍历
     */
    public static void preOrderTraverWithStack(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;

        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                // 迭代访问节点的左孩子并入栈
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftNode;
            }
            // 现在考虑出栈
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightNode;
            }
        }
    }

    /**
     * 二叉树的层序遍历
     */
    public static void levelOrderTraver(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            System.out.println(treeNode);

            if (node.leftNode != null) {
                queue.offer(treeNode.leftNode);
            }

            if (node.rightNode != null) {
                queue.offer(treeNode.rightNode);
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>(Arrays.asList(
                3, 2, 9, null, null, 10, null, null, 8, null, 4));
        TreeNode binaryTree = createBinaryTree(linkedList);
        System.out.println("前序遍历是:");
        preOrderTraver(binaryTree);
        System.out.println("\n中序遍历是:");
        inOrderTraver(binaryTree);
        System.out.println("\n后序遍历是:");
        postOrderTraver(binaryTree);
    }

}
