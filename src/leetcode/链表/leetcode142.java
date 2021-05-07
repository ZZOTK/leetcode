package leetcode.链表;
//判断链表是否有环及环的入口
//快慢指针判断有环。两指针相遇即有环
//相遇后起点与慢指针再相遇为入口。
public class leetcode142 {
    public ListNode detectCycle(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        ListNode temp=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                while(temp!=slow){
                    temp=temp.next;
                    slow=slow.next;
                }
                return temp;
            }
        }
        return null;
    }

    // 补充：环的长度int
    public int cycleLen(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        int ans = 0;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                while(fast!=slow){
                    fast=fast.next;
                    slow=slow.next;
                    ans ++;
                }
                return ans;
            }
        }
        return -1;
    }

}
