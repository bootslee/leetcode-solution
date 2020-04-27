package com.bootslee.leetcode;

import java.util.LinkedList;

public class ProcessQueries1409 {
    /**
     * 给你一个待查数组 queries ，数组中的元素为 1 到 m 之间的正整数。
     * 请你根据以下规则处理所有待查项 queries[i]（从 i=0 到 i=queries.length-1）：
     *
     * 一开始，排列 P=[1,2,3,...,m]。
     * 对于当前的 i ，请你找出待查项 queries[i] 在排列 P 中的位置（下标从 0 开始），
     * 然后将其从原位置移动到排列 P 的起始位置（即下标为 0 处）。
     * 注意， queries[i] 在 P 中的位置就是 queries[i] 的查询结果。
     * 请你以数组形式返回待查数组  queries 的查询结果。
     * @param queries
     * @param m
     * @return
     */
    public static int[] processQueries(int[] queries, int m) {
        int[] mm=new int[m];//存储1-m对应值所在位置
        for (int i=0;i<m;i++){
            mm[i]=i+1;
        }
        int[] reuslt=new int[queries.length];
        for (int i=0;i<queries.length;i++){
            int j=0;int pre=mm[0];
            for(;j<m;j++){
                if(mm[j]==queries[i]){
                    break;
                }
                int tmp=mm[j];
                mm[j]=pre;
                pre=tmp;
            }
            reuslt[i]=j;
            mm[0]=queries[i];
            mm[j]=pre;
        }
        return reuslt;
    }
    public static int[] processQueries2(int[] queries, int m) {
        int[] mm=new int[m];//存储1-m对应值所在位置
        for (int i=0;i<m;i++){
            mm[i]=i;
        }
        int[] result=new int[queries.length];
        for (int i=0;i<queries.length;i++){
            result[i]=mm[queries[i]-1];
            for(int j=0;j<m;j++){
                if(mm[j]<mm[queries[i]-1])mm[j]++;
            }
            mm[queries[i]-1]=0;
        }
        return result;
    }

    public static int[] processQueries3(int[] queries, int m) {
        int[] mm=new int[m];//f(i)记录了元素f(i+1)元素是否移动过
        int[] result=new int[queries.length];
        int max=0;int count=0;
        for (int i=0;i<queries.length;i++){
            if(queries[i]>max){//还没移动过
                result[i]=queries[i]-1;
                max=queries[i];
            }else if(mm[queries[i]-1]>0){//前面已经移动过了。
                int[] f=new int[m];//记录不重复元素的出现次数
                int num=0;
                //查找重复元素之间不重复数字的个数
                for(int j=i-1;j>=0&&queries[j]!=queries[i];j--){
                    if(f[queries[j]-1]==0)num++;
                    f[queries[j]-1]=1;
                }
                result[i]=num;
            }else {
                int num=0;
                //查找比他大的元素所造成他移动的次数
                for(int j=queries[i];j<m;j++){
                    if(mm[j]==1)num++;
                }
                result[i]=queries[i]-1+num;
            }
            mm[queries[i]-1]=1;
        }
        return result;
    }

    public static void main(String[] args) {
        processQueries3(new int[]{8,7,4,2,8,1,7,7},8);
        double f=7.;
    }
    public int[] processQueries4(int[] queries, int m) {
        LRUCache lruCache = new LRUCache(m);
        lruCache.put();
        int [] arr = new int[queries.length];
        for (int i =0; i< queries.length; i++){
            arr[i] = lruCache.get(Integer.valueOf(queries[i]));
        }

        return arr;
    }

    class LRUCache {
        private  Integer capacity;
        private LinkedList<Integer> linkedList = new LinkedList<>();
        private LRUCache(Integer capacity){
            this.capacity = capacity;
        }

        public Integer get(Integer value){
            Integer index ;
            index = linkedList.indexOf(value);
            linkedList.remove(value);
            linkedList.addFirst(value);
            return index;
        }

        public void put(){
            for (int i =1; i<= this.capacity; i++){
                this.linkedList.add(i);
            }
        }
    }

}
