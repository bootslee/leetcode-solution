package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.TreeNode;

public class RangeSumBST938 {

    /**
     * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
     * 二叉搜索树保证具有唯一的值。
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        return  dfs(root,L,R,0);
    }
    private int dfs(TreeNode node, int L, int R, int count) {
        if (node != null) {
            if (L <= node.val && node.val <= R)
                count += node.val;
            if (L < node.val)
                count += dfs(node.left, L, R,count);
            if (node.val < R)
                count +=dfs(node.right, L, R,count);
        }
        return count;
    }

}
