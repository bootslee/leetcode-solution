package com.bootslee.leetcode;

public class JumpGame55 {
    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个位置。
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int maxStep = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxStep) return false;
            maxStep = Math.max(maxStep, i + nums[i]);
        }
        return true;
    }

    boolean canJump2(int[] nums) {
        int len = nums.length;
        int zero_flag = 0;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                zero_flag++;
                continue;
            } else {
                if (nums[i] > zero_flag) {
                    zero_flag = 0;
                } else {
                    zero_flag++;
                }
                continue;
            }
        }
        if (zero_flag == 0) return true;
        return false;
    }

}
