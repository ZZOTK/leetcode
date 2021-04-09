package leetcode.树;
//给定一个二叉树，找出其最小深度。
//最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//说明：叶子节点是指没有子节点的节点。

//重点：判断结束条件
public class leetcode111 {
    //递归的写法
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        //左右有一个为空则相加。都不为空则取小的
        if(root.left == null || root.right == null){
            return m1 + m2 + 1;
        }
        return Math.min(m1,m2) +  1;
    }

    //DFS
    public int minDepth1(TreeNode root) {
        //为null则0，有一个为null则1
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(minDepth(root.left), min);
        }
        if (root.right != null) {
            min = Math.min(minDepth(root.right), min);
        }
        return min + 1;
    }
}
