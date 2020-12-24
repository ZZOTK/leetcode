package leetcode.剑指offer;
//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，
// 否则返回 false。假设输入的数组的任意两个数字都互不相同。

public class offer_33 {
    public boolean verifyPostorder(int[] postorder) {
        return ver(postorder,0,postorder.length - 1);
    }
    //
    public boolean ver(int[] postorder, int i, int j){
        if(i >= j){
            return true;
        }
        int p = i;
        //找左子树
        while(postorder[p] < postorder[j]){
            p ++;
        }
        //m为右子树的开始
        int m = p ;
        //右子树。此时p走了一遍
        while(postorder[p] > postorder[j]){
            p ++;
        }
        //p==j这个判断很重要。
        return p == j && ver(postorder,i,m - 1) && ver(postorder, m, j - 1);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
