
import leetcode.剑指offer.offer_68_2;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> dq = new LinkedList<>();
        int ans = 0;
        int top = 0;
        for(int i = 0; i< heights.length; i ++){
            if(dq.isEmpty() || heights[i] >= heights[dq.peek()]){
                dq.push(i);
            }else{
                while(!dq.isEmpty() && heights[i] < heights[dq.peek()]){
                    top  = dq.pop();
                    int area = heights[top]*(i-top);
                    ans = Math.max(area,ans);
                }
                dq.push(top);
                heights[top] = heights[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        solution a = new solution();
        int[] heights = new int[]{2,1,5,6,2,3};
        int ans = a.largestRectangleArea(heights);
        System.out.println(ans);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
