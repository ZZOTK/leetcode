

import javax.naming.InsufficientResourcesException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class solution {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int[] count = new int[14];
        for(int num : nums){
            count[num] ++;
        }
        return true;
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