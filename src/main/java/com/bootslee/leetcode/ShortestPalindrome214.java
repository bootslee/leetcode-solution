package com.bootslee.leetcode;

public class ShortestPalindrome214 {
    public static String shortestPalindrome(String s) {
        if(s.length()==1||s.length()==0){
            return s;
        }
        char[] chars=s.toCharArray();

        int start=0;int end=chars.length-1;
        int index=chars.length-1;
        int maxlength=0;
        boolean isHead=true;
        while (start<end){
            if(chars[start]==chars[end]){
                start++;
                end--;
            }else {
                start=0;
                end=--index;
            }
        }
        maxlength=index+1;
        char[] newChars=new char[chars.length-maxlength];
        index=0;
        for (int i=chars.length-1;i>maxlength-1;i--){
            newChars[index]=chars[i];
            index++;
        }
        return String.valueOf(newChars)+String.valueOf(chars);
    }

    public static void main(String[] args) {
        String s="aacecaaa";
        System.out.println(shortestPalindrome2(s));
    }
    public static String shortestPalindrome2(String s) {
        String reverses= new StringBuilder(s).reverse().toString();
        String ss = s + '#' + reverses;
        int max = getLastNext(ss);
        System.out.println("max="+max);
        return reverses.substring(0,s.length()-max) + s;
    }
    public static int getLastNext(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int[] next = new int[n + 1];
        next[0] = -1;
        next[1] = 0;
        int k = 0;
        int i = 2;
        while (i <= n) {
            System.out.println("i="+i+",k="+k);
            System.out.print("[");
            for (int x=0;x<next.length;x++){
                System.out.print(next[x]+",");
            }
            System.out.println("]");
            if (k == -1 || c[i - 1] == c[k]) {
                next[i] = k + 1;
                k++;
                i++;
            } else {
                k = next[k];
            }
        }
        System.out.print("[");
        for (int x=0;x<next.length;x++){
            System.out.print(next[x]+",");
        }
        System.out.println("]");
        return next[n];
    }


}
