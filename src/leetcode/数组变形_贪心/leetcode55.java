package leetcode.数组变形_贪心;

public class leetcode55 {
    public boolean canJump(int[] nums) {
        int n=nums.length;
        int temp=0;
        for (int i=0;i<n;i++){
            //temp记录能走到的最大值
            if (i<=temp){
                temp=Math.max(temp,nums[i]+i);
            }
            //temp超过数组最大索引n-1则返回true
            if (temp>=n-1){
                return true;
            }
        }
        return false;
    }
}
