package com.bootslee.leetcode;

public class CountNumberOfNiceSubarrays1248 {
    /**
     * 给你一个整数数组 nums 和一个整数 k。
     * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     * 请返回这个数组中「优美子数组」的数目。
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length, res = 0, feed = 0, arr[] = new int[len + 2];
        for(int i = 0; i < len; i ++) {
            // if it is odd
            if((nums[i] & 1) == 1) {
                arr[++feed] = i;
            }
        }
        // left border
        arr[0] = -1;
        // right border
        arr[feed + 1] = len;
        for(int i = 1; i + k < feed + 2; i ++) {
            res += (arr[i] - arr[i - 1]) * (arr[i + k] - arr[i + k - 1]);
        }
        return res;
    }
}
