package com.bootslee.leetcode;

public class ReachANumber754 {
    /**
     * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
     * 每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。
     * 返回到达终点需要的最小移动次数。
     * @param target
     * @return
     */
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
