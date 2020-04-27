package com.bootslee.leetcode;

public class LongestCommonSubsequence1143 {
    public  int longestCommonSubsequence(String text1, String text2){
        char[] A=text1.toCharArray();
        char[] B=text2.toCharArray();
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


