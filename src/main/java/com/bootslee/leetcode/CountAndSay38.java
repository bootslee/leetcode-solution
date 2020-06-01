package com.bootslee.leetcode;

/**
 * Created By BootsLee on 2020/5/29
 **/
public class CountAndSay38 {
    /**
     * 38. 外观数列
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
     * 1.     1   给一个数，这个数是1
     * 2.     11   描述上一步的数，这个数是 1 即一个1，故写作11
     * 3.     21   描述上一步的数，这个数是11即两个1，故写作21
     * 4.     1211 描述上一步的数，这个数是21即一个2一个1，故写作12-11
     * 5.     111221  描述上一步的数，这个数是1211即一个1一个2两个1，故写作11-12-21
     * 1 被读作  "one 1"  ("一个一") , 即 11。
     * 11 被读作 "two 1s" ("两个一"）, 即 21。
     * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
     * 注意：整数序列中的每一项将表示为一个字符串。
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        // 提示，读懂题目最重要
        StringBuilder s = new StringBuilder();
        int p1 = 0;
        int cur = 1;
        if ( n == 1 )
            return "1";
        String str = countAndSay(n - 1);
        for ( cur = 1; cur < str.length(); cur++ ) {
            // 如果碰到当前字符与前面紧邻的字符不等则更新此次结果
            if ( str.charAt(p1) != str.charAt(cur) ) {
                int count = cur - p1;
                s.append(count).append(str.charAt(p1));
                p1 = cur;
            }
        }
        // 防止最后一段数相等，如果不等说明p1到cur-1这段字符串是相等的
        if ( p1 != cur ){
            int count = cur - p1;
            s.append(count).append(str.charAt(p1));
        }
        return s.toString();
    }
}
