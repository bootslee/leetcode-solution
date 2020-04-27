package com.bootslee.leetcode;

import java.util.Arrays;

public class SortArray912 {
    /**
     * 给你一个整数数组 nums，请你将该数组升序排列。
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
    public int[] sortArray2(int[] nums) {
        return Arrays.stream(nums).parallel().sorted().toArray();
    }
}
