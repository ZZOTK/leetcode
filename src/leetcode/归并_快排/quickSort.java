package leetcode.归并_快排;

import java.util.Arrays;
//从数列中挑出一个元素，称为 “基准”（pivot）;
//重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
//递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
//递归的最底部情形，是数列的大小是零或一，也就是永远都已经被排序好了。虽然一直递归下去，但是这个算法总会退出，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。

public class quickSort {
    public int[] quicksort(int[] nums){
        int l = 0;
        int r = nums.length-1;
        return qs(nums,l,r);
    }

    public int[] qs(int[] nums, int l, int r){
        if(l < r){
            int position = partition(nums, l, r);
            qs(nums,l,position -1);
            qs(nums,position+1,r);
        }
        return nums;
    }

    public int partition(int[] nums, int l , int r){
        int pivot = l;
        int index = pivot + 1;
        for(int i = index ; i <= r; i ++){
            if(nums[i] < nums[pivot]){
                swap(nums,i,index);
                index ++;
            }
        }
        swap(nums,pivot,index - 1);
        return index -1;
    }

    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2,6,4,8,7};
        quickSort a = new quickSort();
        int[] ans = a.quicksort(nums);
        for(int num:ans){
            System.out.println(num);
        }
    }
}
