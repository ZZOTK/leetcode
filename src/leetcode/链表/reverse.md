## 反转链表

三个方法：
1. 迭代
2. 递归
3. 头插法

```java
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

    //头插法
    public ListNode reverseList2(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode pre = head.next;
        ListNode newhead = head;
        while(pre != null){
            head.next = pre.next;
            pre.next = newhead;
            newhead = pre;
            pre = head.next;
        }
        return newhead;
    }
}
```

## leetcode92

反转[left，right]区间的链表

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dum = new ListNode(-1);
        dum.next = head;
        ListNode h1 = dum;
        for(int i = 0; i < left - 1; i ++){
            h1 = h1.next;
        }
        ListNode h2 = h1.next;
        ListNode pre = h2.next;
        ListNode las = h2;
        for(int i = 0; i <right - left; i ++){
            h2.next = pre.next;
            pre.next = las;
            h1.next = pre;
            las = pre;
            pre = h2.next;
            
        }
        return dum.next;
    }
}
```


