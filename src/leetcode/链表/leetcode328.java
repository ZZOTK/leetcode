package leetcode.链表;

//给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
//请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
//原地完成
public class leetcode328 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode l1 = head;
        ListNode l2 = head.next;
        ListNode he = head.next;
        while(l1.next != null && l2.next != null){
            l1.next = l2.next;
            l1 = l1.next;
            l2.next = l1.next;
            l2 = l2.next;
        }
        l1.next = he;
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
