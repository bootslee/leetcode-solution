package com.bootslee.leetcode;

public class MaxUncrossedLines1035 {
    /**
     * LCS 相同
     * 我们在两条独立的水平线上按给定的顺序写下 A 和 B 中的整数。
     * 现在，我们可以绘制一些连接两个数字 A[i] 和 B[j] 的直线，
     * 只要 A[i] == B[j]，且我们绘制的直线不与任何其他连线（非水平线）相交。
     * 以这种方法绘制线条，并返回我们可以绘制的最大连线数。
     * @param A
     * @param B
     * @return
     */
    public int maxUncrossedLines(int[] A, int[] B) {
        int n1 = A.length;
        int n2 = B.length;
        int[][] dp = new int[n1+1][n2+1];
        for(int i=0;i<n1;i++){
            for(int j=0;j<n2;j++){
                if(A[i]==B[j]){
                    dp[i+1][j+1] = dp[i][j]+1;
                }else{
                    dp[i+1][j+1] = Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        return dp[n1][n2];
    }
}
