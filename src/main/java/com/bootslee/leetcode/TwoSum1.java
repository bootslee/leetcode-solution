package com.bootslee.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {
    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     */
    public int[] twoSum(int[] nums, int target) {
        //缓存当前元素和目标值的差值，和坐标
        Map<Integer,Integer> dvalues=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(dvalues.containsKey(nums[i])){
                return new int[]{dvalues.get(nums[i]),i};
            }
            dvalues.put(target-nums[i],i);
        }
        return new int[]{-1,-1};
    }
}
