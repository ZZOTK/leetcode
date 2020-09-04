package leetcode.链表;

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

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

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
