package 动态规划;

import java.util.List;

public class leetcode120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
        if(n==0) return 0;
        if(n==1) return triangle.get(0).get(0);
        int dp[][]=new int[n][n];//dp矩阵
        dp[0][0]=triangle.get(0).get(0);
        dp[1][0]=triangle.get(1).get(0)+triangle.get(0).get(0);
        dp[1][1]=triangle.get(1).get(1)+triangle.get(0).get(0);//前两行特殊，直接赋值
        for(int i=2;i<n;i++){
            for(int j=0;j<=i;j++){
                if(j==0) dp[i][j]=dp[i-1][j]+triangle.get(i).get(j);//第一列
                else if(j==i) dp[i][j]=dp[i-1][j-1]+triangle.get(i).get(j);//最后一列和第一列单独计算
                else dp[i][j]=Math.min(dp[i-1][j-1],dp[i-1][j])+triangle.get(i).get(j);
            }
        }
        int ans =dp[n-1][0];//取出最后一行，最小值为ans
        for(int i=1;i<n;i++){
            ans=Math.min(ans,dp[n-1][i]);
        }
        return ans;
    }
}
