package leetcode.剑指offer;
//请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
//
//visited矩阵做法，还有一种见leetcode79
public class offer_12 {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0 ; i < m ; i ++){
            for(int j = 0 ;j < n; j ++){
                if(board[i][j] == word.charAt(0)){
                    if(dfs(board,word,visited,i,j,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board,String word, boolean[][] visited, int i, int j, int k){
        if( k == word.length()){
            return true;
        }
        if( i < 0 || i >= board.length || j <0 || j >= board[0].length||visited[i][j] == true){
            return false;
        }
        if (board[i][j]!=word.charAt(k)){
            return false;
        }
        visited[i][j] = true;
        boolean res = dfs(board,word,visited,i-1,j,k + 1) ||
                dfs(board,word,visited,i+1,j,k + 1)||
                dfs(board,word,visited,i,j-1,k + 1)||
                dfs(board,word,visited,i,j+1,k + 1);
        visited[i][j] = false;
        return res;
    }
}
