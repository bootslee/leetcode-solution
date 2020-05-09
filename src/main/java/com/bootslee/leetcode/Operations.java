package com.bootslee.leetcode;
/**
 * 面试题 16.09. 运算
 * 请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，
 * 程序中只允许使用加法运算符和逻辑运算符，允许程序中出现正负常数，不允许使用位运算。
 * @author BootsLee
 */
public class Operations {

    public Operations() {

    }

    public int minus(int a, int b) {
        // a-b=a+(-b);
        b = -b;
        // 当进位为 0 时跳出
        while (b != 0) {
            // c = 进位
            int c = (a & b) << 1;
            // a = 非进位和
            a ^= b;
            // b = 进位
            b = c;
        }
        return a;
    }

    public int multiply(int a, int b) {
        int ans = 0;
        boolean flag = (a > 0) == (b <= 0);
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        while (b != 0) {
            if ((b & 1) == 1) {
                ans = ans + a;
            }
            // 在快速幂中这里应该是a = a * a, 快速加应改成a = a + a
            a <<= 1;
            // 用位运算优化了两数相加的操作
            b >>= 1;
        }
        return flag ? -ans : ans;
    }

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
                c = c + (-d2);
            } else {
                // 补0
                res = res << 1;
            }
            d = d >> 1;
        }
        // 判断边界
        return s ? res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res : (int) -res;
    }
}
