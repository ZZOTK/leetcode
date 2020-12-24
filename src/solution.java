

import javax.naming.InsufficientResourcesException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add((long)1);
        int count = 0;
        while(count != n -1){
            long temp = pq.poll();
            if(!pq.contains(temp * 2)){
                pq.add(temp * 2);
            }
            if(!pq.contains(temp * 3)){
                pq.add(temp * 3);
            }
            if(!pq.contains(temp * 5)){
                pq.add(temp * 5);
            }
            count ++;
        }
        long ans = pq.poll();
        return (int)ans;
    }

    public static void main(String[] args) {
        solution a = new solution();
        System.out.println(a.nthUglyNumber(1364));
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