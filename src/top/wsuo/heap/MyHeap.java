package top.wsuo.heap;

import java.util.Arrays;

/**
 * @Author shoo wang
 * @Date 2020/4/1 0001 14:20
 * @Version 1.0
 */
public class MyHeap {

    /**
     * 上浮操作
     *
     * @param array 待操作的数组
     */
    private static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        // temp 保存插入的叶子节点的值
        int temp = array[childIndex];

        // 无需真正的交换,单向赋值即可
        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }

        array[childIndex] = temp;
    }

    /**
     * 下沉调整
     *
     * @param array       待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    private static void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;

        while (childIndex < length) {
            // 如果有右孩子并且右孩子的值小于左孩子,则定位到右孩子
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }

            // 如果父节点小于任何一个孩子的值则直接跳出
            if (temp <= array[childIndex]) {
                break;
            }

            //赋值
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }

        array[parentIndex] = temp;
    }

    private static void buildHeap(int[] array) {
        // 从最后一个非叶子节点开始,依次做下沉调整
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
    }

    private static void heapSort(int[] array) {
        // 把无序数组构建成最大堆
        buildHeap(array);
        System.out.println(Arrays.toString(array));
        // 循环删除堆顶元素,移动到集合尾部,调整堆产生新的堆顶
        for (int i = array.length - 1; i > 0; i--) {
            // 最后一个元素和第一个元素交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // 调整堆
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);
        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(array);
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
