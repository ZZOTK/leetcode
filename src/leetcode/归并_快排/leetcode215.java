package leetcode.归并_快排;

import java.util.PriorityQueue;

//在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
public class leetcode215 {




    //优先队列，即小根堆
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < nums.length; i ++){
            if(pq.size() < k){
                pq.add(nums[i]);
            }else if(nums[i] > pq.peek()){
                pq.remove();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }
}
