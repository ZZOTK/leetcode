package leetcode.树;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//找到二叉树两个节点间的最短距离
//先找到公共祖先，再分别求两个到祖先的距离。
public class FindDistance {

    //使用哈希表而不是递归求最近公共祖先！！！！！！！！！！！！！！
    //
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

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
