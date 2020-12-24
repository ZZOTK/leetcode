package leetcode.动态规划;
//给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

//类似走楼梯的动态规划问题。
public class leetcode377 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i = 1; i <= target; i ++){
            for( int num : nums){
                if(i >= num){
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
