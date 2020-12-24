package leetcode.剑指offer;

//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
//B是A的子结构， 即 A中有出现和B相同的结构和节点值。

//两个递归
public class offer_26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null){
            return false;
        }
        return issame(A, B) //判断A，B是否为一颗子树
                //递归判断A.left与A.right是否为子树
                //注意这里为isSubStructure
                || isSubStructure(A.left,B) || isSubStructure(A.right, B);
    }

    //判断是否为一颗子树
    public boolean issame(TreeNode A, TreeNode B){
        if(B == null){
            return true;
        }
        if(A == null || A.val != B.val){
            return false;
        }
        return issame(A.left, B.left) && issame(A.right, B.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
