package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.List;

public class BSTTreeElements1305 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    private void dfs(TreeNode root1,TreeNode root2, List<Integer> ansList) {
        if (root1 == null&&root2==null) {
            return;
        }
        if(root1 == null && root2!=null){
            dfs(root2.left,null, ansList);
            ansList.add(root2.val);
            dfs(null,root2.right, ansList);
        }
        if(root1 != null&&root2==null){
            dfs(root1.left,null, ansList);
            ansList.add(root1.val);
        }
        if(root1.val>root2.val){
            dfs(root1.left,root2, ansList);
            ansList.add(root2.val);
            dfs(root1,root2.right, ansList);
        }else {
            dfs(root1,root2.left, ansList);
            ansList.add(root1.val);
            dfs(root1.right,root2, ansList);
        }
    }
    private void dfs(TreeNode root1,List<Integer> ansList) {
        if (root1 == null) {
            return;
        }
        dfs(root1.left, ansList);
        ansList.add(root1.val);
        dfs(root1.right, ansList);
    }
    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> ansList = new ArrayList<>();
        int size1 = list1.size();
        int size2 = list2.size();
        int index1, index2;
        for (index1 = 0, index2 = 0; index1 < size1 && index2 < size2;) {
            int num1 = list1.get(index1);
            int num2 = list2.get(index2);
            if (num1 < num2) {
                ansList.add(num1);
                index1++;
            } else {
                ansList.add(num2);
                index2++;
            }
        }

        while (index1 < size1) {
            ansList.add(list1.get(index1++));
        }

        while (index2 < size2) {
            ansList.add(list2.get(index2++));
        }

        return ansList;
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ansList1 = new ArrayList<>();
        List<Integer> ansList2 = new ArrayList<>();
        dfs(root1, ansList1);
        dfs(root2, ansList2);

        return merge(ansList1, ansList2);
    }
    public List<Integer> getAllElements1(TreeNode root1, TreeNode root2) {
        List<Integer> ansList1 = new ArrayList<>();
        dfs(root1,root2, ansList1);
        return ansList1;
    }
}
