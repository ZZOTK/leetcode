package leetcode.链表;
//链表旋转，数组旋转在189
//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数
public class leetcode61 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null) return null;
        if (head.next==null) return head;
//将原来链表转换为一个循环链表；链表旋转即改变循环链表的head和tail
        ListNode old_tail=head;
        int n;
        for (n=1;old_tail.next!=null;n++){
            old_tail=old_tail.next;
        }
        //n为链表长度
        old_tail.next=head;
//循环链表找到tail和head
        ListNode new_tail=head;
        for (int i=1;i<n-k%n;i++){
            new_tail=new_tail.next;
        }
        ListNode new_head=new_tail.next;
//tail的next为null
        new_tail.next=null;
        return new_head;

    }

}
