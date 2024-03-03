package leetcode.动态规划;

public class leetcode673 {
    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] cnt = new int[nums.length];
        int ans = 0;
        int maxlen = 0;
        for(int i = 0; i < nums.length; i++){
            dp[i] = 1;
            cnt[i] = 1;
            for(int j = 0; j <i ;j++){
                if(nums[j] < nums[i]){
                    if(dp[j] +1 > dp[i]){
                        dp[i] = dp[j] +1;
                        cnt[i] = cnt[j];
                    }else if(dp[j] +1 == dp[i]){
                        dp[i] = dp[j] +1;
                        cnt[i] += cnt[j];
                    }
                }
            }
            if(dp[i] > maxlen){
                maxlen = dp[i];
                ans = cnt[i];
            }else if(dp[i] == maxlen){
                ans += cnt[i];
            }
        }
        return ans;
    }
}
