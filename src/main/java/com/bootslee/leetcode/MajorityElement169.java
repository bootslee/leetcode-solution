package com.bootslee.leetcode;

public class MajorityElement169 {
    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count=0;
        Integer res = null;
        for(int num:nums){
            // 随便找一个元素
            if (count == 0) {
                res = num;
            }
            //遇到相同的+1，不同的-1。
            count += (num == res) ? 1 : -1;
        }
        return res;
    }
}
