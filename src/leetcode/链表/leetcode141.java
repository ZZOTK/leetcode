package leetcode.链表;

import java.util.HashSet;
import java.util.Set;

//快慢指针
public class leetcode141 {
    public boolean hasCycle1(ListNode head) {//双指针判断，快慢指针
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    public boolean hasCycle2(ListNode head) {//哈希表的办法
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
          val = x;
          next = null;
      }
  }

}
