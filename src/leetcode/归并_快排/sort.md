由于排序需要用到交换，定义交换数组中a，b位置的swap方法：
```java
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
```

## 选择排序
将最小元素与未排序的第一个元素交换。
```java
    public int[] selectionsort(int[] arrays){
        for(int i = 0; i < arrays.length; i ++){
            int min = i;
            for(int j = i ; j < arrays.length;j++){
                if(arrays[j] < arrays[min]){
                    min = j;
                }
            }

            if(min != i){
                swap(arrays,min,i);
            }
        }
        return arrays;
    }
```

时间复杂度N^2，空间复杂度O(1)，非稳定排序。

## 快速排序
快速排序每次选择一个标准pivot，将比pivot小的都放到左边，大的放到右边，递归排序完成整个数组。
```java
    public int[] quicksort(int[] nums){
        int l = 0;
        int r = nums.length-1;
        return qs(nums,l,r);
    }

    public int[] qs(int[] nums, int l, int r){
        if(l < r){
            //递归
            int position = partition(nums, l, r);
            qs(nums,l,position -1);
            qs(nums,position+1,r);
        }
        return nums;
    }

    //选择左边第一个值为pivot，交换至左小右大
    public int partition(int[] nums, int l , int r){
        int pivot = l;
        int index = pivot + 1;
        for(int i = index ; i <= r; i ++){
            if(nums[i] < nums[pivot]){
                swap(nums,i,index);
                index ++;
            }
        }
        //最后将nums[pivot]放到正确的位置 
        swap(nums,pivot,index - 1);
        return index -1;
    }
```
时间复杂度O(NlogN),空间复杂度使用了递归，O(logN)。非稳定排序

## 归并排序
归并排序的思想是将数组分成两部分，分别进行排序，然后归并起来。

算法步骤 ：
1. 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
2. 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
3. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
4. 重复步骤 3 直到某一指针达到序列尾；
5. 将另一序列剩下的所有元素直接复制到合并序列尾。

```java
    public int[] mergesort(int[] nums){
        //只剩下单个元素就跳出。
        if(nums.length <2){
            return nums;
        }
        int n = nums.length;
        int mid = n/2;
        //找到中点，不断二分。
        //copyOfRange范围：左闭右开
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
```

## 堆排序
```java
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
    
    public static void adjustHeap(int[] arr,int i,int length){
        //由于下标从0开始，左节点2n+1，右节点2n+2
        while(2 * i + 1 < length ){
            int j = 2 * i +1;
            if( j + 1< length && arr[j] < arr[j+1]){
                j ++;
            }
            if(arr[i] > arr[j]){
                break;
            }
            //将arr[i]下沉至合理的位置
            swap(arr,i,j);
            i = j;
        }
    }
```
堆排序时间复杂度NlogN，空间复杂度O(1)。

为什么堆排序时间空间复杂度都很好却很少使用呢？

堆排序无法利用局部性原理进行缓存，也就是数组元素很少和相邻的元素进行比较和交换。多次的读写下反而慢了。

