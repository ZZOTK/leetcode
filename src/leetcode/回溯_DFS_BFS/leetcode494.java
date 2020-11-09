package leetcode.回溯_DFS_BFS;
//给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
//方法一使用回溯算法
//方法二转换为01背包问题
public class leetcode494 {
    //回溯算法
    int result = 0;
    public int findTargetSumWays(int[] nums, int S) {
        backtrack(nums, S, 0, 0);
        return result;
    }
    public void backtrack(int[] nums, int S, int i, int sum){
        //错误条件
        if(i == nums.length && sum != S){
            return;
        }
        //正确条件
        if(i == nums.length && sum == S){
            result ++;
            return;
        }
        //加号选择
        sum += nums[i];
        backtrack(nums, S, i+1, sum);
        //撤销选择
        sum -= nums[i];

        //减号选择
        sum -= nums[i];
        backtrack(nums, S , i+1, sum);
        sum += nums[i];
    }

    public int findTargetSumWays1(int[] nums, int S) {
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
        dp[0][0] = 1;
        for(int i = 1; i <= n ; i ++){
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
}
