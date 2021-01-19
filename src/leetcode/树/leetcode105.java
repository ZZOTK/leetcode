package leetcode.树;
//根据一棵树的前序遍历与中序遍历构造二叉树。
//利用中序遍历找位置，前序遍历依次往里面添加。
//leetcode106从中序和后序遍历
public class leetcode105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    public TreeNode build(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2){
        //只需要判断前序是否出界
        if(l1 > r1 ){
            return null;
        }
        int begin = preorder[l1];
        int ans = 0;
        for(int i = l2; i <= r2; i ++){
            if(inorder[i] == begin){
                ans = i;
                break;
            }
        }
        //利用distance来确定位置。
        int distance = ans - l2;
        TreeNode root = new TreeNode(begin);
        //位置很。更新的r1，l1都需要思考
        root.left = build(preorder, l1 + 1, l1 + distance , inorder, l2, ans - 1);
        root.right = build(preorder,l1 + distance + 1, r1, inorder, ans + 1, r2);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
