package com.bootslee.leetcode;

public class Atoi8 {
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
