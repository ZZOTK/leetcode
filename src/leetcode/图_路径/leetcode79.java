package leetcode.图_路径;
//dfs算法，没有使用visited矩阵。而是直接改board[i][j]的值再复原

public class leetcode79 {
    public boolean exist(char[][] board, String word) {
        int m=board.length;
        int n=board[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                //使用这个判断的原因是开始位置不确定。直接return  dfs(board,word,i,j,0)会导致只判断了第一个出现的位置那一次
                if(board[i][j]==word.charAt(0)){
                    if(dfs(board,word,i,j,0)){
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private boolean dfs(char[][] board, String world,int i,int j,int k){
        if (k==world.length()){
            return true;
        }
        if (i<0||i>=board.length||j<0||j>=board[0].length){
            return false;
        }
        if (board[i][j]!=world.charAt(k)){
            return false;
        }
        //类似回溯的写法。判断并return，再循环。
        char temp=board[i][j];
        board[i][j]='0';
        boolean res=dfs(board,world,i-1,j,k+1)||
                dfs(board,world,i,j-1,k+1)||
                dfs(board,world,i+1,j,k+1)||
                dfs(board,world,i,j+1,k+1);
        board[i][j]=temp;
        return res;
    }
}
