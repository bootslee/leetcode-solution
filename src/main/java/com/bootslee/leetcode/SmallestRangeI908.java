package com.bootslee.leetcode;

import java.util.Arrays;

public class SmallestRangeI908 {
    /**
     * 908. 最小差值 I
     * 给你一个整数数组 A，对于每个整数 A[i]，我们可以选择处于区间 [-K, K] 中的任意数 x ，将 x 与 A[i] 相加，结果存入 A[i] 。
     * 在此过程之后，我们得到一些数组 B。
     * 返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
     */
    public int smallestRangeI(int[] A, int K) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int a:A){
            if(min>a){
                min = a;
            }
            if(max<a){
                max = a;
            }
        }
        return Math.max(0, max - min - 2*K);
    }
}
