package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By BootsLee on 2020/6/1
 **/
public class KidsWithTheGreatestNumberOfCandies1431 {
    /**
     * 1431. 拥有最多糖果的孩子
     * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
     * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。
     * 注意，允许有多个孩子同时拥有 最多 的糖果数目。
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> ret=new ArrayList<>(candies.length);
        int max=Integer.MIN_VALUE;
        for (int num:candies){
            if(num>max){
                max=num;
            }
        }
        for (int num:candies){
            if(extraCandies+num>=max){
                ret.add(Boolean.TRUE);
            }else {
                ret.add(Boolean.FALSE);
            }
        }
        return ret;
    }
}
