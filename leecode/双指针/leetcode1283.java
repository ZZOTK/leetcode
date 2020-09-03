package 双指针;

//双指针实现二分查找//

public class leetcode1283 {
    public int smallestDivisor(int[] nums, int threshold) {
        int maxi=0;
        for(int i=0;i<nums.length;i++){
            maxi=Math.max(maxi,nums[i]);
        }
        //最大值应该不超过数组最大值
        int start=1; int end=maxi;int ans=0;
        while(start<=end)//小于等于，不能是小于
        {
            int mid=(start+end)/2;
            if(qiu(nums,mid)>threshold){
                start=mid+1;
            }
            else{
                end=mid-1;
                ans=mid;
            }
        }//二分查找过程
        return ans;
    }
    public static int qiu(int[] nums,int j){
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=quzheng(nums[i],j);
        }
        return sum;
    }
    public static int quzheng(int n,int j){
        if(n%j==0) return n/j;
        else return n/j+1;
    }
}
