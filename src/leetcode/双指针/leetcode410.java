package leetcode.双指针;
//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
//
//设计一个算法使得这 m 个子数组各自和的最大值最小。
public class leetcode410 {
    public int splitArray(int[] nums, int m) {
        int sum = 0;
        int max = 0;
        for(int num :nums){
            sum += num;
            max = Math.max(num,max);
        }
        int l = max;
        int r = sum;
        while(l <= r){
            int mid = l + (r-l)/2;
            int re = check(nums,mid);
            if(m <re){
                l = mid + 1;
            }else{
                r = mid - 1 ;
            }
        }
        return l;
    }

    public int check(int[] nums,int mid){
        int res = 1;
        int temp = 0;
        for(int i= 0 ; i< nums.length; i ++){
            if(nums[i] +temp >mid){
                res++;
                temp = nums[i];
            }else{
                temp += nums[i];
            }
        }
        return res;
    }
}
