package leetcode.动态规划;
//给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
//转换为01背包问题。即装满一个容量为0.5*sum的背包

public class leetcode416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int target = 0;
        for ( int num : nums) {
            sum += num ;
        }
        //奇数直接返回
        if ( sum % 2 == 1){
            return false ;
        }else {
            target = sum / 2 ;
        }
        int len = nums.length ;
        boolean[][] dp = new boolean[len + 1][target + 1];
        for ( int i = 0; i < len + 1 ; i ++ ){
            dp[i][0] = true ;
        }
        for ( int i = 0 ; i < len ; i ++){
            for (int j = 1; j < target + 1 ; j ++ ){
                if (nums[i] > j){
                    dp[i + 1][j] = dp[i][j];
                }else {
                    dp[i+1][j] = dp[i][j] || dp[i][j - nums[i]];
                }
            }
        }
        return dp[len][target];
    }


    //压缩为一维的dp
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        int target = 0;
        for ( int num : nums) {
            sum += num ;
        }
        if ( sum % 2 == 1){
            return false ;
        }else {
            target = sum / 2 ;
        }
        int len = nums.length ;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true ;
        for ( int i = 0 ; i < len ; i ++){
            for (int j = target; j >= nums[i]; j -- ){
                dp[j] = dp[j] || dp[j - nums[i]];
                if(dp[target]){
                    return true;
                }
            }
        }
        return dp[target];
    }
}
