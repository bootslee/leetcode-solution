package com.bootslee.leetcode;

public class DecodeAtIndex880 {
    /**
     * 定一个编码字符串 S。为了找出解码字符串并将其写入磁带，
     * 从编码字符串中每次读取一个字符，并采取以下步骤：
     * 如果所读的字符是字母，则将该字母写在磁带上。
     * 如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写 d-1 次。
     * 现在，对于给定的编码字符串 S 和索引 K，查找并返回解码字符串中的第 K 个字母。
     * @param S
     * @param K
     * @return
     */
    public String decodeAtIndex(String S, int K) {
        long size = 0;
        int N = S.length();

        // Find size = length of decoded string
        for (int i = 0; i < N; ++i) {
            char c = S.charAt(i);
            if (Character.isDigit(c))
                size *= c - '0';
            else
                size++;
        }

        for (int i = N-1; i >= 0; --i) {
            char c = S.charAt(i);
            K %= size;
            if (K == 0 && Character.isLetter(c))
                return Character.toString(c);

            if (Character.isDigit(c))
                size /= c - '0';
            else
                size--;
        }

        throw null;
    }
}
