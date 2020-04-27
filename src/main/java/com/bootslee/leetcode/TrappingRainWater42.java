package com.bootslee.leetcode;

public class TrappingRainWater42 {
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * @param height
     * @return
     */
    public int trap(int[] height) {
        //从左到右两边开始遍历，在遍历左边的时候，只要右边的高度大于当前高度说明该格子可以存水
        int left=0,right=height.length-1,maxLeft=0,maxRight=0;
        int counts=0;
        while (left<right){
            if(height[left] < height[right]){
                //右边的高度大于左边高度，说明这个格子可能能存水
                if (height[left] >= maxLeft) {//这个格子能存水的第二个条件，要小于最大左边的高度。
                    maxLeft = height[left];
                } else {
                    counts += (maxLeft - height[left]);
                }
                left++;
            }else {
                //同理
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    counts += (maxRight - height[right]);
                }
                --right;
            }

        }
        return counts;
    }
}
