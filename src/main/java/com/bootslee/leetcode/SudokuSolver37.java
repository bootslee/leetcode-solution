package com.bootslee.leetcode;

import java.util.HashMap;

/**
 * Created By BootsLee on 2020/5/10
 **/
public class SudokuSolver37 {

    /**
     * 37. 解数独
     * 回溯
     * 逐行，从左到右，在每一个位置上试探1-9，成功就进入下一个位置，失败就取消本次选择，做下一个选择
     * 当前行试探完毕就换行，知道换到最后一行
     *
     * @param board
     */
    public void solveSudoku2(char[][] board) {
        // 非法数独
        if (board == null || board.length != 9 || board[0] == null || board[0].length != 9)
            return;
        // 回溯法解决
        backTrace(board, 0, 0);
    }

    private boolean backTrace(char[][] board, int row, int col){
        int n = board.length; // 9
        // 当前行已全部试探过，换到下一行第一个位置
        if (col == 9)
            return backTrace(board, row + 1, 0);
        // 满足结束条件，全部行全部位置都已试探过
        if (row == n)
            // 最后一行最后一个位置[8][8]试探过后会试探[8][9]，会执行[9][0]，返回
            return true;
        // 这个位置数字已给出，不需要试探，直接试探下一个位置
        if (board[row][col] != '.')
            return backTrace(board, row, col + 1);
        // 遍历可选择列表(各选择之间并列)
        for (char c = '1'; c <= '9'; c++){
            // 排除不合法的选择
            if (!isValid(board, row, col, c))
                continue;
            // 做选择
            board[row][col] = c;
            // 进行下一步试探，发现当前选择能成功进行下去，就继续往下
            if (backTrace(board, row, col + 1))
                return true;
            // 撤销本次选择，并列进行下一次选择的试探
            board[row][col] = '.';
        }
        // 这个位置把1-9都试过了，都无法继续下去，说明上一次的选择失败，需要回溯
        return false;
    }

    /**
     * 判断 board[row][col]位置放入字符 ch,是否合理
     * 也就判断这个字符有没有在 同一行，同一列，同一个子数独中出现过
     * 行列比较容易，就是一个for循环
     * 而对于 给定的 board[i][j]，它所在的子数独的索引是 (i / 3) * 3 + j / 3
     * 要扫描这个子数独中的全部9个元素，for循环可以这样写
     * boardIndex = (i / 3) * 3 + j / 3
     * for(int k = 0; k < 9; k++){
     * board[(i/3)*3 + k/3][(j/3)*3 + k % 3]
     * }
     * 因为 i和j是确定的，所以 i / 3 * 3可以确定他所在的子数独在第一个三行，还是第二个三行，还是第三个三行
     * j / 3 * 3可以确定它所在的子数独是前三列还是中散列还是后三列，
     * 相当于这两个只是确定了这个【子数独的左上角坐标】，而需要借助 k 完全对这个9个位置的扫描
     *
     * @param board
     * @param row
     * @param col
     * @param ch
     * @return
     */
    private boolean isValid(char[][] board, int row, int col, char ch) {
        // 三个方向，任一方向重复，ch就不能放在这个位置
        for (int k = 0; k < 9; k++) {
            // 同一行九个位置已出现 ch
            if (board[row][k] == ch) return false;
            // 同一列九个位置中已出现 ch
            if (board[k][col] == ch) return false;
            // 同一个子数独九个位置中已出现 ch
            if (board[(row / 3) * 3 + k / 3][(col / 3) * 3 + k % 3] == ch) return false;
        }
        return true;
    }

    private static final int N = 9;
    private static final int LEN = 1 << N;
    private static final int ALL = (1 << N) - 1;

    // row、col、cell分别表示行、列、3*3宫内可放置数字情况
    // 初始会用9个1表示都可以放置，每次放置都标记(changeState)对应行、列、宫内
    int[] row = new int[N], col = new int[N];
    int[][] cell = new int[3][3];
    // ones表示1~2^9-1之间所有数字的二进制包含1的个数
    // map表示表示2的整数次幂中二进制1所在下标位置：map[1]=0,map[2]=1,map[4]=2；
    int[] ones = new int[LEN], map = new int[LEN];

    public void solveSudoku(char[][] board) {
        int cnt = init(board);
        dfs(board, cnt);
    }

    private boolean dfs(char[][] board, int cnt) {
        print(board);
        if (cnt == 0) return true;
        int x = 0, y = 0, min = 10;
        // 找出包含数字最小的位置，也就是说他可能是最容易放置正确数据的位置
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.') {
                    int k = ones[get(i, j)];
                    if (k < min) {
                        min = k;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        // 尝试他可以放置的数字
        for (int i = get(x, y); i != 0; i ^= lowBit(i)) {
            int t = map[lowBit(i)];
            changeState(x, y, t);
            board[x][y] = (char) ('1' + t);
            //放置一个数字，如果可以则继续放置下一个位置
            if (dfs(board, cnt - 1)) {
                return true;
            }
            //回溯
            changeState(x, y, t);
            board[x][y] = '.';
        }
        return false;
    }

    private int get(int i, int j) {
        return row[i] & col[j] & cell[i / 3][j / 3];
    }

    private int lowBit(int n) {
        return -n & n;
    }

    private int init(char[][] board) {
        for (int i = 0; i < N; i++) row[i] = col[i] = ALL;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                cell[i][j] = ALL;
        for (int i = 0; i < N; i++) map[1 << i] = i;
        for (int i = 0; i < LEN; i++) {
            int n = 0;
            for (int j = i; j != 0; j &= j - 1) n++;
            ones[i] = n;
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.') {
                    cnt++;
                } else {
                    int t = board[i][j] - '1';
                    changeState(i, j, t);
                }
            }
        }
        return cnt;
    }

    private void changeState(int i, int j, int t) {
        row[i] ^= 1 << t;
        col[j] ^= 1 << t;
        cell[i / 3][j / 3] ^= 1 << t;
    }
    /**
     * 36.有效的数独
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9) {
            return false;
        }
        int[] map = new int[9];
        for (int i = 0; i < 9; i++) {
            if (board[i] == null || board[i].length != 9) {
                return false;
            }
            for (int j = 0; j < 9; j++) {
                int key = board[i][j] - '1';
                if (key >= 0 && key <= 8) {
                    //用int 32位数字来第1-9位分别表示横向 1-9 ，10-18位表示纵向的1-9 18-26位表示单个格子的1-9
                    int index = (1 << i) | (1 << (j + 9)) | (1 << ((i / 3 * 3) + j / 3 + 18));
                    int old = map[key];
                    if ((old & index) == 0) {//如果这个单元格还没有值，设置进去
                        map[key] = old | index;
                    }else {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        char[][] board={{'.','.','.', '8','.','.', '.','3','9'},
                        {'8','9','2', '.','.','1', '.','.','5'},
                        {'.','.','3', '.','5','.', '8','.','.'},

                        {'.','.','8', '.','9','.', '.','.','6'},
                        {'.','7','.', '.','.','2', '.','.','.'},
                        {'1','.','.', '4','.','.', '.','.','.'},

                        {'.','.','9', '.','8','.', '.','5','.'},
                        {'.','2','.', '.','.','.', '6','.','.'},
                        {'4','8','.', '7','.','.', '.','9','.'}};
        new SudokuSolver37().solveSudoku(board);
        print(board);
    }

    private static void print(char[][] board) {
        System.out.println("-----------------------------");
        for (int i = 0;  i< 9; i++) {
            for (int k = 0; k < 9; k++) {
                System.out.print(board[i][k] + ",");
            }
            System.out.println();
        }
    }
}
