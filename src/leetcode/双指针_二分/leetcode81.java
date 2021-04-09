package leetcode.双指针_二分;

public class leetcode81 {
    public boolean check(int l, int r, int[] nums, int target){
        int pre = l;
        int las= r;
        while(pre <= las){
            int mid = (pre + las)/2;
            if(nums[mid] == target){
                return true;
            }
            if(nums[mid] > target){
                las = mid-1;
            }else{
                pre = mid + 1;
            }
        }
        return false;
    }
    public boolean search(int[] nums, int target) {
        int las = nums.length -1 ;
        //去除末尾相同的
        while(las > 0 && nums[las] == nums[0]){
            las --;
        }
        if(nums[las] >= nums[0]){
            return check(0,las,nums,target);
        }
        //找到最小的点
        int l = 0;int r = las;
        while(l < r){
            int mid = (l+r)/2;
            if(nums[mid] >= nums[0]){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        //前后两端二分查找target
        if(target >= nums[0]){
            return check(0,l-1,nums,target);
        }else {
            return check(l,las,nums,target);
        }
    }
}
