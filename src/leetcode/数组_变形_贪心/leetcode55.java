package leetcode.数组_变形_贪心;

//给定一个非负整数数组，你最初位于数组的第一个位置。
//数组中的每个元素代表你在该位置可以跳跃的最大长度。
//判断你是否能够到达最后一个位置。

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
