package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/6
 **/
public class MinimumCostForTickets983 {
    /**
     * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，
     * 你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
     * 火车票有三种不同的销售方式：
     * 一张为期一天的通行证售价为 costs[0] 美元；
     * 一张为期七天的通行证售价为 costs[1] 美元；
     * 一张为期三十天的通行证售价为 costs[2] 美元。
     * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，
     * 那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
     * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
     * 提示：
     * 1 <= days.length <= 365
     * 1 <= days[i] <= 365
     * days 按顺序严格递增
     * costs.length == 3
     * 1 <= costs[i] <= 1000
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        if(days == null || days.length < 1 || costs == null || costs.length < 1)
            return 0;
        int[] dp = new int[days[days.length-1]+1];
        for(int num : days){
            dp[num] = Integer.MAX_VALUE;
        }
        for(int i = 1; i <= dp.length-1; i++){
            // 跳过不需要出去的天数
            if(dp[i] == 0){
                dp[i] = dp[i-1];
                continue;
            }
            //转移方程的含义为，如果今天需要出行，
            // 则看是今天买1天的票便宜
            // 还是7天前买票或者30天前买票便宜
            // 和常规意义的来说 有点不太好理解的时，
            // 假设 7天前买票便宜，我是直接 将 这7天之内买的票更换为 7天前买一张票了。
            // 但不需要去修改dp前面的记录，因为只需要求最小花费，而不是每天的花费的记录

            int num1 = dp[i-1]+costs[0];
            int num2 = i > 7 ? dp[i-7]+costs[1] : costs[1];
            int num3 = i > 30 ? dp[i-30]+costs[2] : costs[2];
            dp[i] = Math.min(num1,Math.min(num2,num3));
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        new MinimumCostForTickets983().mincostTickets(new int[]{1,4,6,7,8,20},new int[]{2,7,15});
    }
}
