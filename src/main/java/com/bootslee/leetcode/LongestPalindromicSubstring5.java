package com.bootslee.leetcode;

public class LongestPalindromicSubstring5 {
    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s.length()==1||s.length()==0){
            return s;
        }
        char[] chars=s.toCharArray();
        int start=0;
        int max=0;
        for (int i=0;i<chars.length;i++) {
            int f=i,l=i;
            int len = cal(chars,i,i);//奇数回文
            int len2 = cal(chars,i,i+1);//偶数回文
            len=Math.max(len,len2);
            if(max<len){
                max=len;
                // 偶数回文，这个地方需要减1才能找到正确的位置,如 aab 的起始位置会被计算为-1
                // 奇数回文 : (int)(len-1)/2=(int)(len/2)
                start=i-(len-1)/2;
            }
        }
        return String.valueOf(chars,start,max);
    }
    private int cal(char[] chars,int left,int right){
        while (left>-1 && right<chars.length&&chars[left] == chars[right]) {
            right++;
            left--;
        }
        return right-left-1;
    }

    public static String preProcess(String s) {
        int n = s.length();
        if (n == 0) {
            return "^$";
        }
        String ret = "^";
        for (int i = 0; i < n; i++)
            ret += "#" + s.charAt(i);
        ret += "#$";
        return ret;
    }

    // 马拉车算法
    public static String longestPalindrome2(String s) {
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            int i_mirror = 2 * C - i;
            if (R > i) {
                P[i] = Math.min(R - i, P[i_mirror]);// 防止超出 R
            } else {
                P[i] = 0;// 等于 R 的情况
            }
            // 碰到之前讲的三种情况时候，需要利用中心扩展法
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }
            System.out.println("i="+i+",i_m="+i_mirror+",C="+C+",R"+R+",P["+i+"]="+P[i]);
            // 判断是否需要更新 R
            if (i + P[i] > R) {
                C = i; R = i + P[i];
            }
        }

        // 找出 P 的最大值
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2; //最开始讲的求原字符串下标
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        System.out.printf(longestPalindrome2("cbcbcbde"));
    }
}
