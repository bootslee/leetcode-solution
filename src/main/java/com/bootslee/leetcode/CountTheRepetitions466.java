package com.bootslee.leetcode;

public class CountTheRepetitions466 {
    /**
     * 定义由 n 个连接的字符串 s 组成字符串 S，即 S = [s,n]。例如，["abc", 3]=“abcabcabc”。
     * 另一方面，如果我们可以从 s2 中删除某些字符使其变为 s1，我们称字符串 s1 可以从字符串 s2 获得。
     * 例如，“abc” 可以根据我们的定义从 “abdbec” 获得，但不能从 “acbbe” 获得。
     * 现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ N1 ≤ 10^6 和 1 ≤ N2 ≤ 10^6。
     * 现在考虑字符串 S1 和 S2，其中 S1=[s1,n1] 和 S2=[s2,n2] 。
     * 请你找出一个可以满足使[s2,M] 从 S1 获得的最大整数 M 。
     * @param s1
     * @param n1
     * @param s2
     * @param n2
     * @return
     */
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if(s1.length() ==0 || s2.length()==0 || n1==0 || n2 ==0) return 0;
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        int count = 0;
        int index = 0;
        //存储在每个s1字符串中可以匹配出的s2字符串的索引
        int[] indexr = new int[s2Chars.length+1];
        //存储在每个s1字符串时匹配出的s2字符串的个数，可能是包含了前面一个s1循环节的部分
        int[] countr = new int[s2Chars.length+1];
        for(int i=0;i<n1;i++){
            for(int j = 0; j<s1Chars.length;j++){
                if(s1Chars[j] == s2Chars[index]) {
                    if(index == s2Chars.length -1) {
                        count++;
                        index = 0;
                    }else{
                        index++;
                    }
                }
            }
            countr[i] = count;
            indexr[i] = index;
            //剪枝，跳出循环的判断
            //从计数的数组里面去找是否已经出现过该索引。
            //意味着已经出现重复的循环节了。就可以直接计算了。
            for (int k = 0; k < i && indexr[k] == index; k++) {
                int prev_count = countr[k];
                int pattern_count = ((n1 - 1 - k) / (i - k))*(countr[i] - countr[k]);
                int remain_count = countr[k + (n1 - 1 - k) % (i - k)] - countr[k];
                return (prev_count + pattern_count + remain_count) / n2;
            }
        }
        return countr[n1 - 1] / n2;
    }
}
