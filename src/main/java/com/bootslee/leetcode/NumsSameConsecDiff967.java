package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.List;

public class NumsSameConsecDiff967 {
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
