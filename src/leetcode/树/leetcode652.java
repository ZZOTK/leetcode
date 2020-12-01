package leetcode.树;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
//两棵树重复是指它们具有相同的结构以及相同的结点值。

//每个节点的子树表示为一个string，再存入哈希表(key:string,value:cishu)。
//次数默认为0.每次出现则加一。当第二次出现为1.所以为1时加入ans,避免重复计数。

public class leetcode652 {
    String NULL = "#";
    List<TreeNode> ans = new LinkedList<>();
    Map<String,Integer> pre = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        com(root);
        return ans;
    }

    public String com(TreeNode root){
        if(root == null){
            return NULL;
        }

        String left = com(root.left);
        String right = com(root.right);

        //当前节点的子树表示为一个string。
        String substring = left + ',' + right + ',' + root.val;

        int freq = pre.getOrDefault(substring,0);
        if(freq == 1){
            ans.add(root);
        }
        pre.put(substring,freq + 1);
        return substring;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
