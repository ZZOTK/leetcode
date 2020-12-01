package leetcode.剑指offer;

//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
// 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

//将大于k的格子视为障碍物，就成了经典的dfs/bfs问题。
//使用visted矩阵

public class offer_13 {
    int ans = 0;
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        dfs(m,n,visited,k,0,0);
        return ans;
    }

    public void dfs(int m, int n, boolean[][] visited, int k, int r, int l ){
        //排除各个不成立条件
        if( r < 0 || r >= m || l < 0 || l >= n || visited[r][l] || distance(r) + distance(l) > k){
            return;
        }

        ans ++;
        visited[r][l] = true;

        dfs(m,n,visited,k,r+1,l);
        dfs(m,n,visited,k,r,l+1);
    }

    public int distance(int m){
        int res = 0;
        while(m > 0){
            res += m % 10 ;
            m = m/10;
        }
        return res;
    }
}
