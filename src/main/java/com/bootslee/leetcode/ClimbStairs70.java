package com.bootslee.leetcode;

public class ClimbStairs70 {
    /**
     * 动态规划算法。
     * 分析可知
     * 第 i 阶可以由以下两种方法得到：
     *  在第 (i−1) 阶后向上爬1阶。
     * 在第 (i−2) 阶后向上爬 2 阶。
     * 即 dp[i]=dp[i−1]+dp[i−2]
     */
    public int climbStairsByDp(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    /**
     * 通过动态规划算法可以分析得到公式为 斐波那契数列
     *  分析可知 Fib(1)=1 且 Fib(2)=2
     * @param n
     * @return
     */
    public int climbStairsByFib(int n) {
        int a=0,b=1;
        for(int i=1;i<=n;i++){
            int sum=a+b;
            a=b;b=sum;
        }
        return b;
    }
    /**
     *  可以使用矩阵乘法来得到第 n 个斐波那契数
     */
    public int climbStairsBymutiply(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }
    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    /**
     * 斐波那契公式 求解
     * F(n)=1/(5^(1/2)){((1+5^(1/2))/2)^n-((1-5^(1/2))/2)^n }
     *
     * 推到过程
     * a^(n+2)=a^(n+1)+a^(n)
     * 得到a^2-a-1=0
     *
     * @return
     */
    public int climbStairs(int n) {
        double sqrt5=Math.sqrt(5);
        double fibn=Math.pow((1+sqrt5)/2,n+1)-Math.pow((1-sqrt5)/2,n+1);
        return (int)(fibn/sqrt5);
    }

}
