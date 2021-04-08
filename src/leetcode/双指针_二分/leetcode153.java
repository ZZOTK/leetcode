package leetcode.双指针_二分;
//给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
public class leetcode153 {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n- 1;
        while(l<r){
            int mid = l + (r-l)/2;
            //二分
            if(nums[mid] < nums[r]){
                r = mid;
            }else{
                l = mid +1;
            }
        }
        return nums[l];
    }
}
