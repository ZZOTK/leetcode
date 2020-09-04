package leetcode.链表;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class leetcode21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {//l1,l2为两个节点
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);//递归
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);//递归
            return l2;
        }
//并不是新一个链表，而是两个链表间next指向改变
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {};
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
