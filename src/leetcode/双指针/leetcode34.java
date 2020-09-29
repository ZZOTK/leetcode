package leetcode.双指针;

//二分查找法查询左右边界

public class leetcode34 {
    public int[] searchRange(int[] nums, int target) {
        int[] ans=new int[2];
        int first = findFirst(nums, target);
        if (first==-1){
            return new int[]{-1,-1};
        }
        int second = findSecond(nums, target);
        ans[0]=first;ans[1]=second;
        return ans;
    }

    //查询左边界
    private int findFirst(int[] nums,int target){
        int n=nums.length;
        int l=0;int r=n-1;
        while (l<=r){
            int mid=(l+r)/2;
            if (target==nums[mid]){
                r=mid-1;
            }else if (target<nums[mid]){
                r=mid-1;
            }else if (target>nums[mid]){
                l=mid+1;
            }
        }
        //判断是否成立。如果查询的数大于最大值，此时l=n，所以必须加l<n
        if (l<n&&nums[l]==target){
            return l;
        }
        return -1;
    }

    //查询右边界
    private int findSecond(int[] nums,int target){
        int n=nums.length;
        int l=0; int r=n-1;
        while(l<=r){
            int mid=(l+r)/2;
            if (target==nums[mid]){
                l=mid+1;
            }else if(target>nums[mid]){
                l=mid+1;
            }else if (target<nums[mid]){
                r=mid-1;
            }
        }
        //如果查询的数小于最小值，此时r=-1，所以必须加r>-1
        if (r>=0&&nums[r]==target){
            return r;
        }
        return -1;
    }
}
