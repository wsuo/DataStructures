package top.wsuo.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author shuo wang
 * @Date 2020/4/2 0002 11:16
 * @Version 1.0
 */
public class MySort {
    /**
     * 冒泡排序 + 两次优化
     *
     * @param array 输入数组
     */
    private static void sort01(int[] array) {
        // 记录最后一次交换元素的位置
        int lastExchangeIndex = 0;
        // 无序数组的边界
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                int tmp;
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    // 有元素交换,说明不是有序的
                    isSorted = false;
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 冒泡排序的另一种实现: 鸡尾酒排序
     *
     * @param array 输入数组
     */
    private static void sort02(int[] array) {
        int tmp = 0;
        for (int i = 0; i < array.length / 2; i++) {
            boolean isSorted = true;
            // 奇数轮: 从左往右
            for (int j = i; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false;
                }
            }

            if (isSorted) {
                break;
            }

            isSorted = true;
            // 偶数轮: 从右往左
            for (int j = array.length - i - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    isSorted = false;
                }
            }

            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 快速排序: 分治
     *
     * @param array 输入数组
     */
    private static void sort03(int[] array, int startIndex, int endIndex) {
        // 递归结束条件: startIndex >= endIndex 时
        if (startIndex >= endIndex) {
            return;
        }

        // 得到基准元素的位置
        int pivotIndex = partition(array, startIndex, endIndex);

        // 根据基准元素,分成两部分进行递归排序
        sort03(array, startIndex, pivotIndex - 1);
        sort03(array, pivotIndex + 1, endIndex);
    }

    /**
     * 双边循环法
     *
     * @param array      输入数组
     * @param startIndex 开始位置
     * @param endIndex   结束位置
     * @return 返回基准值
     */
    private static int partition(int[] array, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right) {
            // 控制 left 指针比较并右移
            while (left < right && array[left] <= pivot) {
                left++;
            }
            // 控制 right 指针比较并左移
            while (left < right && array[right] > pivot) {
                right--;
            }
            // 交换 left 和 right 指针所指向的元素
            if (left < right) {
                int p = array[left];
                array[left] = array[right];
                array[right] = p;
            }
        }

        // 交换指针的重合点 和 基准元素
        array[startIndex] = array[left];
        array[left] = pivot;

        return left;
    }

    /**
     * 单边循环法
     *
     * @param array      输入数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return 返回值
     */
    private static int partition2(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];
        int mark = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (array[i] < pivot) {
                mark++;
                int p = array[mark];
                array[mark] = array[i];
                array[i] = p;
            }
        }

        array[startIndex] = array[mark];
        array[mark] = pivot;

        return mark;
    }

    /**
     * 不使用递归实现快速排序
     *
     * @param array      输入的数组
     * @param startIndex 开始点
     * @param endIndex   结束点
     */
    private static void sort04(int[] array, int startIndex, int endIndex) {
        // 用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<>();
        // 整个数列的起始下标,以哈希的形式入栈
        Map rootParam = new HashMap();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        quickSortStack.push(rootParam);

        // 循环结束条件: 栈为空
        while (!quickSortStack.isEmpty()) {
            // 栈顶元素出栈, 得到起止下标
            Map<String, Integer> param = quickSortStack.pop();
            // 得到基准位置元素
            int pivotIndex = partition2(array, param.get("startIndex"), param.get("endIndex"));
            // 根据基准元素分成两部分,把每一部分的起止下标入栈
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if (param.get("endIndex") > pivotIndex + 1) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        sort02(array);
        System.out.println(Arrays.toString(array));
    }
}
