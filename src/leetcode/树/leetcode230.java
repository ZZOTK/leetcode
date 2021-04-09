package leetcode.树;
//给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
//BST.中序遍历
public class leetcode230 {
    int rank = 0;
    int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        tra(root, k);
        return res;

    }

    public void tra(TreeNode root ,int  k){
        if(root == null){
            return;
        }
        tra(root.left, k);
        rank ++;
        if(rank == k){
            res = root.val;
            return;
        }
        tra(root.right, k);
    }


}
