## leetcode33

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

思路：
1. 二分查找nums中第一个小于nums[0]的位置，记l
2. 比较target与nums[0]的大小
    * 如果大于，二分查找nums的下标0到l-1位置
    * 如果小于，二分查找nums的下标l到n-1的位置


```java
class Solution {
    //二分查找一个有序数组中是否有target
    public int check(int[] nums, int l, int r,int target){
        while(l <= r){
            int mid = l + (r-l)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                l = mid + 1;
            }else{
                r= mid-1;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int l = 0; 
        int r = nums.length-1;
        //这里视为左边界问题。找到最左边第一个小于nums[0]的位置
        //当做右边界处理也可以的
        while(l <= r){
            int mid = l + (r-l)/2;
            if(nums[mid] >= nums[0]){
                l =mid + 1;
            }else{
                r = mid -1;
            }
        }
        if(target >= nums[0]){
            return check(nums,0,l-1,target);
        }else{
            return check(nums,l,nums.length-1,target);
        }
    }
}
```

## leetocde81
已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。

给你旋转后的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。

和上一题很像，思路:
1. 去除数组最后所有与nums[0]相同的元素
2. 二分查找nums中第一个小于nums[0]的位置，记l
3. 比较target与nums[0]的大小
    * 如果大于，二分查找nums的下标0到l-1位置
    * 如果小于，二分查找nums的下标l到n-1的位置

```java
class Solution {
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
        //去除末尾与nums[0]相同的数
        while(las > 0 && nums[las] == nums[0]){
            las --;
        }
        if(nums[las] >= nums[0]){
            return check(0,las,nums,target);
        }
        int l = 0;int r = las;
        //查找第一个小于nums[0]的位置
        while(l <= r){
            int mid = (l+r)/2;
            if(nums[mid] >= nums[0]){
                l = mid + 1;
            }else{
                r = mid-1;
            }
        }
        if(target >= nums[0]){
            return check(0,l-1,nums,target);
        }else {
            return check(l,las,nums,target);
        }
    }
}
```


