package com.bootslee.leetcode;

import com.bootslee.leetcode.datastructure.TreeNode;

import java.util.HashMap;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal106 {
    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     */
    public static class TreeNodeBuilder{
        private int postIdx;
        private HashMap<Integer,Integer> idxMap;
        private int[] inorder;
        private int[] postorder;
        public TreeNodeBuilder(int[] inorder,int[] postorder){
            idxMap=new HashMap<>();
            postIdx = postorder.length - 1;
            for (int i = 0; i < inorder.length ; i++) {
                idxMap.put(inorder[i], i);
            }
            this.inorder=inorder;
            this.postorder=postorder;
        }

        public TreeNode build(){
            return build(0,inorder.length-1);
        }
        private TreeNode build(int in_left, int in_right){
            // 没有元素了
            if (in_left > in_right){
                return null;
            }
            // 父元素
            int root_val = postorder[postIdx];
            TreeNode root = new TreeNode(root_val);

            // 根据根节点切分 中序列表
            int index = idxMap.get(root_val);

            // 取一个元素
            postIdx--;
            // 构建右子树
            root.right = build(index + 1, in_right);
            // 构建左子树
            root.left = build(in_left, index - 1);
            return root;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return new TreeNodeBuilder(inorder, postorder).build();
    }
}
