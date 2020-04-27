package com.bootslee.leetcode;

public class ChalkboardXorGame {
    /**
     * 一个黑板上写着一个非负整数数组 nums[i] 。小红和小明轮流从黑板上擦掉一个数字，
     * 小红先手。如果擦除一个数字后，剩余的所有数字按位异或运算得出的结果等于 0 的话，
     * 当前玩家游戏失败。 (另外，如果只剩一个数字，按位异或运算得到它本身；
     * 如果无数字剩余，按位异或运算结果为 0。）
     * 换种说法就是，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
     * 假设两个玩家每步都使用最优解，当且仅当小红获胜时返回 true。
     * @param nums
     * @return
     */
    public boolean xorGame(int[] nums) {
        int xor=0;
        for (int num:nums)xor=xor^num;
        //推导可知当前数组如果异或后为0，则先手必胜，或者 数组为偶数个时，先手必胜
        return (nums.length&1) ==0 ||(xor==0) ;
    }

}
