package com.bootslee.leetcode;

import java.util.Arrays;

public class SingleNumbersForThree56 {
    /**
     * 面试题56 - II. 数组中数字出现的次数 II
     * 在一个数组 nums 中除一个数字只出现一次之外，
     * 其他数字都出现了三次。请找出那个只出现一次的数字。
     * @param nums
     * @return
     */
    public static int singleNumbers(int[] nums) {
        //异或运算：x ^ 0 = x​ ， x ^ 1 = ~x
        //与运算：x & 0 = 0 ， x & 1 = x
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;

    }

    public static void main(String[] args) {
        System.out.println(singleNumbers(new int[]{1,2,5,2}));
    }
}
