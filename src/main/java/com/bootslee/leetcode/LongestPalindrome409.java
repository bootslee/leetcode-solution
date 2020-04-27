package com.bootslee.leetcode;

public class LongestPalindrome409 {
    /**
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     * 注意: 假设字符串的长度不会超过 1010。
     * @param s
     * @return
     */
    public int longestPalindrome1(String s) {
        int[] counts = new int[128];
        for (int i = 0; i < s.length(); i++)
            counts[s.charAt(i)] += 1;
        int result = 0;
        boolean hasOdd =false;//标记是否存在可以为奇数回文
        for (int c : counts) {
            if(c==0)continue;
            if(c==1)hasOdd=true;
            if(c>1){
                if((c&1)==1)hasOdd=true;
                result += c / 2 * 2;
            }
        }
        if(hasOdd){
            result+=1;
        }
        return result;
    }
    public int longestPalindrome2(String s) {
        int[] counts = new int[128];
        for (int i = 0; i < s.length(); i++)
            counts[s.charAt(i)] += 1;
        int result = 0;
        for (int c : counts) {
            result += c - (c & 1);
        }
        return result<s.length()?result+1:result;
    }

    public int longestPalindrome3(String s) {
        int[] counts = new int[128];
        int result = 0;
        for (int i = 0; i < s.length(); i++){
            if(counts[s.charAt(i)]>0){
                counts[s.charAt(i)]=0;
                result+=2;
            }else {
                counts[s.charAt(i)]=1;
            }
        }
        return result<s.length()?result+1:result;
    }
}
