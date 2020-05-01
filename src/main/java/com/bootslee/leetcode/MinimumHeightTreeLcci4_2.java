package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class MinimumHeightTreeLcci4_2 {


    /**
     * 面试题 04.02. 最小高度树
     * 给定一个有序整数数组，元素各不相同且按升序排列，
     * 编写一个算法，创建一棵高度最小的二叉搜索树。
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return bst(nums,0,nums.length-1);
    }

    public  TreeNode bst(int[] nums,int left,int right){
        if (left>right)
            return null;
        int mid=(left+right)/2;
        TreeNode node=new TreeNode(nums[mid]);
        node.left=bst(nums,left,mid-1);
        node.right=bst(nums,mid+1,right);
        return node;
    }

    public char firstUniqChar(String s) {
        char[] chars=s.toCharArray();
        Map<Character, Boolean> existMap = new HashMap<>();
        char[] sc = s.toCharArray();
        for(char c : sc)
            existMap.put(c, !existMap.containsKey(c));
        for(char c : sc)
            if(existMap.get(c)) return c;
        return ' ';
    }

    /**
     * 位运算加法实现
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int temp =  a ^ b;
            int temp2=(a & b) << 1;
            a = temp;
            b =temp2;
        }
        return a;
    }
}
