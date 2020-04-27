package com.bootslee.leetcode;

public class GameOfLife289 {

    public void gameOfLife(int[][] board) {
        if (board.length==0)return;
        int x=board.length;
        int y=board[0].length;
        int[][] lifes=new int[x][y];

        for (int i=0;i<x;i++){
            for (int j=0;j<y;j++){
                int ret=cal(board,i,j,x,y);
                lifes[i][j]=ret;
            }
        }
        for (int i=0;i<x;i++){
            for (int j=0;j<y;j++){
                board[i][j]=lifes[i][j];
            }
        }
    }

    /**
     * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
     * 每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。
     * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。
     * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，
     * 其中细胞的出生和死亡是同时发生的。
     */
    private int cal(int[][] board, int i, int j,int x,int y) {
        int dx[] = {-1,  0,  1, -1, 1, -1, 0, 1};//建立一个数组代表统计时，周边的位置。
        int dy[] = {-1, -1, -1,  0, 0,  1, 1, 1};//建立一个数组代表统计时，周边的位置。
        int count=0;
        for(int k = 0; k < 8; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if(nx >= 0 && nx < x && ny >= 0 && ny < y) {
                count += (board[nx][ny]);
            }
        }
        System.out.println("i="+i+",j="+j+",x="+x+",y="+y+",c="+count);
        return  board[i][j]==1?(count<2?0:count>3?0:1):(count==3?1:0);
    }

    /**
     * 利用原数组统计计数
     * @param board
     */
    public void gameOfLife2(int[][] board) {
        if (board.length==0)return;

        int dx[] = {-1,  0,  1, -1, 1, -1, 0, 1};//建立一个数组代表统计时，周边的位置。
        int dy[] = {-1, -1, -1,  0, 0,  1, 1, 1};//建立一个数组代表统计时，周边的位置。

        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                int count=0;
                for(int k = 0; k < 8; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length) {
                        count += (board[nx][ny]&1); // 只累加最低位
                    }
                }
                //利用int 第二位保存计算后的结果，0x11 代表当前活，计算后也是活
                //0x01代表计算后为死，0x10代表计算后为活。0x00代表计算后依旧为死
                board[i][j] = board[i][j]==1?(count<2?1:count>3?1:3):(count==3?2:0);
            }
        }
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                board[i][j]=board[i][j]>>1;//位移1位更新状态
            }
        }
    }
}
