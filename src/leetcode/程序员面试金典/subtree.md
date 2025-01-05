## 简单的子树问题

如果需要比较到子树，那么常用两个递归完成
1. 递归遍历树的所有节点
2. 对第一个遍历中每个节点，递归遍历它的子树

### 面试题 04.10. 检查子树
```java
    // 递归遍历T1的节点，再每个节点的子树与t2比较
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if(t1 == null || t2 == null){
            return false;
        }
        return isSame(t1,t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    //递归比较两个树是否相同
    public boolean isSame(TreeNode A, TreeNode B){
        if(B == null){
            return true;
        }
        if(A == null || A.val != B.val){
            return false;
        }
        return isSame(A.left, B.left) && isSame(A.right, B.right);
    }
```
### 面试题 04.12. 求和路径
```java
public class ms04_12 {
    int res = 0;
    public int pathSum(TreeNode root, int sum) {
        path(root,sum);
        return res;
    }

    //递归遍历树的每个节点
    public void path (TreeNode root,int sum){
        if(root == null){
            return;
        }
        backtrack(root,0,sum);
        pathSum(root.left,sum);
        pathSum(root.right,sum);
    }

    //递归计算每个节点的子树中是否包含路径
    public void backtrack(TreeNode root,int sum,int target){
        if(root == null){
            return;
        }
        int temp = sum + root.val;
        if(temp == target){
            res ++;
        }
        backtrack(root.left,temp,target);
        backtrack(root.right,temp,target);
    }
}
```

### leetCode226 反转二叉树
```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null || root.left == null && root.right == null){
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
```


