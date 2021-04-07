package leetcode.双指针_二分;

//旋转数组二分查找元素

public class leetcode33 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int l = 0;
        int r = nums.length -1 ;
        while( l <= r ){
            int mid = (l + r)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[0] <= nums[mid]){
                if(nums[0] <= target && nums[mid] > target){
                    r= mid-1;
                }else{
                    l = mid + 1;
                }
            }else{
                if(nums[len - 1]>= target && nums[mid] < target){
                    l = mid + 1;
                }else{
                    r = mid - 1;
                }
            }
        }
        return -1 ;
    }
}
