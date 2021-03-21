import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class solution {
    public int splitArray(int[] nums, int m) {
        int sum = 0;
        int max = 0;
        for(int num :nums){
            sum += num;
            max = Math.max(num,max);
        }
        int l = max;
        int r = sum;
        while(l <= r){
            int mid = l + (r-l)/2;
            int re = check(nums,mid);
            if(re >= m){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return r;
    }

    public int check(int[] nums,int mid){
        int res = 0;
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


//    public static void main(String[] args) {
//        double d = 3.1415926;
//        System.out.println(String.format("%.2f", d));
//    }
}
