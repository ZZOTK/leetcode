
import leetcode.剑指offer.offer_68_2;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class solution {
    public int reversePairs(int[] nums) {
        int n  = nums.length;
        if(n == 0){
            return 0;
        }
        int[] temp = new int[n];
        return  reverse(nums,0,n-1,temp);

    }

    public int reverse(int[] nums, int left, int right,int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftpairs = reverse(nums, left, mid,temp);
        int rightpairs = reverse(nums, mid + 1, right,temp);

        if (nums[mid] <= nums[mid + 1]) {
            return leftpairs + rightpairs;
        }

        int crosspairs =  count(nums, left,mid, right,temp);
        //递归的思路
        return leftpairs + rightpairs + crosspairs ;
    }

    //将两个有序数组合并成一个
    public int count(int[] nums, int left, int mid,int right,int[] temp) {
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
                nums[index] = temp[j++];
                res += (mid - i + 1);
            }else{
                nums[index] = temp[i++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,5,6,4};
        solution a = new solution();
        int an = a.reversePairs(nums);
        System.out.println(an);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
