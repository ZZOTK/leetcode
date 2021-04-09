package leetcode.树;

public class leetcode450 {
    //找到比他大的最小节点。即右子树的最左节点
    public int bigger(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    //找到比他小的最大节点。即左子树的最右节点
    public int smaller(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        //递归，找key的位置
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            //找到key，分情况讨论
            //key为叶子节点，直接删除
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                //key的右子树不为空，则需要将节点换为稍大的节点再删除
                root.val = bigger(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                //key的右子树为空，则需要将节点换为稍小的节点再删除
                root.val = smaller(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
}
