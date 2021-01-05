

import javax.naming.InsufficientResourcesException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class solution {
    public ListNode deleteDuplicates(ListNode head) {
        Deque<ListNode> dq = new LinkedList<>();
        ListNode start = head;
        dq.add(start);
        start = start.next;
        while(start != null){
            if(start.val == dq.peek().val){
                while(start != null && start.val == dq.peek().val){
                    start = start.next;
                }
                dq.pollFirst();
            }
            dq.addFirst(start);
            start =start.next;
        }
        ListNode ans = dq.pollLast();
        ListNode dummy =new ListNode(0);
        dummy.next = ans;
        while(!dq.isEmpty()){
            ans.next = dq.pollLast();
            ans = ans.next;
        }
        return dummy.next;

    }

    public static void main(String[] args) {

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
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