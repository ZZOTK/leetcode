package 动态规划;

public class leetcode53 {
    public int maxSubArray1(int[] nums) {
        int[] ans=new int[nums.length+1];
        ans[0]=nums[0];
        int max=nums[0];
        for(int i=1;i<nums.length;i++){//从一开始，免去很多麻烦。相比于0即避免出现i+1. i-1会比i+1好处理
            if(ans[i-1]>=0) {
                ans[i]=ans[i-1]+nums[i];
                max=Math.max(max,ans[i]);
            }
            else {
                ans[i]=nums[i];
                max=Math.max(max,ans[i]);
            }
        }
        return max;
    }

    public static int maxSubArray2(int[] A) //[-2,1,-3,4,-1,2,1,-5,4]
    {
        int max = A[0], maxEndingHere = A[0];
        for (int i = 1; i < A.length; ++i)
        {
            maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);//无需讨论是否大于0.难写
            // A  =            [-2,1,-3,4,-1,2,1,-5,4]
            // maxEndingHere = [-2,1,-2,4, 3,5,6, 1,5]
            max = Math.max(max, maxEndingHere);  // update max
        }
        return max;
    }
}
