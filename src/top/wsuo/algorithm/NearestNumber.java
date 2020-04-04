package top.wsuo.algorithm;

import java.util.Arrays;

/**
 * 字典序算法
 *
 * 寻找全排列数的下一个数字
 * 1、从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界。
 * 2、让逆序区域的前一位和逆序区域中大于它的最小的数字交换位置。
 * 3、把原来的逆序区域转为顺序状态 。
 *
 * @Author shuo wang
 * @Date 2020/4/4 0004 13:09
 * @Version 1.0
 */
public class NearestNumber {

    public static int[] findNearestNumber(int[] numbers) {
        int index = findTransferPoint(numbers);
        // 如果返回值为0,说明数列为逆序数列,没有更大的值了
        if (index == 0) {
            return null;
        }
        int[] copy = Arrays.copyOf(numbers, numbers.length);

        return reverse(exchangeHead(copy, index), index);
    }


    /**
     * 从后向前查看逆序区域，找到逆序区域的前一位
     *
     * @param numbers 输入数组
     * @return 返回数字置换的边界
     */
    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }


    private static int[] exchangeHead(int[] numbers, int index) {
        // 逆序数组的前一位,也就是要交换的数字下标
        int head = numbers[index - 1];
        // 从后往前遍历找到大于 head 的最小数字
        for (int i = numbers.length - 1; i > 0; i--) {
            // 因为是逆序的,遍历到的第一个肯定就是最小的那个
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     * 反转数组
     *
     * @param num   输入数组
     * @param index 交换元素的起点
     * @return 返回数组
     */
    private static int[] reverse(int[] num, int index) {
        for (int i = index, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
        return num;
    }

}
