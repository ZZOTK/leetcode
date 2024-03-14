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

中序遍历的下一个节点

```java
private class TreeLinkNode{
        int val;
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
        public TreeLinkNode(int val){
            this.val = val;
        }
    }
public TreeLinkNode GetNext1(TreeLinkNode pNode) {
         //如果当前节点存在右子节点，则中序下一个节点为右子树最左下的节点，如果右子树没有左子结点就返回右子树根结点
        if (pNode.right != null){
            pNode = pNode.right;
            while (pNode.left != null){
                pNode = pNode.left;
            }
            return pNode;
        }

        //如果不存在右子节点，则回到父节点中判断，如果父节点的右子树为该节点，则继续寻找父节点
        while (pNode.next != null && pNode == pNode.next.right){
            pNode = pNode.next;
        }
        //若该节点为父节点的左孩子，则下一个中序节点就是父节点

        return pNode.next;
    }
```

二叉树的层序遍历

利用队列记录上一层的节点，再放入一个list中
```java
public class leetcode102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        Queue<TreeNode> path=new LinkedList<>();
        if (root == null) {
            return ans;
        }
        path.add(root);
        while (!path.isEmpty()){
            List<Integer> level=new ArrayList<>();
            int len=path.size();
            for (int i=0;i<len;i++){
                TreeNode node=path.poll();
                level.add(node.val);
                if (node.left!=null){
                    path.add(node.left);
                }
                if (node.right!=null){
                    path.add(node.right);
                }
            }
            ans.add(level);
        }
        return ans;
    }

}
```

下面两题都需要用到中序遍历的特性

leetcode 530

leetcode 98 
