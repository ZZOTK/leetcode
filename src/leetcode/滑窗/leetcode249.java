package leetcode.滑窗;
//求滑窗中的最大值
//定义一个maxid变量记录max 的位置，再与遍历的位置比较。
public class leetcode249 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k==1){return nums;}
        if(nums == null || nums.length == 0){
            return new int[0];
        }
        int n=nums.length;
        int max=Integer.MIN_VALUE;
        int maxid=-1;
        int[] ans=new int[n-k+1];
        for(int j=0;j<n-k+1;j++){
            //如果遍历位置大于maxid，则遍历滑窗找最大值
            if(j>=maxid){
                max=nums[j];
                maxid=j;
                for(int i=j;i<j+k;i++){
                    if(nums[i]>max){
                        max=nums[i];
                        maxid=i;
                    }
                }
            }else{
                //不然只需要新滑窗的最后一位与当前的max比较
                if(nums[j+k-1]>max){
                    max=nums[j+k-1];
                    maxid=j+k-1;
                }
            }
            ans[j]=max;
        }
        return ans;
    }
}
