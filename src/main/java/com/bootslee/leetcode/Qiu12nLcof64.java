package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/3
 **/
public class Qiu12nLcof64 {
    public int sumNums(int n) {
        /**
         * 负数在参与位运算时使用的是补码
         * -1的原码是   10000000 00000000 00000000 00000001
         * -1的反码是   11111111 11111111 11111111 11111110
         * -1的补码是   11111111 11111111 11111111 11111111
         * 因此任何数与-1做与运算的结果任然为原数
         */
        /**
         * 由等差数列求和公式可知，结果等于n*(n+1)/2，其中除以2可以通过右移1位进行操作
         * 但n*(n+1)在不允许使用乘法的情况下，只能把n或n+1其中一个拆解为2的n次幂数之和，
         * 配合另一个来进行位运算和累加
         * 此代码利用了-1和任何整数进行与运算还等于原数的特点
         * -((n >> 0) & 1) 求从低到高第i+1位如果为0取 0，如果为1取-1
         * 0和任意数相与为0, -1和任意数相与为 原数
         */

        return (
                (((n + 1) & (-((n >> 13) & 1))) << 13) +
                        (((n + 1) & (-((n >> 12) & 1))) << 12) +
                        (((n + 1) & (-((n >> 11) & 1))) << 11) +
                        (((n + 1) & (-((n >> 10) & 1))) << 10) +
                        (((n + 1) & (-((n >> 9) & 1))) << 9) +
                        (((n + 1) & (-((n >> 8) & 1))) << 8) +
                        (((n + 1) & (-((n >> 7) & 1))) << 7) +
                        (((n + 1) & (-((n >> 6) & 1))) << 6) +
                        (((n + 1) & (-((n >> 5) & 1))) << 5) +
                        (((n + 1) & (-((n >> 4) & 1))) << 4) +
                        (((n + 1) & (-((n >> 3) & 1))) << 3) +
                        (((n + 1) & (-((n >> 2) & 1))) << 2) +
                        (((n + 1) & (-((n >> 1) & 1))) << 1) +
                        ((n + 1) & (-(n & 1)))
        ) >> 1;// 除以 2
    }

    /**
     * 如何不用乘法求 一个a*b的数
     * 实际上 a*b=a+a+……+a 循环b次
     * 复杂度为O(b)
     */
    public static int multiply(int a, int b) {
        int ans = 0;
        while (b > 0) {
            ans = ans + a;
            b--;
        }
        return ans;
    }

    /**
     * 优化的两数乘积的算法
     * 我们可以把b表示为2进制,
     * 假设 b 为 7 ，可以表示为 0111
     * 就可以把 连加的公式表示为  a+2a+4a。
     * 翻过来说，我们可以将a通过左位移1，使其编程 2a，4a，8a。
     * 而将b右位移一位，然后将 b和1 相与，判断最低位是否为1，可以知道是否相加
     * 如，假设b 为 1001=9 ，第一位 ans=a，第二位，a
     *
     * @param a
     * @param b
     * @return
     */
    public static int multiply2(int a, int b) {
        int ans = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                ans = ans + a;
            }
            // 在快速幂中这里应该是a = a * a, 快速加应改成a = a + a
            a <<= 1;
            // 用位运算优化了两数相加的操作
            b >>= 1;
        }
        return ans;
    }

    //不用while的快速相乘
    public static int multiply3(int a, int b) {
        int ans = 0;
        boolean flag = (b > 0 && (ans = multiply3(a << 1, b >> 1)) > 0);
        return ans+(a & (-(b & 1)));
    }
    public static int multiply4(int a, int b) {
        int ans = 0;
        if(b!=0)
            ans = multiply4(a << 1, b >> 1);
        return ans+(a & (-(b & 1)));
    }

    public static int poww(int a, int b) {
        int ans = 1, base = a;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans *= base;
            }
            base *= base;
            b >>= 1;
        }
        return ans;
    }

    /**
     * 面试题 17.01. 不用加号的加法
     * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
     * 面试题65. 不用加减乘除做加法
     * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        /*
        1）分析上面对二进制的计算过程，不难发现：
        1.计算不进位的和，相当于对两个数进制异或：1101^1001=0100；
        2.计算进位，第1位相当于对两个数求与：1101&1001=1001，然后再对其进行左移1位：1001<<1=10010。
        然后再重复以上两个步骤。这里再异或一次就得到结果了，没进位：0100^10010=10110=22。

        2）计算a+b，等价于(a^b)+((a&b)<<1)。
        由于公式中又出现了+号，因此要再重复2）这个等价的计算过程。
        结束条件是：没有进位了。
         */
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }

    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        boolean s = (dividend ^ divisor) >= 0;
        long d1 = (long) dividend;
        long d2 = (long) divisor;
        d1 = d1 > 0 ? d1 : -d1;
        d2 = d2 > 0 ? d2 : -d2;
        if (d1 < d2)
            return 0;
        // d = 1000000000000000000000000000000
        long d = 0x40_00_00_00_00L, c = 0L, res = 0L;
        while (d != 0) {
            c = (c << 1) | ((d & d1) == 0 ? 0 : 1);//（从前往后获取当前的二进制）
            if (c >= d2) {
                res = (res << 1) | 1;//补1
                c -= d2;
            } else {
                res = res << 1;//补0
            }
            d = d >> 1;
        }
        // 判断边界
        return s ? res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)res : (int)-res;
    }

    public int minus(int a, int b) {
        while (b != 0) {
            int p = a & b;
            a ^= p;
            b ^= p;
            a |= b;
            b <<= 1;
        }
        return a;
    }
        /**
         * 面试题 16.09. 运算
         * 请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，
         * 程序中只允许使用加法运算符和逻辑运算符，允许程序中出现正负常数，不允许使用位运算。
         */
    class Operations {

        public Operations() {

        }

        public int minus(int a, int b) {
            b=-b;//a-b=a+(-b);
            while(b != 0) { // 当进位为 0 时跳出
                int c = (a & b) << 1;  // c = 进位
                a ^= b; // a = 非进位和
                b = c; // b = 进位
            }
            return a;
        }

        public int multiply(int a, int b) {
            int ans = 0;
            boolean flag=a>0?(b <= 0):(b > 0);
            a=a>0?a:-a;b=b>0?b:-b;
            while (b != 0 ) {
                if ((b & 1) == 1) {
                    ans = ans + a;
                }
                // 在快速幂中这里应该是a = a * a, 快速加应改成a = a + a
                a <<= 1;
                // 用位运算优化了两数相加的操作
                b >>= 1;
            }
            return flag?-ans:ans;
        }

        public int divide(int dividend, int divisor) {
            boolean s = (dividend ^ divisor) >= 0;
            long d1 = (long) dividend;
            long d2 = (long) divisor;
            d1 = d1 > 0 ? d1 : -d1;
            d2 = d2 > 0 ? d2 : -d2;
            if (d1 < d2)
                return 0;
            // d = 1000000000000000000000000000000
            long d = 0x40_00_00_00_00L, c = 0L, res = 0L;
            while (d != 0) {
                c = (c << 1) | ((d & d1) == 0 ? 0 : 1);//（从前往后获取当前的二进制）
                if (c >= d2) {
                    res = (res << 1) | 1;//补1
                    c  = c+(-d2);
                } else {
                    res = res << 1;//补0
                }
                d = d >> 1;
            }
            // 判断边界
            return s ? res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)res : (int)-res;
        }
    }

    public static void main(String[] args) {
        System.out.println(multiply3(3, 5));
        System.out.println(4 + (4 << 1) + (4 << 2));
    }
}
