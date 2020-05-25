package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.TreeNode;

import java.util.HashMap;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal105 {
    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 根据一棵树的前序遍历与中序遍历构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return new TreeNodeBuilder(preorder, inorder).build();
    }
    public static class TreeNodeBuilder{
        private HashMap<Integer,Integer> idxMap;
        private int[] inorder;
        private int[] preorder;
        public TreeNodeBuilder(int[] preorder,int[] inorder){
            idxMap=new HashMap<>();
            for (int i = 0; i < inorder.length ; i++) {
                idxMap.put(inorder[i], i);
            }
            this.inorder=inorder;
            this.preorder=preorder;
        }
        public TreeNode build(){
            return build(0,preorder.length-1,0,inorder.length-1);
        }
        private TreeNode build(int preorderLeft, int preorderRight, int inorderLeft, int inorderRight){
            if (preorderLeft > preorderRight) {
                return null;
            }

            // 前序遍历中的第一个节点就是根节点
            int preorderRoot = preorderLeft;
            // 在中序遍历中定位根节点
            int inorderRoot = idxMap.get(preorder[preorderRoot]);

            // 先把根节点建立出来
            TreeNode root = new TreeNode(preorder[preorderRoot]);
            // 得到左子树中的节点数目
            int sizeLeftSubtree = inorderRoot - inorderLeft;
            // 递归地构造左子树，并连接到根节点
            // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
            root.left = build(preorderLeft + 1, preorderLeft + sizeLeftSubtree, inorderLeft, inorderRoot - 1);
            // 递归地构造右子树，并连接到根节点
            // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
            root.right = build(preorderLeft + sizeLeftSubtree + 1, preorderRight, inorderRoot + 1, inorderRight);
            return root;
        }
    }
}
