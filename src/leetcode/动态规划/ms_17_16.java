package leetcode.动态规划;
//给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
//dp[i][0] 表示：区间 [0，i] 里接受预约请求，并且下标为 i 的这一天不接受预约的最大时长；
//dp[i][1] 表示：区间 [0，i] 里接受预约请求，并且下标为 i 的这一天接受预约的最大时长。
//今天不接受预约：或者是昨天不接受预约，或者是昨天接受了预约，取二者最大值，即：dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])；
//今天接受预约：只需要从昨天不接受预约转移而来，加上今天的时常，即：dp[i][1] = dp[i - 1][0] + nums[i]。


public class ms_17_16 {
    public int massage(int[] nums) {
        int n=nums.length;
        if(n==0) return 0;
        int[][] dp=new int[n][2];
        dp[0][0]=0;
        dp[0][1]=nums[0];
        for(int i=1;i<n;i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1]=dp[i-1][0]+nums[i];
        }
        return Math.max(dp[n-1][0],dp[n-1][1]);
    }

    //压缩空间。不必要一个数组。维护变量即可。
    public int massage1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int dp0 = 0, dp1 = nums[0];

        for (int i = 1; i < n; ++i){
            int tdp0 = Math.max(dp0, dp1); // 计算 dp[i][0]
            int tdp1 = dp0 + nums[i]; // 计算 dp[i][1]

            dp0 = tdp0; // 用 dp[i][0] 更新 dp_0
            dp1 = tdp1; // 用 dp[i][1] 更新 dp_1
        }
        return Math.max(dp0, dp1);
    }

}
