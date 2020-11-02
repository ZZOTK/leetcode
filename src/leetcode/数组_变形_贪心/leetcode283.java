package leetcode.数组_变形_贪心;
//不创建新数组，将数组中的0放到最后
//双指针，第一遍遍历把所有非零的数往前放，j记录个数，第二遍将j后面的数改为0
public class leetcode283 {
    public void moveZeroes(int[] nums) {
        int n=nums.length;
        if(n==0){return;}
        int j=0;
        for(int i=0;i<n;i++){
            if(nums[i]!=0){
                nums[j]=nums[i];
                j++;
            }
        }
        for(int i=j;i<n;i++){
            nums[i]=0;
        }
    }
}
