package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.List;

public class NumsSameConsecDiff967 {
    /**
     * 返回所有长度为 N 且满足其每两个连续位上的数字之间的差的绝对值为 K 的非负整数。
     * 请注意，除了数字 0 本身之外，答案中的每个数字都不能有前导零。
     * 例如，01 因为有一个前导零，所以是无效的；但 0 是有效的。
     * 你可以按任何顺序返回答案。
     * @param N
     * @param K
     * @return
     */
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> list= new ArrayList<>();
        for (int i = 1; i <= 9; ++i)
            list.add(i);
        if (N == 1)
            list.add(0);
        for (int j=1;j<N;j++) {
            List<Integer> list2 = new ArrayList<>();
            for(int num:list){
                int d = num % 10;
                if (d-K >  -1)
                    list2.add(10*num + (d-K));
                if (d+K < 10)
                    list2.add(10*num + (d+K));
            }
            list=list2;
        }

        int[] ans = new int[list.size()];
        int t = 0;
        for (int x: list)
            ans[t++] = x;
        return ans;
    }
}
