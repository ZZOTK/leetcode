package leetcode.回溯_DFS_BFS;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
//所有数字都是正整数。解集不能包含重复的组合。
public class leetcode216 {
    List<List<Integer>> ans;
    Deque<Integer> path;
    public List<List<Integer>> combinationSum3(int k, int n) {
        ans = new LinkedList<>();
        path = new LinkedList<>();
        backtrack(k,n,0,1);
        return ans;
    }
    public void backtrack(int k, int n,int len, int start){
        if(len == k && n != 0){
            return;
        }
        if(len == k && n ==0){
            ans.add(new LinkedList<>(path));
        }
        //剪枝
        for(int i = start; i <= 9; i ++){
            //break
            if(n < i){
                break;
            }
            //i + 1
            path.addFirst(i);
            backtrack(k,n-i,len + 1,i + 1);
            path.pollFirst();
        }
    }
}
