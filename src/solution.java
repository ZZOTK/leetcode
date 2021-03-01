
import leetcode.剑指offer.offer_68_2;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class solution {

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

        solution a = new solution();
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

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
