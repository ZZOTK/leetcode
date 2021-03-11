## 剑指offer51
> 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

分析：暴力法依次遍历肯定是可以的。不过时间复杂度很高
* 如果将一个数组拆分成俩个有序数组，求逆序对可以求吗？
    * 双指针遍历两个有序数组。后一个数组中数比前一个小，就加逆序对个数。
* 如何将数组分成有序数组 ： 归并排序。
* 注意： 数组中可能有重复元素。 
* 具体细节见代码注释

参考代码： 

```java
public class offer_51 {
    public int reversePairs(int[] nums) {
        int n  = nums.length;
        if(n == 0){
            return 0;
        }
        //建立一个temp数组
        int[] temp = new int[n];
        return  reverse(nums,0,n-1,temp);
    }

    public int reverse(int[] nums, int left, int right,int[] temp) {
        //归并排序到各一个跳出，由于我们是统计逆序对，到一定是0个再跳出
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftpairs = reverse(nums, left, mid,temp);
        int rightpairs = reverse(nums, mid + 1, right,temp);

        //注意等于号，可能有重复的数
        if (nums[mid] <= nums[mid + 1]) {
            return leftpairs + rightpairs;
        }

        int crosspairs =  count(nums, left,mid, right,temp);
        //递归的思路
        return leftpairs + rightpairs + crosspairs ;
    }

    //将两个有序数组合并成一个
    public int count(int[] nums, int left, int mid,int right,int[] temp) {
        //temp保存left到right
        for(int i = left; i <=right; i ++){
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid+1;
        int res = 0;
        for(int index = left; index <= right; index++){
            if(i == mid + 1){
                nums[index] = temp[j++];
            }else if(j == right + 1){
                nums[index] = temp[i++];
            }else if(temp[i] > temp[j]){
                //此时的j，小于当前的i到mid所有的数。所以逆序对有（mid - i + 1）个
                //如果temp[i] == temp[j],截至到当前是不存在逆序对的。所以这里不带等于号
                nums[index] = temp[j++];
                res += (mid - i + 1);
            }else{
                nums[index] = temp[i++];
            }
        }
        return res;
    }
}
```
  
