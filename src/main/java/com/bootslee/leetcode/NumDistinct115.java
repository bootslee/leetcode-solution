package com.bootslee.leetcode;

public class NumDistinct115 {
    /**
     * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
     * 一个字符串的一个子序列是指，
     * 通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        /**
         * 分析过程，示例 s=babgbag,t=bag
         *  如果我们把bag分成一个给字符串计算，就可以知道，
         *  如果要匹配bag的情况，在匹配到g的时候我们只需要知道 ba字符串在前面出现的次数，就可以计算出bag的次数
         *  再计算ba的匹配情况，我们只需要知道前面出现了多少次b的，就可以知道ba的匹配情况，
         *  如果我们可以建立一个二维数组记录 每个字符串在s字符串的出现的次数
         *  我们可以推导出一个公式 count[i][j]为t(0-j) 在位s(0,j)字符匹配的出现次数
         *  我们如果当前字符相等 count[i+1][j+1]=count[i+1][j]+count[i][j]
         *  如果不相等 count[i+1][j+1]=count[i+1][j]
         */
        char[] chars=s.toCharArray();
        char[] words=t.toCharArray();
        int[][] counts=new int[words.length+1][chars.length+1];
        //第一阶段，表示“”字符在s字符串中匹配的情况。
        for (int j = 0; j < chars.length + 1; j++) counts[0][j] = 1;
        //计算后续阶段的匹配情况，示例 s=babgbag,t=bag
        //第二阶段计算b字符串匹配的情况 即计算 s中b字符串的个数，
        //  第三阶段，按ba字符串匹配的情况，我们可以知道 如果在s中找到a 只要知道前面有多少给b就可以知道ba的匹配情况
        for (int i=0;i<words.length;i++){
            for (int j=0;j<chars.length;j++){
                if (chars[i] == words[j]) counts[i+1][j+1] = counts[i+1][j] + counts[i][j];
                else counts[i+1][j+1] = counts[i+1][j];
            }
        }
        return counts[words.length][chars.length];
    }
}
