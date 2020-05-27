package com.bootslee.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By BootsLee on 2020/5/27
 **/
public class SubarraySumsDivisibleByK974 {
    /**
     * 974. 和可被 K 整除的子数组
     * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
     */
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> record = new HashMap<>();
        record.put(0, 1);
        int sum = 0, ans = 0;
        for (int elem: A) {
            sum += elem;
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = (sum % K + K) % K;
            int same = record.getOrDefault(modulus, 0);
            ans += same;
            record.put(modulus, same + 1);
        }
        return ans;
    }
    public int subarraysDivByK2(int[] A, int K) {
        int result = 0;
        //计算前缀和
        int[] arr = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (i == 0) {
                arr[i] = A[i];
            } else {
                arr[i] = A[i] + arr[i - 1];
            }
        }
        //同余定理，取所有前缀和与k的余数
        //(P[j] - P[i-1])%K == 0，根据 同余定理 ，只要 P[j]%K == P[i-1]%K，就可以保证上面的式子成立。
        //要考虑取模为负值的情况
        int[] yu = new int[K];
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i] % K;
            if (a < 0) a = K + a;
            yu[a]++;
        }

        //先处理不是0的情况
        for (int i = 0; i < K; i++) {
            if (yu[i] > 1) {
                int a = yu[i];
                for (int n = 1; n < a; n++) {
                    result = result + n;
                }
            }
        }
        //0的情况需要加上
        if (yu[0] != 0) {
            result = result + yu[0];
        }

        return result;
    }
}
