package leetcode.树;

import java.util.LinkedList;
import java.util.Queue;

//给出一个完全二叉树，求出该树的节点个数。
//完全二叉树，所以要有更好的思路
//写的bfs和递归都是普通解法，好的解法见题解
public class leetcode222 {
    //BFS
    public int countNodes1(TreeNode root) {
        if(root == null){
            return 0;
        }
        int ans = 0;
        Queue<TreeNode> path = new LinkedList<>();
        path.offer(root);
        while(!path.isEmpty()){
            int n = path.size();
            ans += n;
            for(int i = 0; i < n; i ++){
                TreeNode nt = path.poll();
                if(nt.left != null){
                    path.offer(nt.left);
                }
                if(nt.right != null){
                    path.offer(nt.right);
                }
            }
        }
        return ans;
    }

    //递归
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
