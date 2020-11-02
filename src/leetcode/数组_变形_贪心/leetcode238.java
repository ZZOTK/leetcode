package leetcode.数组_变形_贪心;
//求出数组中除了该元素外其他元素的乘积
//不能使用除法
//思路:创建left，right两个数组，分布记录i位置左右所有数的成绩，再相乘。
public class leetcode238 {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] left=new int[n];
        int[] right=new int[n];
        left[0]=1;
        right[n-1]=1;
        int[] ans=new int[n];
        for(int i=1;i<n;i++){
            left[i]=nums[i-1]*left[i-1];
        }
        for(int i=n-2;i>=0;i--){
            right[i]=nums[i+1]*right[i+1];
        }
        for(int i=0;i<n;i++){
            ans[i]=left[i]*right[i];
        }
        return ans;
    }
}
