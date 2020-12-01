package leetcode.树;
//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
//之字形层序遍历二叉树 ———— offer_32
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
