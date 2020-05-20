package com.bootslee.leetcode;

import com.sun.org.apache.bcel.internal.generic.FSUB;

/**
 * Created By BootsLee on 2020/5/16
 **/
public class GasStation134 {
    /**
     * 134. 加油站
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
     * 你从其中的一个加油站出发，开始时油箱为空。
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     * 说明:
     * 如果题目有解，该答案即为唯一答案。
     * 输入数组均为非空数组，且长度相同。
     * 输入数组中的元素均为非负数。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumTank=0,index=0;
        for (int i = 0; i < gas.length; i++) {
            int tank=gas[i]-cost[i];
            sumTank += tank;
            int j = i+1;
            // 因为只有唯一解，可以找到差值大于0的加油站出发
            if(tank>0) {
                index=i;
                // 设置汽车的油量为当前加油站的差值
                int currentTank=tank;
                for (; j < gas.length; j++) {
                    sumTank += gas[j] - cost[j];
                    currentTank+= gas[j] - cost[j];

                    if (currentTank<0){
                        break;
                    }
                }
                i=j;
            }

        }
        return sumTank>=0?index:-1;// 只要开往所有的节点，油箱>=0 则说明此题有解

    }
    // 上一解法存在重复代码，优化下代码结构
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        // 只要开往所有的节点，油箱>=0 则说明此题有解
        int sumTank=0,currentTank=0,index=0;
        for (int i = 0; i < gas.length; i++) {
            int tank=gas[i]-cost[i];
            sumTank += tank;
            // 因为只有唯一解，可以找到差值大于0的加油站出发
            currentTank +=tank;
            // 无法开到下一个加油站了
            if(currentTank<0){
                index=i+1; // 设置为下一个加油站为出发站点
                currentTank=0;
            }
        }
        return sumTank>=0?index:-1;
    }

    // 大神的解法
    // min
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int len = gas.length;
        int spare = 0;
        // 累计油量差距最大的加油站
        int minSpare = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < len; i++) {
            spare += gas[i] - cost[i];
            // 判断出油量差距最大的点
            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }
        // 如果油量差距最大，且总体有解（剩余油量>=0）,那么启动就是差距最大的加油站的下一个加油站(这个加油站的油量差距一定为正)
        return spare < 0 ? -1 : (minIndex + 1) % len;
    }

    public static void main(String[] args) {
        new GasStation134().canCompleteCircuit(new int[]{5,8,2,8},new int[]{6,5,6,6});
    }
}
