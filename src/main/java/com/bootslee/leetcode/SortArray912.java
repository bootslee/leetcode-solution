package com.bootslee.leetcode;

import java.util.Arrays;

public class SortArray912 {
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
    public int[] sortArray2(int[] nums) {
        return Arrays.stream(nums).parallel().sorted().toArray();
    }
}
