package com.bootslee.leetcode;

public class MaxDepthAfterSplit1111 {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ret=new  int[seq.length()];
        int depth=0;
        for (int i=0;i<seq.length();i++){
            if('('==seq.charAt(i)){
                depth++;
                ret[i]=depth&1;
            }else {
                depth--;
                ret[i]=depth&1;
            }
        }
        return  ret;
    }
}
