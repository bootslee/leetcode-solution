package com.bootslee.leetcode;

public class ValidParenthesisString678 {
    /**
     * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
     *  任何左括号 ( 必须有相应的右括号 )。
     *  任何右括号 ) 必须有相应的左括号 ( 。
     *  左括号 ( 必须在对应的右括号之前 )。
     *  * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
     *  一个空字符串也被视为有效字符串。
     * 题解：
     *  直接计数尝试解决，如果两边左括号和右括号数量相等其实就是满足的
     *  考虑到特殊字符* 的存在，可以在依次遍历时，建立一个左括号的范围值 [min,max]
     *  也就是所需右括号的所需值。
     *
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        char[] chars=s.toCharArray();
        int ma=0;//当前位置最大的未匹配的'('个数
        int mi=0;//当前位置最小的未匹配的'('个数
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='('){// 范围右移1
                ma++;
                mi++;
            }
            else if(chars[i]==')'){
                if(ma<=0) return false;//当前左括号不够了，直接失败
                else{
                    ma--;
                    if(mi>0) mi--;
                }
            }
            else{//遇到* max+1，min-1:可以替代右括号;
                ma++;
                if(mi>0) mi--;

            }
        }
        return ma>=0&&mi<=0;
    }
}
