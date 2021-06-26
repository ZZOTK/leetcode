package leetcode.程序员面试金典;
//设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
//
//如果指定节点没有对应的“下一个”节点，则返回null。

/*
对于一般的root的下一个，就是右节点的最左节点
如果不存在右节点
1. 在左子树为父节点
2. 在右子树为null
 */
public class ms04_06 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode pre = null;
        //定位到p的位置。pre表示root的父节点
        while(root.val != p.val){
            if(p.val > root.val){
                root = root.right;
            }else{
                pre = root;
                root = root.left;
            }
        }
        //此时pr用上
        if(root.right == null){
            return pre;
        }
        TreeNode res = root.right;
        while(res.left != null){
            res = res.left;
        }
        return res;
    }

}
