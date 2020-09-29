package leetcode.回溯;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leetcode39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Stack<Integer> path = new Stack<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }


    /**
     * @param candidates 数组
     * @param begin   数组的开始位置
     * @param len     数组的长度
     * @param target   目标和
     * @param path   路径
     * @param res   输出的结果
     */
    private void dfs(int[] candidates, int begin, int len, int target, Stack<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.push(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);

            // 状态重置
            path.pop();
        }
    }
}
