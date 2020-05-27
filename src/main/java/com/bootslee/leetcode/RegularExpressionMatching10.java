package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/23
 **/
public class RegularExpressionMatching10 {
    /**
     * 10. 正则表达式匹配
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch(String s, String p) {
       /*
       s和p可能为空。空的长度就是0，但是这些情况都已经判断过了，只需要判断是否为null即可
       if(p.length()==0&&s.length()==0)
            return true;
            */
        if (s == null || p == null)
            return false;
        int rows = s.length();
        int columns = p.length();
        boolean[][] dp = new boolean[rows + 1][columns + 1];
        //s和p两个都为空，肯定是可以匹配的，同时这里取true的原因是
        //当s=a，p=a，那么dp[1][1] = dp[0][0]。因此dp[0][0]必须为true。
        dp[0][0] = true;
        for (int j = 1; j <= columns; j++) {
            //p[j-1]为*可以把j-2和j-1处的字符删去，只有[0,j-3]都为true才可以
            //因此dp[j-2]也要为true，才可以说明前j个为true
            if (p.charAt(j - 1) == '*' && dp[0][j - 2])
                dp[0][j] = true;
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                char nows = s.charAt(i - 1);
                char nowp = p.charAt(j - 1);
                if (nows == nowp) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (nowp == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (nowp == '*') {
                        //p需要能前移1个。（当前p指向的是j-1，前移1位就是j-2，因此为j>=2）
                        if (j >= 2) {
                            char nowpLast = p.charAt(j - 2);
                            //只有p[j-2]==s[i-1]或p[j-2]==‘.’才可以让*取1个或者多个字符：
                            if (nowpLast == nows || nowpLast == '.')
                                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                            //不论p[j-2]是否等于s[i-1]都可以删除掉j-1和j-2处字符：
                            dp[i][j] = dp[i][j] || dp[i][j - 2];
                        }
                    } else
                        dp[i][j] = false;
                }
            }
        }
        return dp[rows][columns];
    }

    enum Result {
        TRUE, FALSE
    }

    Result[][] memo;

    public boolean isMatch2(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = (dp(i, j + 2, text, pattern) ||
                        first_match && dp(i + 1, j, text, pattern));
            } else {
                ans = first_match && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    /**
     * 回溯算法
     *
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch3(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch3(text, pattern.substring(2)) ||
                    (first_match && isMatch3(text.substring(1), pattern)));
        } else {
            return first_match && isMatch3(text.substring(1), pattern.substring(1));
        }
    }

}
