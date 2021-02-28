
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
    int ans = Integer.MAX_VALUE;
    int res = Integer.MAX_VALUE;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        int n = baseCosts.length;
        int m = toppingCosts.length;
        for(int i = 0 ; i < n; i ++){
            int target2 = target - baseCosts[i];
            ans = Integer.MAX_VALUE;
            backtrack(target2,toppingCosts,0);
            res = Math.min(res,ans);
        }
        return target - res;
    }

    public void backtrack(int target2 , int[] toppingCosts,int start){
        if(target2 < 0){
            return;
        }
        ans = Math.min(target2,ans);
        for(int i = start; i < toppingCosts.length; i ++){
            backtrack(target2 - toppingCosts[i],toppingCosts,start + 1);
            backtrack(target2 - 2 * toppingCosts[i],toppingCosts,start + 1);
            backtrack(target2 ,toppingCosts,start + 1);
        }
    }


    public class TreeNode {
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
