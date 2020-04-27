package com.bootslee.leetcode;

public class GreatestCommonDivisorOfStrings1071 {
    /**
     * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，
     * 我们才认定 “T 能除尽 S”。
     * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
     * 示例 1：
     * 输入：str1 = "ABCABC", str2 = "ABC"
     * 输出："ABC"
     */
    public String gcdOfStrings(String str1, String str2) {
        String x=str1.length()>str2.length()?str1:str2;
        String y =str1.length()>str2.length()?str2:str1;
        while(x.startsWith(y)&&x.length()>y.length()){
            x = x.substring(y.length());
            if(x.length()<y.length()){
                String temp=x;
                x = y;
                y = temp;
            }
        }
        if(x.startsWith(y))return  y;
        return "";
    }
}
