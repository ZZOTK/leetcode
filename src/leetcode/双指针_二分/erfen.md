## leetcode410

> 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。 设计一个算法使得这 m 个子数组各自和的最大值最小。

PS : 这是3.20美团笔试遇到的题。当时就是一个不会啊。。

PS:「使……最大值尽可能小」这种题，一般可以用到二分查找找边界。

思路： 
1. m的最小值为1，此时的子数组和最大值为nums元素的和（max）。
2. m的最大值为nums.length，此时的子数组的最大值为nums中的元素最大值（min）
3. 我们找到的最大值的最小值，一定在这个最大最小值之间。此时分为m组

那么子问题就变为了：
* 假如给定一个数k，使得nums中连续子数组和不超过k，nums最少可以分为多少组？
    * 使用贪心，从第一个元素开始，使得每次向后的子数组和最大且不超过k。
    

参考代码：
```java
    public int check(int[] nums,int k){
        //res从1开始
        int res = 1;
        int temp = 0;
        //贪心
        for(int i= 0 ; i< nums.length; i ++){
            if(nums[i] +temp >k){
                res++;
                temp = nums[i];
            }else{
                temp += nums[i];
            }
        }
        //最后的分组数
        return res;
    }
```
    
* 使用二分查找查找min到max中,使得nums能分为m个子数组的 值 的左边界！

```java
public class leetcode410 {
    public int splitArray(int[] nums, int m) {
        int sum = 0;
        int max = 0;
        for(int num :nums){
            sum += num;
            max = Math.max(num,max);
        }
        //二分查找的左右边界
        int l = max;
        int r = sum;
        //二分查找的左边界。这里我们把m看做是target
        while(l <= r){
            int mid = l + (r-l)/2;
            int re = check(nums,mid);
            if(m <re){
                l = mid + 1;
            }else{
                r = mid - 1 ;
            }
        }
        return l;
    }

    public int check(int[] nums,int mid){
        //贪心求分组数
        int res = 1;
        int temp = 0;
        for(int i= 0 ; i< nums.length; i ++){
            if(nums[i] +temp >mid){
                res++;
                temp = nums[i];
            }else{
                temp += nums[i];
            }
        }
        return res;
    }
}
```