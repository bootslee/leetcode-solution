package com.bootslee.leetcode;

public class CountSquareSubmatricesWithAllOnes1277 {
    /**
     * 1277. 统计全为 1 的正方形子矩阵
     * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int ans = 0;
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || j == 0) {
                    // dp[i][j] = matrix[i][j]; 这样一步会变慢
                    if (matrix[i][j] == 1) {
                        dp[i][j] = 1;
                    }
                }else if (matrix[i][j] == 1){
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                ans +=  dp[i][j];
            }
        }
        return ans;
    }

    /**
     * 思考题 方法2为什么更快
     * 我猜测应该是循环内部指令极少，每次指令都有缓存。所有加速了循环的执行。
     * 而方式1的循环内的指令较多，无法完全缓存，
     * 所以每次都需要加载指令，降低了循环的效率
     * 部分指令 本身只需要执行 m+n次，在方式一中却需要执行 m*n次
     */
    public int countSquares2(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        int results = 0;
        for (int i = 0; i < rows; i++) {
            // dp[i][0] = matrix[i][0]; 这样会变慢
            if (matrix[i][0] == 1) {
                dp[i][0] = 1;
                results++;
            }
        }
        // 去掉matrix[0][0]
        for (int j = 1; j < columns; j++) {
            // dp[0][j]  = matrix[0][j; 这样会变慢
            if (matrix[0][j] == 1) {
                dp[0][j] = 1;
                results++;
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][j] == 1) {
                    continue;
                }
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                results += dp[i][j];
            }
        }

        return results;
    }
}
