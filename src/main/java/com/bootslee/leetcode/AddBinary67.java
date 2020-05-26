package com.bootslee.leetcode;

import java.util.HashMap;

/**
 * Created By BootsLee on 2020/5/18
 **/
public class AddBinary67 {
    /**
     * 67. 二进制求和
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     * 输入为 非空 字符串且只包含数字 1 和 0。
     */
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int pa = a.length() - 1;
        int pb = b.length() - 1;
        int carry = 0;
        while (pa >= 0 || pb >= 0) {
            int va = pa < 0 ? 0 : a.charAt(pa) - '0';
            int vb = pb < 0 ? 0 : b.charAt(pb) - '0';
            int sum = (va + vb + carry) & 1;
            ans.append(sum);
            carry = (va + vb + carry) >> 1;
            pa--;
            pb--;
        }
        ans.append(carry == 0 ? "" : carry);
        return ans.reverse().toString();
    }

    public String addBinary2(String a, String b) {
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }

        int[] ret = new int[Math.max(a.length(), b.length()) + 1];
        for (int i = 0; i < a.length(); i++) {
            ret[i] += a.charAt(a.length() - i - 1) - '0';
        }
        for (int i = 0; i < b.length(); i++) {
            ret[i] += b.charAt(b.length() - i - 1) - '0';
        }

        for (int i = 0, last = 0; i < ret.length; i++) {
            last = last + ret[i];
            ret[i] = last & 1;
            last = last >> 1;
        }

        int index = ret.length - 1;
        for (; index >= 0 && ret[index] == 0; index--){
            // no thing
        }

        StringBuilder sb = new StringBuilder();
        for (; index >= 0; index -= 1) {
            sb.append(ret[index]);
        }

        if (sb.length() == 0) {
            return "0";
        } else {
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String one = new String("A");
        String two = "A".intern();
        System.out.println((one == two));
    }
}
