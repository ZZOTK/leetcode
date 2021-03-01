package leetcode.树;
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。

import java.util.Deque;
import java.util.LinkedList;

//递归与迭代两种方法
public class leetcode98 {
    //迭代。本质上是树的中序遍历。中序遍历应该递增。
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack =  new LinkedList<>();
        while(!stack.isEmpty() || root != null){
            while(root!= null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.val <= pre){
                return false;
            }
            pre = root.val;
            root = root.right;
        }
        return true;
    }

    //递归
    public boolean isValidBST1(TreeNode root) {
        return isva(root,null,null);
    }
    public boolean isva(TreeNode root, TreeNode min, TreeNode max){
        if(root == null){
            return true;
        }
        if(min != null && root.val <= min.val){
            return false;
        }
        if(max != null && root.val >= max.val){
            return false;
        }
        return isva(root.left,min,root) && isva(root.right,root,max);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
