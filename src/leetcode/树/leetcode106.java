package leetcode.树;
//根据一棵树的中序遍历与后序遍历构造二叉树。
//同105，注意ditance，注意递归中left和right中范围的选取。
public class leetcode106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length - 1,postorder, 0, postorder.length - 1);
    }

    public TreeNode build(int[] inorder,int l1, int r1, int[] postorder, int l2, int r2){
        if(l2 > r2){
            return null;
        }
        int node = postorder[r2];
        int renode = 0;
        for( int i = l1; i <= r1; i ++){
            if(inorder[i] == node){
                renode = i;
            }
        }
        int distance = r1 - renode;
        TreeNode root = new TreeNode(node);
        root.left = build(inorder, l1 ,renode - 1 ,postorder,l2,r2 - distance - 1);
        root.right= build(inorder, renode + 1 , r1 ,postorder, r2 - distance, r2 - 1);
        return root;
    }

}
