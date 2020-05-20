package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/18
 **/
public class AddStrings415 {
    /**
     * 415. 字符串相加
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuilder ans = new StringBuilder();
        int pa = num1.length()-1;
        int pb = num2.length()-1;
        int carry = 0;
        while (pa >=0 || pb >= 0) {
            int va = pa<0 ? 0 : num1.charAt(pa)-'0';
            int vb = pb<0 ? 0 : num2.charAt(pb)-'0';
            int sum = (va + vb + carry)%10;
            ans.append(sum);
            carry = (va + vb + carry)/10;
            pa--;
            pb--;
        }
        ans.append(carry==0 ? "" : carry);
        return  ans.reverse().toString();
    }
    public String addStrings2(String num1, String num2) {
        int len = Math.max(num1.length(), num2.length());
        char[] c1 = new char[len+1];
        char[] c2 = new char[len+1];
        for(int i = 0; i < len+1; i ++) {c1[i] = '0'; c2[i] = '0';}
        for(int i = num1.length()-1; i >= 0; i--) c1[num1.length()-i-1] = num1.charAt(i);
        for(int i = num2.length()-1; i >= 0; i--) c2[num2.length()-i-1] = num2.charAt(i);
        int h = 0;
        for(int i = 0; i < len; i++) {
            int t = h + (c1[i]-'0') + (c2[i]-'0');
            int a = t % 10;
            h = t / 10;
            c1[i] = (char)('0' + a);
        }
        if(h != 0) {
            c1[len] = '1';
            len ++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = len-1; i >= 0; i--) {
            sb.append(c1[i]);
        }
        return sb.toString();
    }
}
