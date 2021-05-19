package leetcode.归并_快排;

public class insertSort {
    public int[] insertsort(int[] nums){
        for(int i = 1; i < nums.length; i ++){
            for(int j = i; j > 0 && nums[j] < nums[j-1]; j--){
                int temp = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = temp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2,6,4,8,7};
        insertSort a= new insertSort();
        a.insertsort(nums);
        for(int num:nums){
            System.out.println(num);
        }
    }
}
