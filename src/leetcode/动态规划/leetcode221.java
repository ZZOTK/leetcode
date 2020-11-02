package leetcode.动态规划;
//0和1的二维矩阵中求只包含1的最大正方形面积
public class leetcode221 {
    public int maximalSquare(char[][] matrix) {
        int maxs=0;
        if(matrix.length==0||matrix[0].length==0||matrix==null){
            return 0;
        }

        int n=matrix.length;
        int m=matrix[0].length;
        int[][] dp=new int[n][m];

        //dp[i][j]表示以ij为右下角的正方形边长。
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]=='1'){
                    if(i==0||j==0){
                        dp[i][j]=1;
                    }else{
                        dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    }
                }
                maxs=Math.max(maxs,dp[i][j]);
            }
        }
        return maxs*maxs;

    }
}
