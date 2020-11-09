package leetcode.树;
//给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
//二叉树的根是数组中的最大元素。
//左子树是通过数组中最大值左边部分构造出的最大二叉树。
//右子树是通过数组中最大值右边部分构造出的最大二叉树。
//通过给定的数组构建最大二叉树，并且输出这个树的根节点。

public class leetcode654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0 , nums.length - 1);
    }

    public TreeNode build(int[] nums, int l , int  r){
        if(l > r){
            return null;
        }
        int ans =l;
        for(int i = l; i <= r ; i ++){
            if(nums[i] > nums[ans]){
                ans = i;
            }
        }
        TreeNode root = new TreeNode(nums[ans]);
        root.left = build(nums, l , ans - 1);
        root.right = build(nums, ans + 1, r );
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
