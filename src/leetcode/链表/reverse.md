## 反转链表

三个方法：
1. 迭代
2. 递归
3. 头插法

```java
import leetcode.链表.ListNode;

public class leetcode206 {

    //迭代方法
    public ListNode reverseList1(ListNode head) {
        ListNode las = head;
        ListNode pre = null;
        while (las != null) {
            ListNode temp = las.next;
            las.next = pre;
            pre = las;
            las = temp;

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
        if (head == null) {
            return null;
        }
        ListNode pre = head.next;
        ListNode newhead = head;
        while (pre != null) {
            head.next = pre.next;
            pre.next = newhead;
            newhead = pre;
            pre = head.next;
        }
        return newhead;
    }
}
```
## 翻转链表的前N个节点

对于翻转前N个节点，使用递归方法

```java
class Solution {
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        ListNode successor = head.next.next;
        head.next.next = head;
        head.next = successor;
        return last;
    }
}
```


## leetcode92

反转[left，right]区间的链表



```java
import leetcode.链表.ListNode;

class Solution {
    // 普通的迭代方法
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dum = new ListNode(-1);
        dum.next = head;
        ListNode h1 = dum;
        for (int i = 0; i < left - 1; i++) {
            h1 = h1.next;
        }
        // 可以将las和h1视作一对，目标就是将pre插入到h1和las中间
        ListNode h2 = h1.next;
        ListNode pre = h2.next;
        ListNode las = h2;
        for (int i = 0; i < right - left; i++) {
            h2.next = pre.next;
            // pre放入h1和las之间
            pre.next = las;
            h1.next = pre;
            las = pre;
            // pre后移一格
            pre = h2.next;

        }
        return dum.next;
    }

    

    // 借用翻转1-N个节点递归实现
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        ListNode successor = head.next.next;
        head.next.next = head;
        head.next = successor;
        return last;
    }
}
```

## K个一组反转链表
```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dump = new ListNode(-1);
        dump.next = head;
        // 上一组的最后一个节点
        ListNode preGroup = dump;
        while(true){
            // 反转组的第一个节点
            ListNode start = preGroup.next;
            // 反转组的最后一个节点
            ListNode end  =start;
            for(int i = 0; i < k-1; i ++){
                if(end == null){
                    return dump.next;
                }
                end = end.next;

            }
            // 如果不足k个直接返回
            if(end == null){
                return dump.next;
            }
            // 断开上一组和下一组的链接
            preGroup.next = null;
            ListNode nextGroup = end.next;
            end.next = null;
            // 反转当前组
            reverse(start);
            // 连接上一组和下一组
            // 此时，反转组的第一个和最后一个节点已经反转过了
            start.next = nextGroup;
            preGroup.next = end;
            preGroup = start;
        }
    }


    public void reverse(ListNode head){
        ListNode pre = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = pre;
            pre =head;
            head = temp;
        }
    }
}
```
