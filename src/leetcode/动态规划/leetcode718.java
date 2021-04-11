package leetcode.动态规划;
//最长公共子串
public class leetcode718 {
    public int findLength(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int[][] dp = new int[n][m];
        for (int i= 0; i<m; i ++){
            if(A[0] == B[i]){
                dp[0][i] = 1;
            }
        }
        for (int i= 0; i<n; i ++){
            if(B[0] == A[i]){
                dp[i][0] = 1;
            }
        }
        int max = 0;
        for(int i = 1; i < n; i ++){
            for(int j = 1; j <m; j ++){
                if(A[i] == B[j]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max= Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }
}
