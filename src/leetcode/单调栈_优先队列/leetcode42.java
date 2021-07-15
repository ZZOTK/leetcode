package leetcode.单调栈_优先队列;

public class leetcode42 {
    //使用两个数组l,r,分别存放每个位置左边的最高高度(包括自身)，和右边的最高高度（包括自身）
    //每个位置能接的最多雨水就是 water = min(l[i], r[i]) - height[i]
    public int trap(int[] height) {
        int n = height.length;
        if(n==0){
            return 0;
        }
        int[] left = new int[n];
        left[0] = height[0];
        int[] right = new int[n];
        right[n-1] = height[n-1];
        for(int i= 1 ; i < n; i ++){
            left[i] = Math.max(left[i-1],height[i]);
        }
        for(int i = n-2; i >=0; i--){
            right[i] = Math.max(right[i+1],height[i]);
        }
        int sum = 0;
        for(int i = 0 ; i < n; i ++){
            sum += Math.min(left[i],right[i]) -height[i];
        }
        return sum;
    }
}
