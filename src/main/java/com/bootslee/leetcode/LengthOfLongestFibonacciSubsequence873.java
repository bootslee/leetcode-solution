package com.bootslee.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestFibonacciSubsequence873 {
    /**
     * 873. 最长的斐波那契子序列的长度
     * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
     *  n >= 3
     *  对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
     *  给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
     *
     * （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），
     * 而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
     * @param A
     * @return
     */
    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap();
        // 建立value 和index  对应关系
        // 记录A中每个元素的下标，方便查找
        for (int i = 0; i < N; ++i) {
            index.put(A[i], i);
        }

        // dp map
        Map<Integer, Integer> longest = new HashMap();
        int ans = 0;

        for (int k = 0; k < N; ++k) {
            for (int j = 0; j < k; ++j) {
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j) {
                    // 计算 dp 数组
                    int cand = longest.getOrDefault(i * N + j, 2) + 1;
                    longest.put(j * N + k, cand);
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }

    public int lenLongestFibSubseq2(int[] A) {
        int N = A.length;
        int res = 0;
        for(int i = 0; i < N-2; i++) {
            // int pos = i+2;
            for(int j = i+1; j < N-1; j++) {
                int pos = j+1;
                int sum = A[i]+A[j];
                while(pos < N && A[pos] < sum) {
                    pos++;
                }
                if(pos == N) {
                    return res;
                }
                if(A[pos] != sum) {
                    continue;
                }

                int b = sum, c = A[j]+b, curL = 3, k = pos+1;
                while(true) {
                    while(k < N && A[k] < c) {
                        k++;
                    }
                    if(k == N) {
                        break;
                    }
                    if(A[k] != c) {
                        break;
                    }

                    curL++;
                    c = b + c;
                    b = c - b;
                }
                res = Math.max(res, curL);
            }
        }
        return res;
    }
}
