
import leetcode.剑指offer.offer_68_2;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class solution {
    public double[] dicesProbability(int n) {
        double[] dp = new double[6 * n + 1];
        for(int i = 1 ; i <=6 ; i ++){
            dp[i] = 1.0/6.0;
        }
        for(int i = 2; i <= n ; i ++){
            for(int l = 0 ; l < dp.length; l++){
               dp[l] /= 6.0;
            }
            double[] temp = Arrays.copyOfRange(dp,1,6*(i-1) + 1);
            for(int j = 1 ; j <= temp.length; j ++){
                for(int k = 1; k <= 6; k ++){
                    dp[j + k ] = temp[j -1];
                }
            }
        }
        double[] ans =  Arrays.copyOfRange(dp,n,n * 6 + 1);
        return ans;
    }

    public static void main(String[] args) {
        solution a = new solution();
        double[] ans = a.dicesProbability(2);
        for(double res : ans){
            System.out.println(res);
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
