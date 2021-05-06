## leetcode410

> 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。 设计一个算法使得这 m 个子数组各自和的最大值最小。

PS : 这是3.20美团笔试遇到的题。当时就是一个不会啊。。

PS:「使……最大值尽可能小」这种题，一般可以用到二分查找找边界。

什么叫二分查找的判定问题呢？如果我们需要求k使得满足条件d，当我们确定一个k时，可以找到k对应的条件与d的大小关系。
利用这个关系，用二分查找查找k的范围，找到最大或最小的k。

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

## leetcode1011
> 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
> 
> 传送带上的第 i个包裹的重量为weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
> 
> 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。

同样是一个二分判定的问题。注意这里k的取值范围，是（max，sum）.

```java
    public int shipWithinDays(int[] weights, int D) {
        int r = 0;
        int l = 0;
        for(int weight : weights){
            r += weight;
            l = Math.max(l,weight);
        }

        while(l <= r){
            int mid = l + (r-l)/2;
            int temp = config(weights,mid);
            if(temp > D){
                l = mid + 1;
            }else{
                r = mid -1;
            }
        }
        return l;
    }

    public int config(int[] weights, int k){
        int sum = 0;
        int count = 1;
        for(int weight : weights){
            if(sum + weight > k){
                count ++;
                sum = weight;
            }else{
                sum += weight;
            }
        }
        return count;
    }
```



