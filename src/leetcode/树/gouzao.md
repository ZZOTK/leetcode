从前序与中序遍历序列构造二叉树

递归的思路
前序：中左右
中序：左中右

前序的第一个数为中，遍历中序，找到这个数，那么左边的长度就是左子树的长度

递归重建
```java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,0, preorder.length - 1, inorder, 0, inorder.length - 1);

    }
    public TreeNode build(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2){
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
        //左子树的长度
        int distance = ans - l2;
        TreeNode root = new TreeNode(begin);
        //前序中的左子树：去掉第一个，加上左子树长度
        //中序中的左子树：第一个到ans-1
        root.left = build(preorder, l1 + 1, l1 + distance , inorder, l2, ans - 1);
        //同理
        root.right = build(preorder,l1 + distance + 1, r1, inorder, ans + 1, r2);
        return root;
    }
}
```

中序与后序重建，同理

```java
class Solution {
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
        root.left = build(inorder, l1 ,renode - 1 ,postorder, l2 ,r2 - distance - 1);
        root.right= build(inorder, renode + 1 , r1 ,postorder, r2 - distance, r2 - 1);
        return root;
    }
}
```