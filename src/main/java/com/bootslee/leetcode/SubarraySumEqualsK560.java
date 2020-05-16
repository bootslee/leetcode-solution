package com.bootslee.leetcode;

import java.util.HashMap;

public class SubarraySumEqualsK560 {
    /**
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     */
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 只为计算所需要的空间大小，注意数组可以为负数
        for (int n : nums) {
            sum += n;
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        }
        int[] sums = new int[max - min + 1];
        int count = 0;
        sum = 0;
        //sum 为累计和，如果sum[i]-sum[j]=k 则说明[i,j]满足条件
        for (int n : nums) {
            sum += n;
            int target = sum - min - k;
            //判断目标值 是不是在数组内，如果在数组内意味着直接叠加结果
            if (target >= 0 && target < sums.length) {
                count += sums[target];
            }
            sums[sum - min]++;
        }
        if (k - min >= 0 && k - min < sums.length) {
            count += sums[k - min];
        }
        return count;
    }


    public int subarraySum2(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap< Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //sum 为累计和，如果sum[i]-sum[j]=k 则说明[i,j]满足条件
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
