package leetcode.链表;

import java.util.Stack;

public class leetcode445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //利用栈处理链表倒序问题
        Stack<Integer> s1=new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        //存入栈
        while (l1!=null){
            s1.push(l1.val);
            l1=l1.next;
        }
        while (l2!=null){
            s2.push(l2.val);
            l2=l2.next;
        }
        int jinwei=0;
        //进位，常用
        ListNode head=null;
        while (!s1.isEmpty() || !s2.isEmpty()||jinwei>0){
            int sum=jinwei;
            //不为空pop返回，为空则返回0
            sum += s1.isEmpty()? 0: s1.pop();
            sum += s2.isEmpty()? 0: s2.pop();
            ListNode node=new ListNode(sum%10);
            //指头法创建倒序链表
            node.next = head;
            head = node;
            jinwei=sum/10;
        }
        return head;
    }
}
