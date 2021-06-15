## leetcode94

给定一个二叉树的根节点 root ，返回它的 中序 遍历。

递归方法：
```java
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        zx(root,res);

        return res;
    }
    public void zx(TreeNode root,List<Integer> res){
        if(root==null){
            return;
        }
        zx(root.left,res);
        res.add(root.val);
        zx(root.right,res);
    }
```

迭代方法:
```java
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
```

Morris遍历： 它能将非递归的中序遍历空间复杂度降为O(1)。

```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    TreeNode pre = null;
    while(root!=null) {
        //如果左节点不为空，就将当前节点连带右子树全部挂到
        //左节点的最右子树下面
        if(root.left!=null) {
            pre = root.left;
            while(pre.right!=null) {
                pre = pre.right;
            }
            pre.right = root;
            //将root指向root的left
            //断开环
            TreeNode tmp = root;
            root = root.left;
            tmp.left = null;
        //左子树为空，则打印这个节点，并向右边遍历	
        } else {
            res.add(root.val);
            root = root.right;
        }
    }
    return res;
}

```



