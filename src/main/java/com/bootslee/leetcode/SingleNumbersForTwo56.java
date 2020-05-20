package com.bootslee.leetcode;

import java.util.Arrays;

public class SingleNumbersForTwo56 {
    /**
     * 面试题56 - I. 数组中数字出现的次数
     * 260. 只出现一次的数字 III
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
     * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     * @param nums
     * @return
     */
    public static int[] singleNumbers(int[] nums) {
        int xor=0;
        //如果是只有一个只出现一次的数字，异或后为结果
        for(int num:nums){
            xor^=num;
        }
        //获取高位bit为1，用于分组
        int mark=xor & -xor;
        int a=0;
        for (int num:nums){
            //将所有的数字根据mark分成两组。
            //可以知道相同的数字肯定在同一组中，而两个不同的数字则被分在不同的数组中了
            if((num & mark)!=0){
                a^=num;
            }
        }
        //充分利用 上一次的xor值，a^b=xor 则 b=xor^a 异或法则
        return new int[]{a,xor^a};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(singleNumbers(new int[]{1,2,5,2})));
    }
}
