package leetcode.树;
//二叉树反转问题。交换左右节点会带着节点的left和right一起交换。
public class leetcode226 {
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        //交换过程，左右交换
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;

        //递归，交换左子树和右子树
        invertTree(root.left);
        invertTree(root.right);

        //返回整棵数
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
