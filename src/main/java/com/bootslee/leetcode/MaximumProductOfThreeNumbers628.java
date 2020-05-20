package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/18
 **/
public class MaximumProductOfThreeNumbers628 {
    /**
     * 628. 三个数的最大乘积
     * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n: nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            // 找出第二小的数字
            } else if (n <= min2) {
                min2 = n;
            }
            // 找出最大的数
            if (n >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            //找出第二大的数
            } else if (n >= max2) {
                max3 = max2;
                max2 = n;
            // 找出第三大的数
            } else if (n >= max3) {
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}
