package leetcode.树;
//
//给定一个二叉树，原地将它展开为一个单链表。

public class leetcode116 {
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = null;
        root.right = left;
        TreeNode p = root;
        while(p.right != null){
            p = p.right;
        }
        p.right = right;

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
