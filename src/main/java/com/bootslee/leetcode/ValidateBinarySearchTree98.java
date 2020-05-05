package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created By BootsLee on 2020/5/5
 **/
public class ValidateBinarySearchTree98 {
    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     */
    public boolean isValidBST(TreeNode root) {
        if(root==null)return true;
        List<Integer> list=new ArrayList<>();
        itear(list,root);
        int preValue=list.get(0);
        for (int i = 1; i < list.size() ; i++) {
            if(list.get(i)>preValue){
                preValue=list.get(i);
            }else {
                return false;
            }
        }

        return true;
    }

    public void itear(List<Integer> list,TreeNode node){
        if(node.left!=null){
            itear(list,node.left);
        }
        list.add(node.val);

        if(node.right!=null){
            itear(list,node.right);
        }
    }
    private int preV;
    private boolean init = false;
    public boolean isValidBST1(TreeNode root) {
        if (root == null) return true;
        if (root.left != null){
            if(!isValidBST(root.left)) return false;
        }
        if (!init){
            preV = root.val;
            init = true;
        }else if (preV >= root.val){
            return false;
        }else{
            preV = root.val;
        }

        if (root.right != null){
            if (!isValidBST(root.right)) return false;
        }
        return true;
    }
    public boolean isValidBST3(TreeNode root) {
       Stack<Integer> stack=new Stack<>();
       return helper(stack,root);
    }
    public boolean helper(Stack<Integer> stack,TreeNode node){
        if (node == null) return true;
        if (node.left != null){
            if(!helper(stack,node.left)) return false;
        }
        if (stack.isEmpty()){
            stack.push(node.val);
        }else if (stack.peek() >= node.val){
            return false;
        }else{
            stack.push(node.val);
        }
        if (node.right != null){
            if (!helper(stack,node.right)) return false;
        }
        return true;
    }
    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (! helper(node.right, val, upper)) return false;
        if (! helper(node.left, lower, val)) return false;
        return true;
    }

    public boolean isValidBST4(TreeNode root) {
        return helper(root, null, null);
    }
}
