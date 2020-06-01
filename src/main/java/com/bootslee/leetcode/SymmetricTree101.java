package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.TreeNode;

/**
 * Created By BootsLee on 2020/5/31
 **/
public class SymmetricTree101 {
    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     * @param root
     * @return
     */

    public boolean isSymmetric(TreeNode root) {
        if(root==null||(root.left == null && root.right == null)){
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        return check(root.left, root.right);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

}
