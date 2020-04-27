package com.bootslee.leetcode;

public class MinRefuelStops871 {
    /**
     * 871. 最低加油次数
     * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
     * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，
     * 并且有 station[i][1] 升汽油。
     * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。
     * 它每行驶 1 英里就会用掉 1 升汽油。
     * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
     * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
     * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。
     * 如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if(startFuel>=target){
            return 0;
        }
        int count=0;
        if(stations.length <= 0 ||startFuel - stations[0][0]<0){
            return -1;
        }
        int fuel=startFuel;
        int start=0;
        while (fuel<target){
            //查找可以行使范围内,油量最大的加油站
            int max=0;
            int i=0;
            for (;i<stations.length;i++) {
                if(fuel<stations[i][0]){
                    break;
                }
                if(stations[i][1]>max){
                    max=stations[i][1];
                    start=i;
                }
            }
            if(max>0){
                stations[start][1]=-1;
                fuel+=max;
                count++;
            }else{
                //如果没找到加油站则退出
                return  -1;
            }
        }
        return fuel<target?-1:count;
    }
}
