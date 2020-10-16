
import javax.xml.xpath.XPath;
import java.util.*;


public class solution {
    public int numIslands(char[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int num=0;
        boolean[][] visited=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                visited[i][j]=false;
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1&&visited[i][j]==false){
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
        if(a<0 || a>m-1 || b<0 || b>n-1 || visited[a][b]==true || grid[a][b]==0){
            return;
        }
        visited[a][b]=true;
        dfs(grid,visited,a-1,b);
        dfs(grid,visited,a+1,b);
        dfs(grid,visited,a,b-1);
        dfs(grid,visited,a,b+1);
    }


    public static void main(String[] args) {
        solution a=new solution();


        System.out.println();
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}