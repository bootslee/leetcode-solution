package com.bootslee.leetcode;

import java.util.HashSet;

public class SingleNumber136 {
    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，
     * 其余每个元素均出现两次。找出那个只出现了一次的元素。
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int num = 0;
        for(int i=0;i<nums.length;i++){
            num=num^nums[i];
        }
        return num;
    }

    public int singleNumber2(int[] nums) {
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                set.remove(nums[i]);// 扔掉
            }else {
                set.add(nums[i]);
            }
        }
        Integer[] ints=set.toArray(new Integer[0]);

        return ints[0];
    }
}
