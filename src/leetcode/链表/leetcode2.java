package leetcode.链表;

import java.util.List;

//给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
//如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//您可以假设除了数字 0 之外，这两个数都不会以 0开头。

public class leetcode2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp=new ListNode(0);
        ListNode cur=temp;
        ListNode p1=l1;
        ListNode p2=l2;
        int jinwei=0;
        while (p1!=null||p2!=null){
            int sum=0;
            int x=0;int y=0;
//            if (p1==null) p1.val=0;
//            if (p2==null) p2.val=0;
//           节点为null无法直接用val赋值
            if (p1!=null){
                x=p1.val;
            } else x=0;
            if (p2!=null){
                y=p2.val;
            }else y=0;
            sum=x+y+jinwei;
            //判断是否为空并后移
            if (p1!=null){
                p1=p1.next;
            }
            if (p2!=null){
                p2=p2.next;
            }
            jinwei=sum/10;
            cur.next=new ListNode(sum%10);
            cur=cur.next;
        }
        //最后一个位进位
        if (jinwei==1){
            cur.next=new ListNode(jinwei);
        }
        return temp.next;
    }

}
