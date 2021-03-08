package leetcode.归并_快排;

public class selectionSort {
    public int[] selectionsort(int[] arrays){
        for(int i = 0; i < arrays.length; i ++){
            int min = i;
            for(int j = i ; j < arrays.length;j++){
                if(arrays[j] < arrays[min]){
                    min = j;
                }
            }

            if(min != i){
                int temp = arrays[i];
                arrays[i] = arrays[min];
                arrays[min] = temp;
            }
        }
        return arrays;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2,6,4,8,7};
        selectionSort a= new selectionSort();
        a.selectionsort(nums);
        for(int num:nums){
            System.out.println(num);
        }
    }
}
