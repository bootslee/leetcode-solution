package com.bootslee.leetcode;

public class JumpGameV1340 {
    /**
     * 给你一个整数数组 arr 和一个整数 d 。每一步你可以从下标 i 跳到：
     * i + x ，其中 i + x < arr.length 且 0 < x <= d 。
     * i - x ，其中 i - x >= 0 且 0 < x <= d 。
     * 除此以外，你从下标 i 跳到下标 j 需要满足：arr[i] > arr[j] 且 arr[i] > arr[k] ，其中下标 k 是所有 i 到 j 之间的数字（更正式的，min(i, j) < k < max(i, j)）。
     * 你可以选择数组的任意下标开始跳跃。请你返回你 最多 可以访问多少个下标。
     * 请注意，任何时刻你都不能跳到数组的外面。
     */
    int[] arr;
    int n; //数组长度
    int d;
    int[] dp;   //用来存储每个柱子的最大结果
    public int maxJumps(int[] arr, int d) {
        this.arr = arr;
        this.n = arr.length;
        this.d = d;
        dp = new int[n];
        int ans = 0;
        for(int i=0; i<n; i++){
            ans = Math.max(ans, getMaxFromOnePoint(i));
        }
        return ans;
    }

    private int getMaxFromOnePoint(int index){
        if(dp[index] != 0)  return dp[index];   //当前柱子已经计算过，直接返回它的值
        // 如果没有，分别计算它往左和往右跳一次可以得到的最大值
        int leftMax = 0;
        int left = 1;  // 往左跳的距离
        while(index-left>=0 && left<=d){
            if(arr[index-left]>=arr[index]){   //遇到了高柱子挡路，只能结束
                break;
            } else{
                if(dp[index-left]==0)  dp[index-left] = getMaxFromOnePoint(index-left);
                leftMax = Math.max(leftMax, dp[index-left]);
                left++;
            }
        }
        // 同理右边
        int rightMax = 0;
        int right = 1;
        while(index+right<n && right<=d){
            if(arr[index+right]>=arr[index]){
                break;
            } else{
                if(dp[index+right]==0)  dp[index+right] = getMaxFromOnePoint(index+right);
                rightMax = Math.max(rightMax, dp[index+right]);
                right++;
            }
        }

        return Math.max(leftMax, rightMax)+1;
    }
}
