岛屿问题主要就是连接问题。


leetCode 200 岛屿数量

```java
class Solution {
 public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for(int i = 0; i < n; i ++){
            for(int j = 0 ; j < m; j ++){
                if(grid[i][j] == '1'){
                    ans ++;
                    backtrack(grid,i,j);
                }
            }
        }
        return ans;
    }
    
    public void backtrack(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j <0 || j >= grid[0].length || grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '0';
        backtrack(grid,i-1,j);
        backtrack(grid,i+1,j);
        backtrack(grid,i,j-1);
        backtrack(grid,i,j+1);
    }
}
```

leetcode 695 岛屿的最大面积

```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int max = 0;
        for(int i = 0; i < n; i ++){
            for(int j = 0 ; j < m; j ++){
                if(grid[i][j] == 1){
                    max = Math.max(backtrack(grid,i,j),max);
                }
            }
        }
        return max;
    }

    public int backtrack(int[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j <0 || j >= grid[0].length || grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;
        int ans = 1;
        int backtrack = backtrack(grid, i - 1, j);
        ans += backtrack;
        int backtrack1 = backtrack(grid, i + 1, j);
        ans += backtrack1;
        int backtrack2 = backtrack(grid, i, j - 1);
        ans += backtrack2;
        int backtrack3 = backtrack(grid, i, j + 1);
        ans += backtrack3;
        return ans;
    }
}
```

leetcode 130 包围的区域

```java
class Solution {
    public void solve(char[][] board) {
        int n = board.length;
        if(n == 0){
            return;
        }
        int m = board[0].length;
        for(int i = 0 ; i < n ; i ++){
            dfs(board,i,0);
            dfs(board,i,m-1);
        }
        for(int i = 0; i < m ; i ++){
            dfs(board,0,i);
            dfs(board,n-1,i);
        }
        for(int i =0 ; i< n; i ++){
            for(int j = 0 ; j < m; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == '*'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int n, int m){
        if(n < 0 || n >= board.length || m<0 || m >= board[0].length || board[n][m] != 'O'){
            return;
        }
        board[n][m] = '*';
        dfs(board,n-1,m);
        dfs(board,n+1,m);
        dfs(board,n,m-1);
        dfs(board,n,m+1);
    }
}
```