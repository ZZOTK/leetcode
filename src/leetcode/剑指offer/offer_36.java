package leetcode.剑指offer;
//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

import java.util.Deque;
import java.util.LinkedList;

public class offer_36 {
    //先用栈存储再依次处理
    Deque<Node> path;
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        path = new LinkedList<>();
        dfs(root);
        Node head = new Node();
        Node pre = path.pop();
        head.right = pre;
        while(!path.isEmpty()){
            Node temp = path.pop();
            pre.right = temp;
            temp.left = pre;
            pre = temp;
        }
        Node fir = head.right;
        fir.left = pre;
        pre.right = fir;
        return head.right;
    }
    //中序遍历。
    public void dfs(Node root){
        if(root == null){
            return;
        }
        dfs(root.left);
        path.add(root);
        dfs(root.right);
    }


    //递归的思路。漂亮的写法
    Node head, pre;
    public Node treeToDoublyList1(Node root) {
        if(root==null) return null;
        dfs1(root);

        pre.right = head;
        head.left =pre;//进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的

        return head;

    }

    public void dfs1(Node cur){
        if(cur==null) return;
        dfs1(cur.left);

        //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
        if(pre==null) head = cur;
            //反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
        else pre.right = cur;

        cur.left = pre;//pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。

        pre = cur;//pre指向当前的cur
        dfs1(cur.right);//全部迭代完成后，pre指向双向链表中的尾节点
    }


    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
