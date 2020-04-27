package com.bootslee.leetcode;

public class ReorganizeString767 {
    /**
     * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
     * 若可行，输出任意可行的结果。若不可行，返回空字符串。
     */
    public String reorganizeString(String S) {
        int N = S.length();
        int[] counts = new int[26];
        for (char c : S.toCharArray()) counts[c - 'a'] += 1;

        int index = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[index]) {
                index = i;
            }
        }
        int maxNum = counts[index];
        if (maxNum > (N + 1) / 2) return "";
        char[] results = new char[N];
        int evenIndex = 0;
        while (counts[index] > 0) {
            results[evenIndex] = (char) (index + 'a');
            evenIndex += 2;
            counts[index]--;
        }
        int oddIndex = 1;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                if (evenIndex < results.length) {
                    results[evenIndex] = (char) (i + 'a');
                    evenIndex += 2;
                }else {
                    results[oddIndex] = (char) (i + 'a');
                    oddIndex += 2;
                }
                counts[i]--;
            }
        }
        return String.valueOf(results);
    }
}
