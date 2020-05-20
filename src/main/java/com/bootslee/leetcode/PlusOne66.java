package com.bootslee.leetcode;

import java.util.Arrays;

/**
 * Created By BootsLee on 2020/5/18
 **/
public class PlusOne66 {
    /**
     * 66. 加一
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int[] ret=new int[digits.length+1];
        int[] ret2=new int[digits.length];
        int b=1;ret[0]=1;
        for (int i = digits.length-1; i >=0 ; i--) {
            int  n = digits[i]+b;
            if(n>9){
                n=n%10;
                ret[i+1]=n;
                ret2[i]=ret[i+1];
                b=1;
            }else {
                b=0;
                ret[i+1]=n;
                ret2[i]=ret[i+1];
            }
        }
        return b>0?ret:ret2;
    }
}
