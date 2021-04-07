package leetcode.动态规划;
//给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
public class leetcode1262 {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n+1][3];
        //这里用最小值表示，让第一个余数为1和2的数结果是正确的
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            if (nums[i-1] % 3 == 0) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][0] + nums[i-1]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][1] + nums[i-1]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][2] + nums[i-1]);
            } else if (nums[i-1] % 3 == 1) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] + nums[i-1]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + nums[i-1]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + nums[i-1]);
            } else if (nums[i-1] % 3 == 2) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + nums[i-1]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][2] + nums[i-1]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][0] + nums[i-1]);
            }
        }
        return dp[n][0];
    }

    //补充：把3换成k
    private int maxSumDivN(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k];
        for (int j = 0; j < k; ++j) {
            dp[0][j] = 0;
        }
        boolean[] flag = new boolean[k];
        for (int i = 0; i < n; ++i) {
            int num = nums[i];
            //            int r = num % k;
            for (int j = 0; j < k; ++j) {
                int sum = dp[i][j] + num;
                int ii = sum % k;
                flag[ii] = true;
                dp[i + 1][ii] = Math.max(sum, Math.max(dp[i + 1][ii], dp[i][ii]));
            }
            for (int j = 0; j < k; ++j) {
                if (flag[j]) flag[j] = false;
                else dp[i + 1][j] = dp[i][j];
            }
        }
        return dp[n][0];
    }
}
