package com.bootslee.leetcode;

import java.util.HashSet;

public class JumpGameIII1306 {
    HashSet<Integer> set = new HashSet<>() ;
    /**
     * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
     * 请你判断自己是否能够跳到对应元素值为 0 的 任意 下标处。
     * 注意，不管是什么情况下，你都无法跳到数组之外。
     */
    public boolean canReach(int[] arr, int start) {
        if(start<0 ||start >= arr.length)return false;
        if(arr[start]==0)return true;
        if(!set.add(start)) return false ;
        return canReach(arr,arr[start]+start)|| canReach(arr,start-arr[start]);
    }
}
