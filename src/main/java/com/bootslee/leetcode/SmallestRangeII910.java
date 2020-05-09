package com.bootslee.leetcode;

import java.util.Arrays;

public class SmallestRangeII910 {
    /**
     * 910. 最小差值 II
     * 给定一个整数数组 A，对于每个整数 A[i]，我们可以选择 x = -K 或是 x = K，并将 x 加到 A[i] 中。
     * 在此过程之后，我们得到一些数组 B。
     * 返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
     */
    public int smallestRangeII(int[] A, int K) {

        Arrays.sort(A);
        int length = A.length;
        int result = A[length - 1] - A[0];

        for (int i = 1; i < length; i++) {
            int max = Math.max(A[i - 1] + K, A[length - 1] - K);
            int min = Math.min(A[0] + K, A[i] - K);
            result = Math.min(result, max - min);
        }
        return result;
    }
}
