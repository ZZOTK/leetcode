package leetcode.归并_快排;

//希尔排序，也称递减增量排序算法，是插入排序的一种更高效的改进版本。但希尔排序是非稳定排序算法。
//希尔排序是基于插入排序的以下两点性质而提出改进方法的：
//插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率；
//但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位；
//希尔排序的基本思想是：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。

public class shellSort {
    public int[] shellsort(int[] nums){
        int gap = 1;
        while(gap < nums.length/3){
            gap = gap *3 +1;
        }
        while(gap > 0){
            for( int i = gap; i < nums.length; i ++){
                int temp = nums[i];
                int j = i -gap;
                while(j >= 0 && nums[j] >temp){
                    nums[j + gap] = nums[j];
                    j -= gap;
                }
                nums[j +gap] = temp;
            }
            gap = gap/3;
        }
        return nums;
    }

    public static void main(String[] args) {
        shellSort a = new shellSort();
        int[] nums = new int[]{1,3,5,2,6,4,8,7};
        int[] res = a.shellsort(nums);
        for(int i : res){
            System.out.println(i);
        }
    }

}
