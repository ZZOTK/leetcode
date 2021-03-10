## leetcode141
> 给定一个链表，判断链表中是否有环。

判断是否有环，我们只需要快慢指针（快慢指针在链表中常用）。快指针每次走两步，慢指证走一步。
如果有环，双指针一定会相遇。没有的话，快指针会先到null。


```java
public class leetcode141 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast!= null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}
```

## leetcode142
> 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

环入口怎么求。
* 快慢指针相遇，判断出是否有环。
* 有环之后，慢指针与头节点处的一个指针一步一步走，再相遇。此时相遇的地点为入口

```java
    public ListNode detectCycle(ListNode head) {
        //快指针
        ListNode fast=head;
        //慢指针
        ListNode slow=head;
        ListNode temp=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            //判断是否有环，如果有环
            if(fast==slow){
                //再次相遇
                while(temp!=slow){
                    temp=temp.next;
                    slow=slow.next;
                }
                return temp;
            }
        }
        //没有环返回null
        return null;
    }
```

## 环的长度
> 给定一个链表，返回其中环的长度。如果没有环，返回-1。

相同思路：
* 快慢指针判断是否有环
* 有环之后，快慢指针再次相遇，走了几次就是环的长度。

```java
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
```