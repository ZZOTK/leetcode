package leetcode.链表;
//反转链表
public class leetcode206 {

    //迭代方法
    public ListNode reverseList1(ListNode head) {
        ListNode las=head;
        ListNode pre=null;
        ListNode temp=null;
        while(las!=null){
            temp=las.next;
            las.next=pre;
            pre=las;
            las=temp;

        }
        return pre;
    }

//递归方法
    public ListNode reverseList(ListNode head) {
        // 1. 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        //重点
        //这里使原来的head.next指向head
        head.next.next = head;
        head.next = null;
        return p;

    }


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
