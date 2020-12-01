

import javax.naming.InsufficientResourcesException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans= new LinkedList<>();
        Deque<TreeNode> path = new LinkedList<>();
        int count = 0;
        if(root == null){
            return ans;
        }
        path.addFirst(root);
        while(!path.isEmpty()){
            LinkedList<Integer> level = new LinkedList<>();
            int n = path.size();
            for(int i = 0; i< n; i ++){
                TreeNode temp = path.poll();
                if(count%2 == 0){
                    level.addLast(temp.val);
                }else{
                    level.addFirst(temp.val);
                }
                if(root.left != null){
                    path.addLast(root);
                }
                if(root.right != null){
                    path.addLast(root);
                }
            }
            count ++;
            ans.add(level);
        }
        return ans;
    }

    public static void main(String[] args) {

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


}