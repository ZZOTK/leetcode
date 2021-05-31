import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class solution {

    public static AtomicInteger aa = new AtomicInteger(0);

    public static void main(String[] args) {
        aa.get();
        aa.incrementAndGet();

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}