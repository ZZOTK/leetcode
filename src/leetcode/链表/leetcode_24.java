package leetcode.链表;
//反转链表
public class leetcode_24 {
    public ListNode swapPairs1(ListNode head) {
        if ((head == null) || (head.next == null)) {
            return head;
        }
        //递归解法
        ListNode firstNode = head;
        ListNode secondNode = head.next;
        //交换
        firstNode.next  = swapPairs1(secondNode.next);
        secondNode.next = firstNode;
        //返回链表头节点
        return secondNode;
    }

    public ListNode swapPairs2(ListNode head) {
        //新建一个头节点，链表常用方法
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while(temp.next != null && temp.next.next != null) {
            //交换过程
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }


    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
