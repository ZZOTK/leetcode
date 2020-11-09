package leetcode.动态规划;

//给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
//转换位01背包问题。即所有正的和未X，负的和为Y。有X+Y=S，X-Y=sum.则X=（S+sum)/2
//即01背包问题，和为（S+sum)/2有多少种方法
public class leetcode494 {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(S > sum){
            return 0;
        }
        int temp =sum + S;
        if(temp % 2 == 1){
            return 0;
        }
        int target = temp / 2;
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];
        //dp[0][0]为1.其实dp[i][0]都为1.每个元素是可选可不选。所以和为0至少有一种做法
        dp[0][0] = 1;
        for(int i = 1; i <= n ; i ++){
            //需要从0开始。
            for(int j = 0; j <= target; j ++){
                if(j >= nums[i-1]){
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i-1]];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    //dp压缩为一维
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(S > sum){
            return 0;
        }
        int temp =sum + S;
        if(temp % 2 == 1){
            return 0;
        }
        int target = temp / 2;
        int n = nums.length;
        int[] dp = new int [target + 1];
        dp[0] = 1;
        for(int i = 0; i < n ; i ++){
            for(int j = target; j >= nums[i]; j --){
                dp[j] += dp[j - nums[i]];

            }
        }
        return dp[target];
    }
}
