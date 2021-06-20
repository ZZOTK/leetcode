package leetcode.程序员面试金典;

public class ms02_05 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int jinwei = 0;
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        while(l1 != null || l2 != null || jinwei != 0){
            int sum = 0;
            if(l1 != null){
                sum += l1.val;
                l1 =l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            sum += jinwei;
            jinwei = sum/10;
            sum = sum % 10;
            dummy.next = new ListNode(sum);
            dummy = dummy.next;
        }
        return res.next;
    }
}
