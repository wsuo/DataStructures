package top.wsuo.algorithm;

/**
 * 判断一个数是不是2的整数次幂
 *
 * @Author shuo wang
 * @Date 2020/4/4 0004 11:39
 * @Version 1.0
 */
public class IsPowerOf2 {

    public static boolean solution1(int num) {
        int temp = 1;

        while (temp <= num) {

            if (temp == num) {
                return true;
            }

            temp *= 2;
        }

        return false;
    }

    public static boolean solution2(int num) {
        int temp = 1;

        while (temp <= num) {

            if (temp == num) {
                return true;
            }

            temp >>= 1;
        }

        return false;
    }

    /**
     * 妙用位运算
     *
     * @param num 输入的数字
     * @return 返回
     */
    public static boolean solution3(int num) {
        return ((num - 1) & num) == 0;
    }
}
