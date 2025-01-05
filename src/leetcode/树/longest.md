leetcode 543
```java
//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
public class leetcode543 {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right); 
        max = Math.max(max,left +right);
        // 到某个节点为止的最大深度
        return Math.max(left,right) +1;
    }

}
```

leetcode104 二叉树的最大深度
```java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        // 最大深度必须过根节点
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}
```

leetcode124
