package leetcode.回溯_DFS_BFS;
//给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
import java.util.*;
//难点依然是去重
//类似leetcode40，不过这里不能用sort
public class leetcode491 {
    List<List<Integer>> ans;
    Deque<Integer> path;
    public List<List<Integer>> findSubsequences(int[] nums) {
        ans = new LinkedList<>();
        path = new LinkedList<>();
        backtack(nums,0,0);
        return ans;
    }
    public void backtack(int[] nums, int start, int len){
        if(len >= 2){
            ans.add(new LinkedList<>(path));
        }
        //利用set对dfs的一层树进行去重。
        //很有适用性的方法
        Set<Integer> set = new HashSet<>();
        for(int i = start; i < nums.length; i ++){
            //已经存在就去重
            if(set.contains(nums[i])){
                continue;
            }
            if(!path.isEmpty() && nums[i] < path.peekLast()){
                continue;
            }
            set.add(nums[i]);
            path.addLast(nums[i]);
            backtack(nums,i + 1, len + 1);
            path.pollLast();
        }
    }
}
