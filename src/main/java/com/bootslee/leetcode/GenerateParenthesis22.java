package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis22 {
    public List<String> generateParenthesis(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n == 0)
            return result.get(0);
        List<String> list0 = new ArrayList<String>();
        list0.add("");
        result.add(list0);
        List<String> list1 = new ArrayList<String>();
        list1.add("()");
        result.add(list1);
        for (int i = 2; i <= n; i++) {
            List<String> temp = new ArrayList<String>();
            for (int j = 0; j < i; j++) {
                List<String> listJ = result.get(j);
                List<String> listI = result.get(i - 1 - j);
                for (String s1 : listI) {
                    for (String s2 : listJ) {
                        String el = "(" + s1 + ")" + s2;
                        temp.add(el);
                    }
                }

            }
            result.add(temp);
        }
        return result.get(n);
    }
    ArrayList[] cache = new ArrayList[100];
    public List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generate(c))
                    for (String right: generate(n - 1 - c))
                        ans.add("(" + left + ")" + right);
        }
        cache[n] = ans;
        return ans;
    }
    public List<String> generateParenthesis2(int n) {
        return generate(n);
    }
}
