package leetcode.动态规划;
//状态转移。注意Math.max
public class leetcode309 {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if(n<=1){
            return 0;
        }
        int[][] dp=new int[3][n+2];
        dp[0][0]=0;
        dp[1][0]=-prices[0];
        dp[2][0]=0;
        for(int i=1;i<n;i++){
            dp[0][i]=Math.max(dp[0][i-1],dp[1][i-1]+prices[i]);
            dp[1][i]=Math.max(dp[1][i-1],dp[2][i-1]-prices[i]);
            dp[2][i]=dp[0][i-1];
        }
        return Math.max(dp[2][n-1],dp[0][n-1]);
    }
}
