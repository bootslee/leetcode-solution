package com.bootslee.leetcode;

public class JumpGameII45 {
    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     */
    public int jump(int[] nums) {
        int maxStep=0;
        int result=0;
        int end=0;
        for (int i=0;i<nums.length-1;i++){
            maxStep=Math.max(maxStep,i+nums[i]);
            if(i==end){
                result++;
                end=maxStep;
            }
        }
        return result;
    }
}
