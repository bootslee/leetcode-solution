package com.bootslee.leetcode;

import java.util.HashMap;

public class ContinuousSubarraySum523 {
    /**
     * 523. 连续的子数组和
     * 给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，
     * 总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
     *
     * 前缀和 preSum[i]表示 数组来表示A[0],...,A[i]这段子数组的和
     * 根据前缀和的概念，容易求出任意一段子数组的和
     * 公式 preSum[i]−preSum[j−1]==A[j]+A[j+1]+...+A[i]
     *
     * 两个不同的前缀和的余数相等，意味着这两个前缀和之差就是k的倍数 同余定理
     * (preSum[i]−preSum[j]) % k==0⟺ preSum[i] % k==preSum[j] % k
     *
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap< Integer, Integer > map = new HashMap < > ();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 只对求余结果并无影响
            if (k != 0) {
                sum = sum % k;
            }
            // 两个不同的前缀和的余数相等，意味着这两个前缀和之差就是k的倍数
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }

}
