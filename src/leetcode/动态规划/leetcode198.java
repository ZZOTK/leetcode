package leetcode.动态规划;
//打劫问题，求最大值。定义一个状态为max
public class leetcode198 {
    public int rob(int[] nums) {
        int n=nums.length;
        if(n==0){return 0;}
        if(n==1){return nums[0];}
        int[] dp=new int[n+1];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<n;i++){
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return Math.max(dp[n-1],dp[n-2]);
    }
}
