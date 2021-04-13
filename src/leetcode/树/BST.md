##

二叉搜索树的中序遍历是从小到大有序的！

## leetcode450
> 自己来实现二叉搜索树的删除节点

分析：如果要删除的节点不是叶节点，那么需要将他替换成比他小的最大值（左子树的最右节点）或者是比他大的最小值（右子树的最左节点）。
然后再删除替换的节点。

```java
    //找到比他大的最小节点。即右子树的最左节点
    public int bigger(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    //找到比他小的最大节点。即左子树的最右节点
    public int smaller(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        //递归，找key的位置
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            //找到key，分情况讨论
            //key为叶子节点，直接删除
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                //key的右子树不为空，则需要将节点换为稍大的节点再删除
                root.val = bigger(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                //key的右子树为空，则需要将节点换为稍小的节点再删除
                root.val = smaller(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
```