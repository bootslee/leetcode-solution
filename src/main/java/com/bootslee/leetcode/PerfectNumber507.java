package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/11
 **/
public class PerfectNumber507 {
    /**
     * 欧几里得-欧拉定理告诉我们，每个偶完全数都可以写成 (2^p-1)*2^(p-1)的形式，其中 p为素数。
     * @param p
     * @return
     */
    public int pn(int p) {
        return (1 << (p - 1)) * ((1 << p) - 1);
    }

    /**
     * 507. 完美数
     *
     * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        int[] primes=new int[]{2,3,5,7,13,17,19,31};
        for (int prime: primes) {
            if (pn(prime) == num)
                return true;
        }
        return false;
    }
}
