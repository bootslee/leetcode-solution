package com.bootslee.leetcode;

public class StringToIntegerAtoi8 {
    /**
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * 接下来的转化规则如下：
     * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，
     * 形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     * 该字符串在有效的整数部分之后也可能会存在多余的字符，
     * 那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、
     * 字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     * 提示：
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 假设我们的环境只能存储 32 位大小的有符号整数，
     * 那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，
     * 请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     */
    public static int myAtoi(String str) {
        if (str==null){
            return 0;
        }
        char[] chars=str.trim().toCharArray();
        int i = 0, len = chars.length;
        int limit = -Integer.MAX_VALUE;
        boolean negative = false;
        int result = 0;
        if (len > 0) {
            char firstChar = chars[0];
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative=true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+'){
                    return  0;
                }
                if (len == 1){
                    return  0;
                }
                i++;
            }
            int multmin = limit / 10;
            while (i < len) {
                if(chars[i]<'0'||chars[i]>'9'){
                   break;
                }
                int digit = chars[i]-'0';
                int d= Character.digit(chars[i],10);
                if (result < multmin) {
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result *= 10;
                if (result < limit + digit) {
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result -= digit;
                i++;
            }
        }
        return negative ? result : -result;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("  -42"));
    }
}
