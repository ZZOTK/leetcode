package leetcode.树;
//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
//路径和 是路径中各节点值的总和。
//
//当前节点最大路径和怎么求：左边路径最大加右边路径最大加root.val
//

public class leetcode124 {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        backtrack(root);
        return max;
    }

    public int backtrack(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //左右必须大于0，不然可以不要，就为0
        int left = Math.max(backtrack(root.left), 0);
        int right = Math.max(backtrack(root.right), 0);
        //sum为当前节点的路径和
        int sum = root.val + left + right;
        max = Math.max(max, sum);
        //当前节点作为一侧路径的最大和
        return root.val + Math.max(right, left);
    }


}
