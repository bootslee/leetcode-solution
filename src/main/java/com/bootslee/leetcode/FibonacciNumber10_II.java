package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/3
 **/
public class FibonacciNumber10_II {
    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。
     * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     */
    public int numWays(int n) {
        int a = 1, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
