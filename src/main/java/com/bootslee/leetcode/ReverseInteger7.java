package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/20
 **/
public class ReverseInteger7 {
    /**
     * 7. 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
     * 请根据这个假设，如果反转后整数溢出那么就返回 0。
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        int max=Integer.MAX_VALUE/10;
        int min=Integer.MIN_VALUE/10;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > max || (rev == max && pop > 7)) return 0;
            if (rev < min || (rev == min && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
