package leetcode.动态规划;
//求乘积最大连续子数组，使用动态规划
//不需区分正负，定义两个状态为min和max
public class leetcode152 {
    public int maxProduct(int[] nums) {
        int n=nums.length;
        int[][] dp=new int[2][n];
        dp[0][0]=nums[0];
        dp[1][0]=nums[0];
        int max=nums[0];
        for(int i=1;i<n;i++){
            dp[0][i]=Math.max(dp[0][i-1]*nums[i],Math.max(nums[i],dp[1][i-1]*nums[i]));
            dp[1][i]=Math.min(dp[0][i-1]*nums[i],Math.min(nums[i],dp[1][i-1]*nums[i]));
            max=Math.max(max,dp[0][i]);
        }
        return max;

    }
}
