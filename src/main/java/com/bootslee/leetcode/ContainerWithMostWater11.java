package com.bootslee.leetcode;

public class ContainerWithMostWater11 {
    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            result = Math.max(result, area);
            if (height[left] <= height[right]) {
                ++left;
            } else {
                --right;
            }
        }
        return result;
    }
}
