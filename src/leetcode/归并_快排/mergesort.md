归并排序是一种利用分治思想的排序方法。时间复杂度为O(nlogn)，空间复杂度为O(n)

算法步骤 ：
1. 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
2. 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
3. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
4. 重复步骤 3 直到某一指针达到序列尾；
5. 将另一序列剩下的所有元素直接复制到合并序列尾。

```java
public class mergeSort {
    public int[] mergesort(int[] nums){
        //只剩下单个元素就跳出。
        if(nums.length <2){
            return nums;
        }
        int n = nums.length;
        int mid = n/2;
        //找到中点，不断二分。
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
```