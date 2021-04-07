package leetcode.双指针_二分;

//给定一个增序排列数组 nums ，你需要在 原地 删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
//不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成

//简单思路：遍历，遇到超过两个就删除。时间复杂度高
//优化思路：利用双指针。将满足条件的值赋给前一个指针。
//这个思路在数组原地修改问题中比较常用

public class leetcode80 {
    public int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if(n <= 2){
            return n;
        }
        int l = 2;
        int r = 2;
        while(r < n){
            if(nums[l -2] != nums[r]){
                nums[l] = nums[r];
                l ++;
            }
            r++;
        }
        return l;
    }

    public int removeDuplicates(int[] nums) {
        int j = 1;
        int count = 1;
        int n = nums.length;
        //挨个遍历
        for(int i = 1 ;  i < n; i++){
            if(nums[i] == nums[i - 1]){
                count ++;
            }else{
                count = 1;
            }
            //赋值
            if(count <= 2){
                nums[j ++] = nums[i];
            }
        }
        return j;
    }
}
