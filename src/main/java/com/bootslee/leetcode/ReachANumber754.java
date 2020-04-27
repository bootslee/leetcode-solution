package com.bootslee.leetcode;

public class ReachANumber754 {
    public int reachNumber(int target) {
        //等差数列的求和公式Sn=n(a1+an)/2
        //一元二次方程求解公式
        // 利用求解公式快速接近目标值。
        int n =  (int)(Math.sqrt(2*Math.abs(target)));
        int sum=(n*(1+n)/2);
        //n%2==0 则和位奇数，n%2==1则和也为计数
        while (sum<target){
            n++;
            sum+=n;
        }
        if((sum-target)%2==0){
            return n;
        }
        return target%2==0? n : n + 1 + n%2;
    }
}
