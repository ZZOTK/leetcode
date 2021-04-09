package leetcode.树;
//填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
public class leetcode114 {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
        twoconnect(root.left, root.right);
        return root;

    }

    public void twoconnect(Node left, Node right){
        if(left == null || right == null){
            return;
        }
        left.next = right;
        twoconnect(left.left, left.right);
        twoconnect(right.left, right.right);
        twoconnect(left.right, right.left);
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


}
