package leetcode.链表;
//双指针

public class leetcode19 {
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }//找到链表的总长度length
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        //找到倒数第n+1个节点
        for (int i = 0; i < n ; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }
        //用哑节点确保.next.next不会报错
        second.next = second.next.next;
        //必须为dummy.next,head有可能被删除
        return dummy.next;
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
  }

}
