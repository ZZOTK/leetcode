package leetcode.回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class leetcode22 {
    //解法一
    List<String> ans = new LinkedList<>();

    public List<String> generateParenthesis1(int n) {
        backtrack(0, 0, n, "");
        return ans;
    }

    //不合法就跳出。回溯的基本思路及模板
/*    result = []
    def backtrack(路径, 选择列表):
            if 满足结束条件:
            result.add(路径)
            return

            for 选择 in 选择列表:
                 做选择
                 backtrack(路径, 选择列表)
                 撤销选择
*/
    public void backtrack(int left, int right, int n, String path) {
        // 肯定不合法，提前结束
        if (left > n || left < right) {
            return;
        }
        // 到达结束条件
        if (left + right == 2 * n) {
            ans.add(path);
            return;
        }
        // 选择
        backtrack(left + 1, right, n, path + '(');
        backtrack(left, right + 1, n, path + ')');
    }

    //解法2 DFS
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return res;
    }

    private void dfs(int left, int right, String curStr) {
        if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
            res.add(curStr);
            return;
        }

        if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
            dfs(left - 1, right, curStr + "(");
        }
        if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
            dfs(left, right - 1, curStr + ")");
        }
    }
}

