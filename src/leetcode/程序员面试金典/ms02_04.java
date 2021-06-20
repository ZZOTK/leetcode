package leetcode.程序员面试金典;

public class ms02_04 {
    //双链表
    public ListNode partition1(ListNode head, int x) {
        if(head == null){
            return null;
        }
        ListNode small = new ListNode(0);
        ListNode ss = small;
        ListNode large = new ListNode(0);
        ListNode ll = large;
        while(head!= null){
            if(head.val < x){
                small.next = new ListNode(head.val);
                small =small.next;
            }else{
                large.next = new ListNode(head.val);
                large = large.next;
            }
            head = head.next;
        }
        small.next = ll.next;
        large.next = null;
        return ss.next;
    }


    //头插法
    public ListNode partition(ListNode head, int x) {
        if(head == null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = head;
        ListNode las = head.next;
        while(las != null){
            if(las.val < x){
                pre.next = las.next;
                las.next = dummy.next;
                dummy.next = las;
                las = pre.next;
            }else{
                pre =pre.next;
                las = las.next;
            }
        }
        return dummy.next;
    }
}
