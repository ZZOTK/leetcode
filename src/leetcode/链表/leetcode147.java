package leetcode.链表;

//插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
//每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。

public class leetcode147 {

    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start = dummy.next;
        ListNode cur = start.next;
        while (cur != null) {
            if (start.val <= cur.val) {
                start = start.next;
            } else {
                ListNode tem = dummy;
                //必须用tem.next.val。这样while的结果正好在应插入的前面
                while (cur.val >= tem.next.val) {
                    tem = tem.next;
                }
                //重点难点
                start.next = cur.next;
                cur.next = tem.next;
                tem.next = cur;
            }
            cur = start.next;
        }
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
