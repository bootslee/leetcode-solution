package com.bootslee.leetcode;

public class Rotate1395 {
    /**
     * 给定一幅由N × N矩阵表示的图像，其中每个像素的大小为4字节，编写一种方法，将图像旋转90度。
     *
     * 不占用额外内存空间能否做到？
     *
     *  
     *
     * 示例 1:
     *
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * 示例 2:
     *
     * 给定 matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        //推导 matrix[0][0] -> matrix[0][n] -> matrix[n][n]-> matrix[n][0] -> matrix[0][0]
        //推导 matrix[0][1]-> matrix[1][n] -> matrix[n][n-1]-> matrix[n-1][0] -> matrix[0][1]
        //推导 matrix[0][2]-> matrix[2][n] -> matrix[n][n-2]-> matrix[n-2][0] -> matrix[0][2]
        // ……
        //推导 matrix[0][n-1]-> matrix[n-1][n] -> matrix[n][1]-> matrix[1][0] -> matrix[0][n-1]
        // 结论 matrix[x][y]-> matrix[y][n-x] -> matrix[n-x][n-y]-> matrix[n-y][n-(n-x)] -> matrix[0][n-1]
        // x = x^y;
        // y = x^y;
        // x = x^y;
        int m=matrix.length;
        int n=(m/2);
        for(int x=0;x<n;x++){//按圈进行计算
            int y=x; //循环次数
            while (y < (m-x-1)){
                //左上角 和 右上角 交换位置
                matrix[x][y]     = matrix[x][y]^matrix[y][m-1-x];
                matrix[y][m-1-x] = matrix[x][y]^matrix[y][m-1-x];
                matrix[x][y]     = matrix[x][y]^matrix[y][m-1-x];
                //遍历最外圈,依次遍历四个角的元素
                //左上角（右上角） 和 右下角 交换位置 即 右上角移动到了右下角
                matrix[x][y]         = matrix[x][y]^matrix[m-1-x][m-1-y];
                matrix[m-1-x][m-1-y] = matrix[x][y]^matrix[m-1-x][m-1-y];
                matrix[x][y]         = matrix[x][y]^matrix[m-1-x][m-1-y];
                //左上角（右下角） 和 左下角 交换位置 即 右下角移动到了左下角
                //交换完成，左下角已经到了右上角
                matrix[x][y]     = matrix[x][y]^matrix[m-1-y][x];
                matrix[m-1-y][x] = matrix[x][y]^matrix[m-1-y][x];
                matrix[x][y]     = matrix[x][y]^matrix[m-1-y][x];
                y++;
            }
        }
    }
}
