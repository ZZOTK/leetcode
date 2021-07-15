package leetcode.剑指offer;
import java.util.*;

//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
public class offer_59_1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n == 0){
            return new int[0];
        }
        int[] ans = new int[n-k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 == o2){
                    //值相同序号小的在前
                    return o1-o2;
                }else{
                    //按值从大到小
                    return nums[o2] - nums[o1];
                }
            }
        });
        for(int i = 0 ; i < k; i ++){
            pq.add(i);
        }
        ans[0] = nums[pq.peek()];
        for(int i = k ; i< n; i ++){
            //条件不为空
            //大于当前的最大值或者当前的最大值已经过期
            while(!pq.isEmpty() && (nums[i] > nums[pq.peek()] || pq.peek() <= i-k)){
                pq.poll();
            }
            pq.add(i);
            ans[i-k+1] = nums[pq.peek()];
        }
        return ans;
    }
}
