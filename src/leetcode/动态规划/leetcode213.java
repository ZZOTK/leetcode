package leetcode.动态规划;
//打家劫舍Ⅱ
public class leetcode213 {
    public int rob(int[] nums) {
        int n=nums.length;
        if(n==0){return 0;}
        if(n==1){return nums[0];}
        if(n==2){return Math.max(nums[0],nums[1]);}
        int[] dp=new int[n];
        dp[0]=nums[0];dp[1]=Math.max(dp[0],nums[1]);
        for(int i=2;i<=n-2;i++){
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        int max=0;
        max=Math.max(dp[n-3],dp[n-2]);
        dp[0]=0;
        dp[1]=nums[1];
        for(int i=2;i<=n-1;i++){
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        max=Math.max(dp[n-1],max);
        return max;
    }
}
