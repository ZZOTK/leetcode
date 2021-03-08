
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
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] count = new int[len][2];
        for(int i = 0 ;i < len; i ++){
            String temp = strs[i];
            int count0 = 0;
            int count1 = 0;
            for(int j = 0; j < temp.length(); j ++){
                if(temp.charAt(j) == '1'){
                    count0++;
                }else if(temp.charAt(j) == '0'){
                    count1 ++;
                }
            }
            count[i][0] = count0;
            count[i][1] = count1;
        }

        int[][][] dp = new int[n + 1][m + 1][n + 1];
        dp[0][0][0] = 1;
        for(int i = 1 ;i <= len ; i++){
            for(int j = 0; j <=m; j ++ ){
                for(int k = 0 ; k <= n; k++){
                    if(k < count[i][0] || j < count[i][1]){
                        dp[i][j][k] = dp[i-1][j][k];
                    }else{
                        dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-count[i][1]][k-count[i][0]];
                    }
                }
            }
        }
        return dp[len][m][n];

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
