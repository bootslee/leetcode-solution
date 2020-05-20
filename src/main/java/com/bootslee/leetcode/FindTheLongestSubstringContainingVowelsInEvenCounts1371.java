package com.bootslee.leetcode;

import java.util.Arrays;

/**
 * Created By BootsLee on 2020/5/20
 **/
public class FindTheLongestSubstringContainingVowelsInEvenCounts1371 {
    /**
     * 1371. 每个元音包含偶数次的最长子字符串
     * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：
     * 每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
     * @param s
     * @return
     */
    public int findTheLongestSubstring(String s) {
        /*
            首先题目中要求子字符串中每个元音字母恰好出现偶数次，
            我们就可以使用 0 和 1 来标识每个字母的状态(偶数次或奇数次)，
            我们不需要知道每个字母出现的完整次数，只需要知道这个次数的奇偶性
            次数统计可以用xor运算进行解决。
            每次求出一个坐标的状态码的时候就去瞅瞅这个状态码前面是否存在，
            如果存在，那么就计算一下之间子字符串的长度就 ok 了，
            还需要一个Hash表存储每个状态码对应的下标！
            当然因为我们状态码最长也就是 11111 = 2^5 - 1 = 31，
            也就是 32 大小的数组。
         */
        int n = s.length();
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return ans;
    }
}
