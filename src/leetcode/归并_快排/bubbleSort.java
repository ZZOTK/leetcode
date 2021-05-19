package leetcode.归并_快排;

import java.util.Arrays;

public class bubbleSort {
    public int[] bubble(int[] arrs){
        int[] arr = Arrays.copyOf(arrs,arrs.length);
        int N = arr.length;
        boolean flag = false;
        for(int i = N-1 ; i > 0 && !flag ; i--){
            for(int j = 0 ; j < i; j ++){
                flag = true;
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j +1 ] = temp;
                    flag = false;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        bubbleSort a = new bubbleSort();
        int[] nums = new int[]{1,3,5,2,6,4,8,7};
        int[] res = a.bubble(nums);
        for(int i : res){
            System.out.println(i);
        }
    }
}
