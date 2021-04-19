import java.util.*;

public class Main {
    public int minJumps(int[] nums) {
        int n = nums.length;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < n; i ++){
            for(int j = i; j <= nums[i] + i && j < n; j ++){
                dp[j] = Math.min(dp[i] + 1,dp[j]);
            }
        }
        return dp[n-1];
    }
}
