package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SortCharactersByFrequency451 {
    public String frequencySort(String s) {
        int[] count = new int[128];
        for(int i = 0; i < s.length(); i++)
            count[s.charAt(i)] += 1;
        List<Character>[] charsSort =new ArrayList[s.length() + 1];
        for(int i = 0; i < count.length; i++){
            if(count[i]==0){
                continue;
            }
            if(charsSort[count[i]] == null)
                charsSort[count[i]] = new ArrayList<>();
            charsSort[count[i]].add((char) i);
        }

        StringBuilder res = new StringBuilder();
        for(int i = charsSort.length-1; i >= 0; i--){
            if(charsSort[i] != null){
                for(char c : charsSort[i]){
                    for(int j = 0; j < i; j++)
                        res.append(c);
                }
            }
        }
        return res.toString();
    }
}
