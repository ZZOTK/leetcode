package leetcode.树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
