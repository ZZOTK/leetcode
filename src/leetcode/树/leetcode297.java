package leetcode.树;

//请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
//

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//方法一，前序遍历并复原
public class leetcode297 {
    String seb = ",";
    String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root,sb);
        return sb.toString();
    }

    public void serialize(TreeNode root,StringBuilder sb){
        if(root == null){
            sb.append(NULL);
            sb.append(seb);
            return;
        }
        sb.append(root.val);
        sb.append(seb);

        serialize(root.left , sb);
        serialize(root.right , sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for(String s : data.split(seb)){
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    public TreeNode deserialize(LinkedList<String> nodes){
        if(nodes.isEmpty()){
            return null;
        }
        String first = nodes.removeFirst();
        if(first.equals(NULL)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));

        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }

    //后序做法
    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        StringBuilder sb =new StringBuilder();
        serialize1(root,sb);
        return sb.toString();
    }

    public void serialize1(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append(NULL);
            sb.append(seb);
            return;
        }
        serialize1(root.left,sb);
        serialize1(root.right,sb);
        sb.append(root.val);
        sb.append(seb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        LinkedList<String> da = new LinkedList<>();
        for(String str : data.split(seb)){
            da.addLast(str);
        }
        return deserialize1(da);

    }

    public TreeNode deserialize1(LinkedList<String> da){
        if(da.isEmpty()){
            return null;
        }
        String first = da.removeLast();
        if(first.equals(NULL)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.right = deserialize1(da);
        root.left = deserialize1(da);
        return root;
    }

}
