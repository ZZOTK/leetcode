package leetcode.回溯_DFS_BFS;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
//回溯算法全排列
//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
public class leetcode46 {
    List<List<Integer>> ans;
    public List<List<Integer>> permute(int[] nums) {
        ans = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        backtack(nums,path);
        return ans;
    }
    public void backtack(int[] nums, Deque<Integer> path){
        if(path.size() == nums.length){
            ans.add(new LinkedList<>(path));
            return;
        }
        for(int i = 0; i< nums.length; i ++){
            //有重复的剪枝
            if(path.contains(nums[i])){
                continue;
            }
            path.addFirst(nums[i]);
            backtack(nums,path);
            path.removeFirst();
        }
    }
}
