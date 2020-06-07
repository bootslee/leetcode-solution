package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/6/4
 **/
public class ProductOfArrayExceptSelf238 {
    /**
     * 238. 除自身以外数组的乘积
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
     * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] rets = new int[nums.length];
        int multiply = 1;
        for (int i = 0; i < nums.length; i++) {
            rets[i] = multiply;
            multiply *= nums[i];
        }
        multiply = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            rets[i] *= multiply;
            multiply *= nums[i];
        }
        return rets;
    }
}
