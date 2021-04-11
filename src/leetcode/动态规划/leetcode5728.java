package leetcode.动态规划;

public class leetcode5728 {
    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        int[][] dp=new int[n][3];
        dp[0][0]=1;
        dp[0][2]=1;

        for(int i=1;i<n;++i){
            if(obstacles[i]==1){
                dp[i][0]=Integer.MAX_VALUE-1;
                dp[i][1]=Math.min(dp[i-1][1],dp[i-1][2]+1);
                dp[i][2]=Math.min(dp[i-1][2],dp[i-1][1]+1);
            }else if(obstacles[i]==2){
                dp[i][1]=Integer.MAX_VALUE-1;
                dp[i][0]=Math.min(dp[i-1][0],dp[i-1][2]+1);
                dp[i][2]=Math.min(dp[i-1][2],dp[i-1][0]+1);
            }else if(obstacles[i]==3){
                dp[i][2]=Integer.MAX_VALUE-1;
                dp[i][0]=Math.min(dp[i-1][0],dp[i-1][1]+1);
                dp[i][1]=Math.min(dp[i-1][1],dp[i-1][0]+1);
            }else{
                dp[i][0]=Math.min(dp[i-1][0],Math.min(dp[i-1][1],dp[i-1][2])+1);
                dp[i][1]=Math.min(dp[i-1][1],Math.min(dp[i-1][0],dp[i-1][2])+1);
                dp[i][2]=Math.min(dp[i-1][2],Math.min(dp[i-1][0],dp[i-1][1])+1);
            }
        }

        return Math.min(dp[n-1][0],Math.min(dp[n-1][1],dp[n-1][2]));
    }
}
