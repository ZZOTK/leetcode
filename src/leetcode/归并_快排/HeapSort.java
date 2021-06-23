package leetcode.归并_快排;

import java.util.Arrays;

public class HeapSort {

    public static void main(String []args){
        int []arr = {7,6,7,11,5,12,3,0,1};
        System.out.println("排序前："+Arrays.toString(arr));
        sort(arr);
        System.out.println("排序前："+ Arrays.toString(arr));
    }

    public static void sort(int []arr){
        //1.构建大顶堆
        int N = arr.length - 1;
        for(int i=N/2;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr,i,N);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=N;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr,int i,int length){
        while(2 * i + 1 < length ){
            int j = 2 * i +1;
            if( j + 1< length && arr[j] < arr[j+1]){
                j ++;
            }
            if(arr[i] > arr[j]){
                break;
            }
            swap(arr,i,j);
            i = j;
        }
    }

    /**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
