package com.bootslee.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountVowelPermutation1220 {
    /**
     * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
     *
     * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
     * 每个元音 'a' 后面都只能跟着 'e'
     * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
     * 每个元音 'i' 后面 不能 再跟着另一个 'i'
     * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
     * 每个元音 'u' 后面只能跟着 'a'
     * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
     * @param n
     * @return
     */
    static Map<Character, List<Character>> rules=new HashMap<>();
    static {
        rules.put('a', Arrays.asList('e'));
        rules.put('e', Arrays.asList('a','i'));
        rules.put('i', Arrays.asList('a','e','o','u'));
        rules.put('o', Arrays.asList('i','u'));
        rules.put('u', Arrays.asList('a'));
    }
    public int countVowelPermutation(int n) {
        if(n==0){
            return 0;
        }
        if(n==1){
            return 5;
        }
        int count=1;
        int mod = (int)Math.pow(10,9) + 7;
        /**
         * 推理一下：
         * i=1, count=5 =1个a,1个e，1个i，1个o，1个u
         * i=2, count=10 = (1+2+4+2) 得到 3个a, 2个e，2个i，1个o，2个u
         * i=3, count=19= (3*1+2*2+2*4+2*1+1*2) 得到 2+2+2个a,3+2个e，2+1个i，2个o，2+1个u
         */
        long[] ints =new long[]{1, 1, 1, 1, 1};
        while (count<n){
            count++;
            ints=new long[]{(ints[1]+ints[2]+ints[4] )% mod, (ints[0]+ints[2])% mod, (ints[1]+ints[3])% mod,  (ints[2])% mod, (ints[2]+ints[3])% mod};
        }
        int res = 0;
        for(int i = 0;i < 5;i++){
            res += ints[i];
            res %= mod;
        }
        return res;
    }
}
