package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created By BootsLee on 2020/5/28
 **/
public class FourSum18 {
    /**
     * 18. 四数之和
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
     * 判断 nums 中是否存在四个元素 a，b，c 和 d ，
     * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     * 注意：
     * 答案中不可以包含重复的四元组。
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        int n = nums.length;
        if(nums==null||n<4){
            return result;
        }
        Arrays.sort(nums);
        for(int a = 0; a<n-3; a++){
            if(nums[a]+nums[a+1]+nums[a+2]+nums[a+3] > target)break;
            if(nums[a]+nums[n-1]+nums[n-2]+nums[n-3] < target)continue;
            if(a > 0 && nums[a] == nums[a-1])continue;

            int new_tar = target - nums[a];
            for(int b = a+1; b < n - 2 ;b++){
                if (nums[b]+nums[b+1]+nums[b+2] > new_tar)continue;
                if (nums[b]+nums[n-1]+nums[n-2] < new_tar)continue;
                if(b > a+1 && nums[b] == nums[b-1])continue;
                int c = b + 1,d = n - 1;
                while(c < d){
                    int sum = nums[b] + nums[c] + nums[d];
                    if(sum == new_tar){
                        result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while(c < d && nums[c] == nums[c+1]) c++;
                        while(d > c && nums[d] == nums[d-1]) d--;
                        c++;
                        d--;
                    }else if(sum < new_tar){
                        c++;
                    }else{
                        d--;
                    }
                }
            }
        }
        return result;
    }
}
