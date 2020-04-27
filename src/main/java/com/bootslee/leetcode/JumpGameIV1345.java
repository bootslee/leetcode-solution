package com.bootslee.leetcode;

import java.util.*;

public class JumpGameIV1345 {
    /**
     * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
     * 每一步，你可以从下标 i 跳到下标：
     * i + 1 满足：i + 1 < arr.length
     * i - 1 满足：i - 1 >= 0
     * j 满足：arr[i] == arr[j] 且 i != j
     * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
     * 注意：任何时候你都不能跳到数组外面。
     */
    public int minJumps(int[] arr) {
        if (arr.length == 1)
            return 0;
        Map<Integer, Set<Integer>> value2Index = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {//存储相同值对应的下标
            value2Index.computeIfAbsent(arr[i], k -> new HashSet<>()).add(i);
        }

        int minStep = 0;
        Set<Integer> hasReached = new HashSet<>();
        Queue<Integer> nextStep = new LinkedList<>();
        nextStep.add(arr.length-1);
        hasReached.add(arr.length-1);
        while (!nextStep.isEmpty()) {
            int count = nextStep.size();
            minStep++;
            for (int i = 0; i < count; i++) {
                int index = nextStep.poll();
                value2Index.get(arr[index]).remove(index);//移除我已经到达的下标
                Set<Integer> temp = new HashSet<>(value2Index.get(arr[index]));
                //增加+1，-1的下标
                if (index - 1 >= 0) {
                    temp.add(index-1);
                }
                if (index + 1 < arr.length) {
                    temp.add(index + 1);
                }
                for (int a : temp) {
                    if (a == 0) {
                        return minStep;
                    }
                    if (!hasReached.contains(a)) {
                        hasReached.add(a);
                        nextStep.add(a);
                    }
                }
            }
        }
        return minStep;
    }
}
