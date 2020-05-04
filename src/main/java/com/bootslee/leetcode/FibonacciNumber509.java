package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/3
 **/
public class FibonacciNumber509 {
    /**
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 给定 N，计算 F(N)。
     */
    public int fib(int N) {
        int[] nums=new int[N+1];
        nums[0]=0;nums[1]=1;
        if (N <= 1) {
            return N;
        }
        if (N == 2) {
            return 1;
        }

        int current = 0;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 3; i <= N; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
    public int fib2(int N) {
        int[] nums=new int[N+1];
        nums[0]=0;nums[1]=1;
        for (int i = 2; i <= N; i++) {
            nums[i]=nums[i-1]+nums[i-2];
        }
        return nums[N];
    }
}
