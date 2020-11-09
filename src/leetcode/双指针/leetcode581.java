package leetcode.双指针;

import java.util.Arrays;
//给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
//直接排序，找不同
//找到第一个不是升序的数，再找到左边<=他的第一个数。同理。倒序找到第一个不是降序的数，再找到右边第一个大于他的数
public class leetcode581 {
    public int findUnsortedSubarray(int[] nums) {
        int left = 0;
        int right = 0;
        int[]  n = nums.clone();
        Arrays.sort(n);
        for(int i = 0; i < nums.length; i ++){
            if(n[i] != nums[i]){
                left = i;
                break;
            }
        }
        for(int j = nums.length - 1; j >= 0; j --){
            if(nums[j] != n[j]){
                right = j;
                break;
            }
        }
        if(right - left <= 0){
            return 0;
        }
        return (right - left + 1);
    }
}
