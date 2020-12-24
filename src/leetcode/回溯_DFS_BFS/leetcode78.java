package leetcode.回溯_DFS_BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//说明：解集不能包含重复的子集。

public class leetcode78 {
    public List<List<Integer>> subsets(int[] nums) {
        int len=nums.length;
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> ans=new ArrayList<>();
        if (nums.length==0){
            return ans;
        }
        for (int i=1;i<=nums.length;i++){
            backtrack(nums,path,ans,0,i);
        }
        ans.add(new ArrayList<>());
        return ans;

    }
    public void backtrack(int[] nums,Deque<Integer> path,List<List<Integer>> ans,int begin,int i ) {
        if (path.size() == i) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = begin; j < nums.length; j++) {
            path.addLast(nums[j]);
            //注意理解j+1. 和leetcode39有相似的地方。
            backtrack(nums, path, ans, j + 1, i);
            path.removeLast();
        }
    }
}
