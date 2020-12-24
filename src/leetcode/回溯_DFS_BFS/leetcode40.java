package leetcode.回溯_DFS_BFS;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
//candidates中的每个数字在每个组合中只能使用一次。
//说明：所有数字（包括目标数）都是正整数。解集不能包含重复的组合。

//难点在于去重。如何不包含重复的组合
public class leetcode40 {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new LinkedList<>();
        //关键，必须sort
        Arrays.sort(candidates);
        Deque<Integer> path = new LinkedList<>();
        backtrack(candidates,target,path,0);
        return ans;
    }

    public void backtrack(int[] candidates, int target, Deque<Integer> path, int start){
        if(target == 0){
            ans.add(new LinkedList<>(path));
            return;
        }
        for(int i = start; i < candidates.length; i ++){
            //由于从小到大排列，那么target < candidates[i]时可以直接break
            //注意为break.这个枝往后都不用执行。
            if(target - candidates[i] < 0){
                break;
            }
            //难点！！！！！！！！！！！！
            //在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，
            //必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
            //第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
            //也就是说，此时添加的元素都在同一层！所以可以去除相同的
            if(i > start && candidates[i] == candidates[i-1]){
                continue;
            }
            path.addFirst(candidates[i]);
            backtrack(candidates,target - candidates[i], path, i + 1);
            path.pollFirst();
        }
    }
}
