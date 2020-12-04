package leetcode.回溯_DFS_BFS;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//包含重复元素的去重是难点
public class leetcode90 {
    Deque<Integer> path;
    List<List<Integer>> ans;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        path = new LinkedList<>();
        ans = new LinkedList<>();
        Arrays.sort(nums);
        if (nums.length==0){
            return ans;
        }
        for (int i=1;i<=nums.length;i++){
            backtrack(nums,0,i);
        }
        ans.add(new LinkedList<>());
        return ans;

    }
    public void backtrack(int[] nums,int begin,int i ) {
        if (path.size() == i) {
            ans.add(new LinkedList<>(path));
            return;
        }
        for (int j = begin; j < nums.length; j++) {

            //这个判断达到了去重的效果
            //回溯时，由于start = j + 1，重复的位置就可以被计数。所用用之前需要排序。
            if(j > begin && nums[j] == nums[j -1]){
                continue;
            }

            path.addLast(nums[j]);
            //注意理解j+1. 和leetcode39有相似的地方。
            backtrack(nums,  j + 1, i);
            path.removeLast();
        }
    }
}
