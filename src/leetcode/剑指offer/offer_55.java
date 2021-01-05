package leetcode.剑指offer;
//输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

//任意节点都要是平衡二叉树
public class offer_55 {
    //自顶向下的思路
    //root，root.left，root.right都满足，就满足。递归向下
    public boolean isBalanced1(TreeNode root) {
        if (root == null) return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced1(root.left) && isBalanced1(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }


    //自底向上的方法
    public boolean isBalanced(TreeNode root) {
        if(dfs(root) == -1){
            return false;
        }
        return true;

    }
    //用-1来记录不是平衡二叉树
    public int dfs(TreeNode root){
        if(root == null){
            return 0;
        }
        int numl = dfs(root.left);
        if(numl == -1){
            return -1;
        }
        int numr = dfs(root.right);
        if(numr == -1){
            return -1;
        }
        return Math.abs(numl - numr) < 2 ? Math.max(numl, numr) + 1 : -1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
