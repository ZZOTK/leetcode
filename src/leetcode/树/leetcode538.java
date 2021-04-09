package leetcode.树;
//给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
//后序遍历。每个节点值赋依次相加的值

public class leetcode538 {
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        houxu(root);
        return root;

    }

    public void houxu(TreeNode root){
        if(root == null){
            return;
        }
        houxu(root.right);
        sum += root.val;
        root.val = sum;
        houxu(root.left);
    }

}
