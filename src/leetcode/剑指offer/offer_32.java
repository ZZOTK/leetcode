package leetcode.剑指offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
public class offer_32 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        Deque<TreeNode> path = new LinkedList<>();
        int count = 0;
        if(root == null){
            return ans;
        }
        path.add(root);
        while(!path.isEmpty()){
            LinkedList<Integer> level = new LinkedList<>();
            int n = path.size();
            for(int i = 0;i < n; i ++){
                TreeNode nt = path.poll();

                if(count % 2 == 0){
                    level.addLast(nt.val);
                }else{
                    level.addFirst(nt.val);
                }

                if(nt.left != null){
                    path.add(nt.left);
                }
                if(nt.right != null){
                    path.add(nt.right);
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
