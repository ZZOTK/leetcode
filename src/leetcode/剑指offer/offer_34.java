package leetcode.剑指offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
//同leetcode113
//注意必须为叶子节点
public class offer_34 {
    List<List<Integer>> ans;
    Deque<Integer> path;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ans = new LinkedList<>();
        path = new LinkedList<>();
        backtrack(root,sum);
        return ans;

    }

    public void backtrack(TreeNode root, int sum){
        if(root == null){
            return;
        }
        sum -= root.val;
        //先加
        path.add(root.val);
        if(sum == 0 && root.left == null &&root.right == null){
            ans.add(new LinkedList<>(path));
            //这里很关键。如果此处return，就要先polllast。
        }
        backtrack(root.left,sum);
        backtrack(root.right, sum);
        path.pollLast();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
