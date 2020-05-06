package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.TreeNode;

public class SubtreeOfAnotherTree572 {
    /**
     * 572. 另一个树的子树
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
     * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null) {
            return false;
        }
        if(helper(s, t)) {
            return true;
        } else {
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }

    private boolean helper(TreeNode p, TreeNode q){
        if(q == null && p == null) {
            return true;
        }
        if(q == null || p == null) {
            return false;
        }
        if(q.val != p.val) {
            return false;
        } else {
            return helper(q.left, p.left) && helper(q.right, p.right);
        }
    }


    private boolean isFoundSame = false;
    private int tCount;

    /**
     * 递归计算二叉树s每棵子树的节点数，并同时寻找是否有子树与t相等
      */
    // 判断两棵二叉树是否相等
    private boolean isSame(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2 == null;
        }

        if (root2 == null) {
            return false;
        }

        return root1.val == root2.val && isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
    }
    private int calcCount(TreeNode root, TreeNode t) {
        if (isFoundSame) {
            return 0;
        }

        if (root == null) {
            return 0;
        }

        int count = calcCount(root.left, t) + calcCount(root.right, t) + 1;
        if (count == tCount && isSame(root, t)) {
            isFoundSame = true;
            return 0;
        }
        return count;
    }

    /**
     *  计算二叉树t的节点个数
     */
    private int calcTCount(TreeNode root) {
        return root == null ? 0 : calcTCount(root.left) + calcTCount(root.right) + 1;
    }

    /**
     * 找出子树的节点数量 以便快速判断是否包含子树
     */
    public boolean isSubtree2(TreeNode s, TreeNode t) {
        tCount = calcTCount(t);
        calcCount(s, t);
        return isFoundSame;
    }

    // KMP算法
    public boolean isSubtree3(TreeNode s, TreeNode t) {
        System.out.println(serialByPreOrder(s));
        System.out.println(serialByPreOrder(t));
        return getIndexOf(serialByPreOrder(s), serialByPreOrder(t)) != -1;
    }

    public static String serialByPreOrder(TreeNode head) {
        if (head == null) {
            return "_#_";
        }
        String cur = "_" + head.val + "_";
        return cur + serialByPreOrder(head.left) + serialByPreOrder(head.right);
    }

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int next[] = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1 ++;
                i2 ++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2: -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

}
