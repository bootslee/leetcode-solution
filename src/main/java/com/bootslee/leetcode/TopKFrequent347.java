package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequent347 {
    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> retList=new ArrayList<>(k);
        Map<Integer,Integer> counts=new HashMap<>();
        int max=0;//记录最大的序号，优化桶的个数
        for (Integer word:nums){
            counts.put(word,counts.getOrDefault(word,0)+1);
            if(max<counts.get(word)){
                max=counts.get(word);
            }
        }
        //桶排序 复杂度 O(n)
        //生成N+1个桶,最大桶数已经通过统计时得知了。
        List<Integer>[] sortList = new List[max+1];
        for(int key : counts.keySet()){
            // 获取出现的次数作为下标
            int i = counts.get(key);
            if(sortList[i] == null){
                sortList[i] = new ArrayList();
            }
            sortList[i].add(key);
        }
        for(int i = sortList.length - 1;i >= 0 && retList.size() < k;i--){
            if(sortList[i] == null) continue;
            retList.addAll(sortList[i]);
        }
        return retList.subList(0,k);
    }

}
