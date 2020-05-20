package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/19
 **/
public class ValidPalindromeII680 {
    public boolean validPalindrome(String s){
        int low=0,high=s.length()-1;
        while (low<high){
            char c1=s.charAt(low);
            char c2=s.charAt(high);
            if (c1==c2){
                low++;
                high--;
            }else {
                boolean falg1=true,falg2=true;
                for (int i=low,j=high-1;i<j;i++,j--){
                    char c3=s.charAt(i),c4=s.charAt(j);
                    if (c3!=c4){
                        falg1=false;
                        break;
                    }
                }
                for (int i=low+1,j=high;i<j;i++,j--){
                    char c3=s.charAt(i),c4=s.charAt(j);
                    if (c3!=c4){
                        falg2=false;
                        break;
                    }
                }
                return falg1||falg2;
            }
        }
        return true;
    }
}
