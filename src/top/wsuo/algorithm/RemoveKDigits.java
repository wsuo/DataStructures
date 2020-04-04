package top.wsuo.algorithm;

/**
 * 删除K个数字后的最小值
 * 使用贪心算法,先求局部最优解然后合并成全局最优解
 *
 * @Author shuo wang
 * @Date 2020/4/4 0004 14:01
 * @Version 1.0
 */
public class RemoveKDigits {

    /**
     * 每次循环先遍历比较,找出第一个比后面的数字大的元素,然后删掉
     *
     * @param num 输入的数字
     * @param k   要删除数字的数量
     * @return 返回数字
     */
    public static String removeKDigits1(String num, int k) {
        String newNum = num;
        for (int i = 0; i < k; i++) {
            // 标记看下有没有找到符合条件的元素
            boolean hasCut = false;
            for (int j = 0; j < num.length() - 1; j++) {
                if (newNum.charAt(j) > newNum.charAt(j + 1)) {
                    newNum = num.substring(0, j) + num.substring(j + 1, newNum.length());
                    hasCut = true;
                    break;
                }
            }
            // 如果没有找到就删除最后一个
            if (!hasCut) {
                newNum = num.substring(0, newNum.length() - 1);
            }
            // 清除数字左侧的零
            newNum = removeZero(newNum);
        }

        // 如果所有的数字都被删除了,返回0
        if (newNum.length() == 0) {
            return "0";
        }

        return newNum;
    }

    /**
     * 清除数字左边的零
     *
     * @param num 输入的数字序列
     */
    private static String removeZero(String num) {
        for (int i = 0; i < num.length() - 1; i++) {
            if (num.charAt(i) != '0') {
                break;
            }
            num = num.substring(1, num.length());
        }
        return num;
    }

    /**
     * 使用栈来优化
     *
     * @param num 输入数字
     * @param k   删掉数字的数目
     * @return 返回结果
     */
    public static String removeKDigits2(String num, int k) {
        // 新整数的长度等于原整数的长度-k
        int newLength = num.length() - k;
        // 创建一个栈用于接收所有的数字
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); ++i) {
            // 遍历当前数字
            char c = num.charAt(i);
            // 当栈顶元素存在并且大于遍历到的元素时,出栈(相当于删除元素)
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top--;
                k--;
            }
            // 遍历到的数字入栈
            stack[top++] = c;
        }
        // 找到栈中的第一个非零数字的位置,以此构建新的整数字符串
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }
        return offset == newLength ?
                "0" : new String(stack, offset, newLength - offset);
    }
}
