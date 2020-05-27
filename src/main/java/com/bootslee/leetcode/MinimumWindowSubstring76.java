package com.bootslee.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By BootsLee on 2020/5/23
 **/
public class MinimumWindowSubstring76 {
    /**
     * 76. 最小覆盖子串
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     * 说明：
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     */
    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();
    public String minWindow(String s, String t) {
        int tLen = t.length();
         // 统计评率
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    /**
     * 检查出现字符串的频率是否满足要求
     * @return
     */
    private boolean check() {
        for (Map.Entry<Character,Integer> entry:ori.entrySet()) {
            Character key =entry.getKey();
            Integer val = entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 无法判断t中包含重复字符
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        Map<Character,Integer> idx=new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            idx.put(t.charAt(i),-1);
        }
        for (int i = 0; i < s.length(); i++) {
            Character c=s.charAt(i);
            if(idx.containsKey(c)){
                int index=idx.get(c);
                if(i>index){
                    idx.put(c,i);
                }

            }
        }
        int min=s.length();int max=-1;
        for (Map.Entry<Character,Integer> entry:idx.entrySet()){
            if(entry.getValue()<0){
                return "";
            }
            min=Math.min(min,entry.getValue());
            max=Math.max(max,entry.getValue());
        }
        return s.substring(min,max+1);
    }
}
