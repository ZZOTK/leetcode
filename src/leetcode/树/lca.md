## 剑指offer68-2
> 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

对于树的问题，我们会考虑使用递归。我们**自叶向根**，让每个节点返回p，q的**父结点**。

也就是，当root 为 null，p，q时，直接返回root，这也是递归的一个终止条件。

记录每个root的left和right。然后观察left和right 的值。如果一侧为空，那么说明，p，q的父结点都在另一侧，则返回另一侧。
如果p，q都不为空，说明在root的两侧，就返回root。递归代码如下：

```java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
```

## 求两个节点的最小距离
显然，两个节点的最小距离就是两个点到最近公共子节点的距离和。用递归的方法我们可以求出最近的公共节点。但是，此时求这个公共节点到p，q的距离
依然十分复杂。

利用Hash表求最近公共节点。
* 利用哈希表存储每个节点的父节点，然后再用set找到第一个既是q的父结点中也是p的父结点的节点。
* 利用哈希表去求距离。

```java
public class FindDistance {

    //使用哈希表而不是递归求最近公共祖先！
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    //dfs，存放每个节点的父结点。
    public void dfs(TreeNode root){
        if (root.left!=null){
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null){
            parent.put(root.right.val,root);
            dfs(root.right);
        }
    }

    //将p的父结点一路向上，全部放入set中。再向上遍历q的父结点，如果set中已经存在，那么就是公共节点
    public TreeNode lca(TreeNode root,TreeNode p, TreeNode q){
        dfs(root);
        while(p!=null){
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while(q!=null){
            if (visited.contains(q.val)){
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    //p，q分别向上遍历到公共节点，记录次数即距离
    public int dis(TreeNode lca,TreeNode p,TreeNode q){
        int n = 0;
        while(p.val!=lca.val){
            n ++;
            p = parent.get(p.val);
        }
        while(q.val!=lca.val){
            n ++;
            q = parent.get(q.val);
        }
        return n;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.right = node7;
        node6.left = node8;

        FindDistance a = new FindDistance();
        TreeNode lca = a.lca(node1,node3,node7);
        int dis = a.dis(lca,node3,node7);
        System.out.println(dis);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
```
