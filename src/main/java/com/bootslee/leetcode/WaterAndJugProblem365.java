package com.bootslee.leetcode;

public class WaterAndJugProblem365 {
    /**
     * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
     * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
     * 你允许：
     *   装满任意一个水壶
     *   清空任意一个水壶
     *   从一个水壶向另外一个水壶倒水，直到装满或者倒空
     * 题解：
     *  要判断是否能给得到，其实就是要看z是否为 x ,y 的最大公约数的倍数
     *  推理过程暂忽略
     */
    public boolean canMeasureWater(int x, int y, int z) {
        if(x+y<z)return  false;
        if(x==0||y==0) return y==z||x==z;
        return z%gcd(x,y)==0;
    }

    /**
     * 九章算术中的求最大公约数的算法
     * 求最大的公约数
     * @param m
     * @param n
     * @return
     */
    private int gcd(int m,int n){
        m = m<0?-m : m;
        n = n<0?-n : n;
        if(m==0)return n;
        if(n==0)return m;
        int x=0,y=0;
        x=m>n?m:n;
        y = m+n-x;
        while(x!=y){
            x = x-y;
            if(x<y){
                x = x+y;
                y = x-y;
                x = x-y;
            }
        }
        return x;
    }

    /**
     * 辗转相除求最大公约数的算法
     * @param x
     * @param y
     * @return
     */
    private int gcd2(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}
