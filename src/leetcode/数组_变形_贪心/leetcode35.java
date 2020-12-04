package leetcode.数组_变形_贪心;
//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
//你可以假设数组中无重复元素。

//二分查找
//最后判断。
public class leetcode35 {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int r = n -1;
        while(l < r){
            int mid = 1/2 * (r -l) + l;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                r = mid - 1;
            }else if(nums[mid] < target){
                l = mid + 1;
            }
        }
        //难点
        if(nums[l] < target){
            return l + 1;
        }
        return l;
    }
}
