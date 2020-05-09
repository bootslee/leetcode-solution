package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.TreeNode;

import java.util.Stack;

/**
 * Created By BootsLee on 2020/5/10
 **/
public class LowestCommonAncestorOfBinaryTree236 {
    /**
     * 236. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return null;
        if(root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left =  lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 左边没有就在右边
        if(left == null) {
            return right;
        }
        // 左边没有就在右边
        if(right == null) {
            return left;
        }
        // p和q在两侧 就返回根节点
        if(left!=null && right!=null) {
            return root;
        }
        // 必须有返回值
        return null;
    }
}
