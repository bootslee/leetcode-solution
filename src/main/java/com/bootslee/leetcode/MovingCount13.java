package com.bootslee.leetcode;

public class MovingCount13 {
    /**
     * 机器人的运动范围
     * 等差数列的求和公式Sn=n(a1+an)/2
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        //划分区域为 row*col个格子
        int row = (m/10), col = (n/10);
        //因为是移动，barrier 设置为一个墙，如果在当前格子无法通往下一个格子则需要终止循环
        int count = 0,barrier  = m + n;
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (i + j > barrier) break;
                // border 用来代替k， 循环时相当于将格子 取值范围 -1
                int border = k-i-j;
                if (border < 9) { // 小于9时，外面其他区域被挡
                    barrier = i + j;
                }
                // 还没到达边角
                if (row != i && col != j) {
                    count += calArea(10, 10, border);
                }
                // 角区域
                else if (row == i && col == j) {
                    count += calArea(m%10, n%10, border);
                }
                // 边区域
                else if (row == i) {
                    count += calArea(m%10, 10, border);
                }
                // 边区域
                else if (col == j) {
                    count += calArea(10, n%10, border);
                }
            }
        }
        return count;
    }
    private int calArea(int m, int n, int k) {
        if (k < 0) return 0;
        // 全部
        if (k >= m+n-2) return m*n;
            // 三角形
        if (k < m && k < n) return (k+1)*(k+2)/2;
        // 分成长方形和三角形
        if (k < m && k >= n) return (k-n+1)*n + n*(n+1)/2;
        if (k < n && k >= m) return (k-m+1)*m + m*(m+1)/2;
        // 减去超过区域范围的
        int len = n + m - 2 - k;
        return m*n - len*(len+1)/2;
    }

    public static void main(String[] args) {
        new MovingCount13().movingCount(16,8,4);
    }
}
