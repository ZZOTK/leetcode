package leetcode.数组_变形_贪心;

//给定一个非负整数数组，你最初位于数组的第一个位置。
//数组中的每个元素代表你在该位置可以跳跃的最大长度。
//你的目标是使用最少的跳跃次数到达数组的最后一个位置。

import java.util.Arrays;

public class leetcode45 {
    //贪心
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            //过了可以直接return
            if(maxPosition >= length -1){
                return ++steps;
            }
            //遍历到最大值
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    //动态规划的做法
    public int jumpdp(int[] nums) {
        int n = nums.length;
        if(n==1){
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < n; i ++){
            for(int j = i + 1; j <= nums[i] + i && j < n; j ++){
                dp[j] = Math.min(dp[i] + 1,dp[j]);
            }
        }
        return dp[n-1];
    }
}
