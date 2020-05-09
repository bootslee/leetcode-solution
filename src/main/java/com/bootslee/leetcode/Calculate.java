package com.bootslee.leetcode;

import java.util.LinkedList;

public class Calculate {
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
        if(b!=0) {
            ans = multiply4(a << 1, b >> 1);
        }
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
     * 50. Pow(x, n)
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
     */
    public double myPow(double x, int n) {
        return  poww(x,n);
    }
    private double poww(double x,long n){
        double ans = 1, base = x;
        long nn=n>0?n:-n;
        while (nn > 0) {
            if ((nn & 1) != 0) {
                ans *= base;
            }
            base *= base;
            nn >>= 1;
        }
        return n>0?ans:1/ans;
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
        // 当进位为 0 时跳出
        while(b != 0) {
            // c = 进位
            int c = (a & b) << 1;
            // a = 非进位和
            a ^= b;
            // b = 进位
            b = c;
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
        if (d1 < d2) {
            return 0;
        }
        // d = 1000000000000000000000000000000
        long d = 0x40_00_00_00_00L, c = 0L, res = 0L;
        while (d != 0) {
            //（从前往后获取当前的二进制）
            c = (c << 1) | ((d & d1) == 0 ? 0 : 1);
            if (c >= d2) {
                // 补1
                res = (res << 1) | 1;
                c -= d2;
            } else {
                // 补0
                res = res << 1;
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
    public double sqrt2(int a) {
        double x1 = 1, x2;
        // 牛顿迭代公式
        x2 = x1 / 2.0 + a / (2 * x1);
        while (Math.abs(x2 - x1) > 1e-4) {
            x1 = x2;
            x2 = x1 / 2.0 + a / (2 * x1);
        }
        return x2;
    }
}
