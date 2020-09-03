package 动态规划;

public class leetcode300 {
    public int lengthOfLIS(int[] nums) {//自己写的版本,很多不足
        int[] ans=new int[nums.length+1];
        int max=1;
        for(int a=0;a<ans.length;a++){
            ans[a]=1;
        }
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    ans[i]=Math.max(ans[i],ans[j]+1);
                    max=Math.max(max,ans[j]+1);
                }
            }
        }
        return max;
    }

    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);//maxval找出各个dp[j]中的最大值。
                }
            }
            dp[i] = maxval + 1;//利用maxval求dp[i]
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

}
