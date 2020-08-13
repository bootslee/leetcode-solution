package com.bootslee.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created By BootsLee on 2020/6/12
 **/
public class DailyTemperatures739  {
    /**
     * 739. 每日温度
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：
     * 要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，
     * 请在该位置用 0 来代替。
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
}
