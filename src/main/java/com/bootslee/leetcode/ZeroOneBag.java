package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/3
 **/
public class ZeroOneBag {
    /**
     * 0-1背包问题
     * weights数组和values等长，
     * values为物品的价值数组 weights为物品的重量
     */
    public static int getGreatestValue(int[] weights,int[] values,int N){
        int[][] dp=new int[weights.length+1][N+1];
        for(int i = 1; i <= weights.length; i++) {
            for(int j = 0; j <= N ; j++) {
                //如果容量为j的背包放得下第i个物体
                if(j >= weights[i-1]) {
                    dp[i][j] = Math.max(dp[i - 1][j - weights[i-1]] + values[i-1], dp[i - 1][j]);
                }else {
                    //放不下，只能选择不放第i个物体
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[weights.length][N];
    }

    public static void main(String[] args) {
        System.out.println(getGreatestValue(new int[]{2,2,6,5,4},new int[]{6,3,5,4,6},10));
    }
}
