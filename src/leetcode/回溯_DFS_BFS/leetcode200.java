package leetcode.回溯_DFS_BFS;
//岛屿数量，使用dfs算法。第一种方法使用了visited矩阵。第二种用变0
//总结只用一次可以用0.不止一次用visited
public class leetcode200 {
    public int numIslands1(char[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int num=0;
        boolean[][] visited=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'&&!visited[i][j]){
                    num++;
                    dfs(grid,visited,i,j);
                }
            }
        }
        return num;
    }
    public void dfs(char[][] grid,boolean[][] visited,int a,int b){
        int m=grid.length;
        int n=grid[0].length;
        if(a<0 || a>m-1 || b<0 || b>n-1 || visited[a][b] || grid[a][b]=='0'){
            return;
        }
        visited[a][b]=true;
        dfs(grid,visited,a-1,b);
        dfs(grid,visited,a+1,b);
        dfs(grid,visited,a,b-1);
        dfs(grid,visited,a,b+1);
    }

    public int numIslands(char[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int num=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    num++;
                    dfs(grid,i,j);
                }
            }
        }
        return num;
    }
    public void dfs(char[][] grid,int a,int b){
        int m=grid.length;
        int n=grid[0].length;
        if(a<0 || a>m-1 || b<0 || b>n-1 || grid[a][b]=='0'){
            return;
        }
        grid[a][b]='0';
        dfs(grid,a-1,b);
        dfs(grid,a+1,b);
        dfs(grid,a,b-1);
        dfs(grid,a,b+1);
    }
}
