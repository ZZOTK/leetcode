package leetcode.回溯_DFS_BFS;
//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//注意重复数字，类似：leetcode90
public class leetcode47 {
    List<List<Integer>> ans;
    Deque<Integer> path;
    public List<List<Integer>> permuteUnique(int[] nums) {
        ans = new LinkedList<>();
        path = new LinkedList<>();
        int n = nums.length;
        boolean[] vis = new boolean[n];
        //有重复数字的要先sort
        Arrays.sort(nums);
        backtrack(nums,vis,0);
        return ans;
    }
    public void backtrack(int[] nums, boolean[] vis, int len){
        if(len == nums.length){
            ans.add(new LinkedList(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(vis[i]){
                continue;
            }
            //这个!vis[i -1]理解。
            //比如{1，1，2}，那么第一层循环将只有1，2
            if(i > 0 && nums[i] == nums[i - 1] && !vis[i -1 ] ){
                continue;
            }
            path.addFirst(nums[i]);
            vis[i] = true;
            backtrack(nums,vis,len + 1);
            vis[i] = false;
            path.pollFirst();
        }
    }
}
