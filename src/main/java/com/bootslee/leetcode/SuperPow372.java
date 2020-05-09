package com.bootslee.leetcode;

import java.util.LinkedList;

public class SuperPow372 {
    /**
     *  372. 超级次方
     * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
     */
    static int mod=1337;
    public int superPow(int n,int[] nums){
        LinkedList<Integer> list = new LinkedList<>();
        for (int e:nums) {
            list.add(e);
        }
        return myPow(n,list);
    }

    private int myPow(int n, LinkedList<Integer> list) {
        if (list.size()==0) {
            return 1;
        }
        Integer last = list.removeLast();
        n=n%mod;
        int part1=quickPow(n,last);
        int part2=quickPow(myPow(n,list),10);
        return (part1*part2)%mod;
    }

    private int quickPow(int n, Integer k) {
        int res=1;
        int temp=n%mod;
        while (k>0){
            if ((k &1) ==1 ){
                res=(res*temp)%mod;
            }
            temp=(temp*temp)%mod;
            k=k>>1;
        }
        return res%mod;
    }

}
