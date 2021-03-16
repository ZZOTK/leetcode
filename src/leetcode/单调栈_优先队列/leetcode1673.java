package leetcode.单调栈_优先队列;
//1673.找出最有竞争力的子序列
//给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
//数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
//在子序列a 和子序列b 第一个不相同的位置上，如果a中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。
// 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上，4 小于 5 。

import java.util.Deque;
import java.util.LinkedList;

public class leetcode1673 {
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        int m = n - k;
        int[] ans = new int[k];
        Deque<Integer> path = new LinkedList<>();
        for(int num : nums){
            while(!path.isEmpty() && num < path.peek() && m > 0){
                path.pop();
                m --;
            }
            path.push(num);
        }
        while(m > 0){
            path.pop();
            m --;
        }
        int index = 0;
        while (!path.isEmpty()){
            ans[index ++] = path.pollLast();
        }
        return ans;
    }
}
