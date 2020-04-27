package com.bootslee.leetcode;

public class NumberOfIslands200 {
    /**
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，
     * 计算岛屿的数量。一个岛被水包围，
     * 并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
     * 你可以假设网格的四个边均被水包围。
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        if(grid.length==0)return 0;

        int counts=0;
        for(int i=0;i<grid.length;i++)
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    grid[i][j]='0';
                    process(i,j,grid);
                    counts++;
                }
            }
        return counts;
    }
    private static void process(int x,int y,char[][] grid){
        for(int i=x+1;i<grid.length;i++){
            if(grid[i][y]=='1'){//向下探索
                grid[i][y]='0';
                process(i,y,grid);
            }else{
                break;
            }
        }
        for(int j=y+1;j<grid[0].length;j++){
            if(grid[x][j]=='1'){//向下探索
                grid[x][j]='0';
                process(x,j,grid);
            }else{
                break;
            }
        }
        for(int i=x-1;i>=0;i++){
            if(grid[i][y]=='1'){//向下探索
                grid[i][y]='0';
                process(i,y,grid);
            }else{
                break;
            }
        }
        for(int j=y-1;j>=0;j++){
            if(grid[x][j]=='1'){//向下探索
                grid[x][j]='0';
                process(x,j,grid);
            }else{
                break;
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid={{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        numIslands(grid);
    }
}
