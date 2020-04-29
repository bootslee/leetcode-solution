package com.bootslee.leetcode.datastructure;

public class MountainArray {
    private int[] values;
   public MountainArray(int[] nums){
        values=nums;
    }
    public int get(int index){
       return  values[index];
    }
    public int length(){
        return values.length;
    }
}

