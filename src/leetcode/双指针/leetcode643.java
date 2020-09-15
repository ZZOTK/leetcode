package leetcode.双指针;

//双指针滑窗，重要！

public class leetcode643 {
    public double findMaxAverage(int[] nums, int k) {
        int i=0;
        int j=k-1;
        double sum=0;
        for(int l=0;l<=k-1;l++){
            sum+=nums[l];
        }
        double max=sum;
        //注意为length-1
        while(j<nums.length-1){
            i++;
            j++;
            sum=sum-nums[i-1]+nums[j];
            max=Math.max(sum,max);
        }
        return max/k;
    }
}
