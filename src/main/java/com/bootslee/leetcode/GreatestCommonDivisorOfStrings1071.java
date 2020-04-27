package com.bootslee.leetcode;

public class GreatestCommonDivisorOfStrings1071 {
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
