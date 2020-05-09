package com.bootslee.leetcode;

public class Sqrtx69 {
    /**
     * 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     */
    public int mySqrt(int x) {
        return  (int) Math.sqrt(x);
    }
    public static double sqrt(int t, Double precise) {
        //先确定当前数所处的最小整数区间
        int i = 0;
        for ( ; i <= t; i++) {
            if (i * i == t) {
                return i;
            }
            if(i * i > t){
                break;
            }
        }
        //再通过二分法来进行判断检测
        double low = i-1, high = i, prec = precise != null ? precise : 1e-7;
        double middle, squre;
        while (high - low > prec) {
            middle = (low + high) / 2;
            squre = middle * middle;

            if (squre > t) {
                high = middle;
            } else {
                low = middle;
            }
        }
        return (low + high) / 2;
    }
    public int mySqrt2(int a) {
        double x1 = 1, x2;
        x2 = x1 / 2.0 + a / (2 * x1);//牛顿迭代公式
        while (Math.abs(x2 - x1) > 1e-4) {
            x1 = x2;
            x2 = x1 / 2.0 + a / (2 * x1);
        }
        return (int)x2;
    }

    public int mySqrt3(int a) {
        long x = a;
        while (x * x > a ) {
            x = (x + a / x) / 2;
        }
        return (int) x;
    }

}
