package com.bootslee.leetcode;

public class MaximumSubarray53 {
    public int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) return nums[left];

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        //求实际上从右到左的一个最大子数组的合计
        for(int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        //求实际上从左到右的一个最大子数组的合计
        for(int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }
        // 返回 数组中间一部分子数据的大小
        return leftSubsum + rightSubsum;
    }

    public int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int p = (left + right) / 2;

        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }
    // 题目要求的 分治算法
    public int maxSubArray(int[] nums) {
        /**
         * 以数组中点为界，目标值只能在三处---要么在中点左边(不含中点)，要么必须包含中点，要么右边(不含中点)，那就可以这样分
         * -left_sum 为最大子数组前 n/2 个元素最大和，不含索引为 (left + right) / 2 的元素。
         * -right_sum 为最后 n/2 的元素最大和，不含索引为 (left + right) / 2 的元素。
         * -cross_sum 包含含索引 (left + right) / 2 的最大和。
         */
        return helper(nums, 0, nums.length - 1);
    }
    //贪心算法
    //使用单个数组作为输入来查找最大（或最小）元素（或总和）的问题
    public int maxSubArray1(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for(int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
    // 动态规划算法
    //  转移方程：dp[i] = max(dp[i - 1], 0) + nums[i]
    public int maxSubArray2(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for(int i = 1; i < n; ++i) {
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }
}
