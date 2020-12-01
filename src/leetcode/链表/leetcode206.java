package leetcode.链表;
//反转链表
public class leetcode206 {
    public ListNode reverseList(ListNode head) {
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

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
