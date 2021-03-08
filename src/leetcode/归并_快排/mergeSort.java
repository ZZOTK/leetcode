package leetcode.归并_快排;

import java.util.Arrays;
//重要的归并排序
public class mergeSort {
    public int[] mergesort(int[] nums){
        if(nums.length <2){
            return nums;
        }
        int n = nums.length;
        int mid = n/2;
        int[] left = Arrays.copyOfRange(nums,0,mid);
        int[] right = Arrays.copyOfRange(nums,mid,n);

        //递归的思路
        return merge(mergesort(left),mergesort(right));
    }

    //将两个有序数组合并成一个
    public int[] merge(int[] left , int[] right){
        int nl = left.length;
        int nr = right.length;
        int i = 0;
        int j = 0;
        int[] ans = new int[nl + nr];
        int index = 0;
        while(i<nl && j < nr){
            if(left[i] < right[j]){
                ans[index++] = left[i];
                i ++;
            }else{
                ans[index++] = right[j];
                j++;
            }
        }
        while(i != nl){
            ans[index++] = left[i++];
        }
        while (j != nr){
            ans[index++] = right[j++];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2,6,4,8,7};
        mergeSort a = new mergeSort();
        int[] ans = a.mergesort(nums);
        for(int num : ans){
            System.out.println(num);
        }
    }
}
