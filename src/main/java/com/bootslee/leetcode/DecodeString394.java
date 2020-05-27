package com.bootslee.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created By BootsLee on 2020/5/28
 **/
public class DecodeString394 {
    /**
     * 394. 字符串解码
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
     * 注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     */
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Stack<Integer> multiStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        int length=s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '[') {
                multiStack.push(multi);
                stringStack.push(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int curMulti = multiStack.pop();
                for (int j = 0; j < curMulti; j++) {
                    tmp.append(res);
                }
                res = new StringBuilder(stringStack.pop() + tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + (c-'0');
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
