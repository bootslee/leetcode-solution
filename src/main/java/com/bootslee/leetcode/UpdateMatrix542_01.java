package com.bootslee.leetcode;

public class UpdateMatrix542_01 {
    /**
     * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     * 两个相邻元素间的距离为 1 。
     *
     */
    public int[][] updateMatrix(int[][] matrix) {

        int rows=matrix.length;//行数
        int cols=matrix[0].length;//列数
        int[][] updateMatrix=new int[rows][cols];

        int max=Math.max(cols,rows);
        for(int i=0;i<rows;i++){//初始化矩阵
            for (int j=0;j<cols;j++){
                if(matrix[i][j]==0){//将当前数组中为1的元素设置一个较大的值
                    updateMatrix[i][j]=0;
                }else{
                    updateMatrix[i][j]=max;
                }
            }
        }
        // 向左移动 和 向上移动
        // 第一次遍历去找最近的元素的位置
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (i - 1 >= 0) {
                    updateMatrix[i][j] = Math.min(updateMatrix[i][j], updateMatrix[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    updateMatrix[i][j] = Math.min(updateMatrix[i][j], updateMatrix[i][j - 1] + 1);
                }
            }
        }

        // 向右移动 和 向下移动
        // 再反向遍历去找最近的元素的位置
        for (int i = rows - 1; i >= 0; --i) {
            for (int j = cols - 1; j >= 0; --j) {
                if (i + 1 < rows) {
                    updateMatrix[i][j] = Math.min(updateMatrix[i][j], updateMatrix[i + 1][j] + 1);
                }
                if (j + 1 < cols) {
                    updateMatrix[i][j] = Math.min(updateMatrix[i][j], updateMatrix[i][j + 1] + 1);
                }
            }
        }
        return  updateMatrix;
    }
}
