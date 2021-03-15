
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
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int N = reader.nextInt();
        int[] weight = new int[N];
        int[] count = new int[N];
        for(int i = 0; i < N ; i ++){
            weight[i] = reader.nextInt();
        }
        for(int i = 0; i < N ; i ++){
            count[i] = reader.nextInt();
        }
        Set<Integer> set = new HashSet<>();
        dfs(0,set,weight,count);
        System.out.println(set.size());
    }

    public static void dfs(int sum, Set<Integer> set,int[] weight, int[] count){
        set.add(sum);
        for(int i= 0; i < weight.length;i++){
            if(count[i--] == 0){
                break;
            }
            dfs(sum+weight[i],set,weight,count);
        }
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
