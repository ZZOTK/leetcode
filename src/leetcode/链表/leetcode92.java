package leetcode.链表;
//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
//说明:1 ≤ m ≤ n ≤ 链表长度。
public class leetcode92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode h1 = pre;
        for(int i = 0 ; i < left -1 ; i ++){
            h1= h1.next;
        }
        //找到第m-1个节点
        //迭代，头插法
        ListNode h2 = h1.next;
        //原来的h2会是中间反转后的尾节点
        ListNode las = h2;
        ListNode h3 = h2.next;
        for(int i = 0; i < right -left; i ++){
            ListNode temp = h3.next;
            h1.next = h3;
            h3.next = h2;
            h2 = h3;
            h3 = temp;
        }
        //中间反转后的尾节点指向最后的头节点
        las.next = h3;
        return pre.next;
    }

}
