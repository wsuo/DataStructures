package top.wsuo.algorithm;

/**
 * 求最大公约数
 *
 * @Author shuo wang
 * @Date 2020/4/4 0004 10:55
 * @Version 1.0
 */
public class GCD {

    /**
     * 欧几里得算法(辗转相除法)
     *
     * @param a 第一个数
     * @param b 第二个数
     * @return 循环递归
     */
    private static int gcd1(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);

        if (big % small == 0) {
            return small;
        }

        return gcd1(small, big % small);
    }

    /**
     * 更相减损术
     *
     * @param a 第一个数
     * @param b 第二个数
     * @return 递归
     */
    private static int gcd2(int a, int b) {
        if (a == b) {
            return a;
        }

        int big = Math.max(a, b);
        int small = Math.min(a, b);

        return gcd2(big - small, small);
    }

    /**
     * 移位算法,综合二者的优势
     *
     * @param a 第一个数
     * @param b 第二个数
     * @return 返回值
     */
    private static int gcd3(int a, int b) {
        if (a == b) {
            return a;
        }

        if (((a&1) == 0) && ((b&1) == 0)) {
            return gcd3(a >> 1, b >> 1) << 1;
        } else if (((a&1) != 0) && ((b&1) == 0)) {
            return gcd3(a, b >> 1);
        } else if (((a&1) == 0) && (b&1) != 0) {
            return gcd3(a >> 1, b);
        } else {
            int big = Math.max(a, b);
            int small = Math.min(a, b);
            return gcd3(big - small, small);
        }
    }
}
