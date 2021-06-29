package leetcode.程序员面试金典;
//给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
//
public class ms04_12 {
    int res = 0;
    public int pathSum(TreeNode root, int sum) {
        path(root,sum);
        return res;
    }

    public void path (TreeNode root,int sum){
        if(root == null){
            return;
        }
        backtrack(root,0,sum);
        pathSum(root.left,sum);
        pathSum(root.right,sum);
    }

    public void backtrack(TreeNode root,int sum,int target){
        if(root == null){
            return;
        }
        int temp = sum + root.val;
        if(temp == target){
            res ++;
        }
        backtrack(root.left,temp,target);
        backtrack(root.right,temp,target);
    }
}
