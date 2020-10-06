package leetcode.回溯;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
            backtrack(nums, path, ans, j + 1, i);
            path.removeLast();
        }
    }
}
