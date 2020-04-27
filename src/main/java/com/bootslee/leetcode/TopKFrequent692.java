package com.bootslee.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKFrequent692 {
    /**
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        List<String> list=new ArrayList<>(k);
        Map<String,Integer> counts=new HashMap<>();
        for (String word:words){// 统计各个元素的计算
            counts.put(word,counts.getOrDefault(word,0)+1);
        }
        // 排序
        List<Map.Entry<String,Integer>> sortList=new ArrayList<>(counts.entrySet());
        Collections.sort(sortList,(o1, o2) -> {
            if(o2.getValue().compareTo(o1.getValue())==0){
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue().compareTo(o1.getValue());
        });
        //获取前K个元素
        for(int i=0;i<k;i++){
            list.add(sortList.get(i).getKey());
        }
        return list;
    }
    // 利用PriorityQueue 数组的排序性
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String,Integer> counts=new HashMap<>();
        for (String word:words){
            counts.put(word,counts.getOrDefault(word,0)+1);
        }
        PriorityQueue<Map.Entry<String,Integer>> heap = new PriorityQueue<Map.Entry<String,Integer>>(
                (w1, w2) -> w1.getValue()==w2.getValue() ?
                        w2.getKey().compareTo(w1.getKey()) : w1.getValue() - w2.getValue() );
        for (Map.Entry<String,Integer> word: counts.entrySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }
        List<String> list=new ArrayList<>(k);
        while (!heap.isEmpty()) list.add(heap.poll().getKey());
        Collections.reverse(list);
        return list;
    }
    // stream 写法
    public List<String> topKFrequent3(String[] words, int k) {
        Map<String, Long> map = Arrays.stream(words).parallel().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return map.entrySet().parallelStream().sorted((w1, w2) -> w2.getValue()==w1.getValue() ?
                w1.getKey().compareTo(w2.getKey()) : w2.getValue().compareTo(w1.getValue())).limit(k).map(w->w.getKey()).collect(Collectors.toList());
    }
}
