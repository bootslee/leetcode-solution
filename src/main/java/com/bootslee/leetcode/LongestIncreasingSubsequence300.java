package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/3
 **/
public class LongestIncreasingSubsequence300 {
    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * 示例:
     *     输入: [10,9,2,5,3,7,101,18]
     *     输出: 4
     *     解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
