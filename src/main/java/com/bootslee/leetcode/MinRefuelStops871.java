package com.bootslee.leetcode;

public class MinRefuelStops871 {
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
