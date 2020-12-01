package leetcode.树;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//同offer_32
public class leetcode103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans= new LinkedList<>();
        Deque<TreeNode> path = new LinkedList<>();
        int count = 0;
        if(root == null){
            return ans;
        }
        path.add(root);
        while(!path.isEmpty()){
            LinkedList<Integer> level = new LinkedList<>();
            int n = path.size();
            for(int i = 0; i< n; i ++){
                TreeNode temp = path.poll();
                if(count%2 == 0){
                    level.addLast(temp.val);
                }else{
                    //利用addFirst实现从右往左
                    level.addFirst(temp.val);
                }
                if(temp.left != null){
                    path.addLast(temp.left);
                }
                if(temp.right != null){
                    path.addLast(temp.right);
                }
            }
            count ++;
            ans.add(level);
        }
        return ans;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
