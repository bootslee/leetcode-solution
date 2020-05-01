package com.bootslee.leetcode;

public class MedianOfTwoSortedArrays4 {
    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) { // 确保 m<=n
            int[] temp = nums1; nums1 = nums2; nums2 = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) >>1;
        while (iMin <= iMax) {
            int i = (iMin + iMax) >>1;
            int j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                // i 需要增大
                iMin = i + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                // i 需要减小
                iMax = i - 1;
            } else { // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft;
                //A分成的leftA(空集) 和 rightA(A的全部)  所以leftPart = leftA(空集) + leftB,故maxLeft = B[j-1]。
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                //B分成的leftB(空集) 和 rightB(B的全部)  所以leftPart = leftA + leftB(空集),故maxLeft = A[i-1]。
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                //排除上述两种特殊情况，正常比较
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                //奇数，中位数正好是maxLeft
                if (((m + n) & 1) == 1) {
                    return maxLeft;
                }
                //偶数
                int minRight;
                //A分成的leftA(A的全部) 和 rightA(空集)  所以rightPart = rightA(空集) + rightB,故minRight = B[j]。
                if (i == m) {
                    minRight = nums2[j];
                //B分成的leftB(B的全部) 和 rightB(空集)  所以rightPart = rightA + rightB(空集),故minRight = A[i]。
                } else if (j == n) {
                    minRight = nums1[i];
                //排除上述两种特殊情况，正常比较
                } else {
                    minRight = Math.min(nums2[j],nums1[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
