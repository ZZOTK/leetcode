
import javax.xml.xpath.XPath;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class solution {
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        Map<Integer,Integer> ma = new HashMap<>();
        for(int i = 0; i < n; i ++){
            int distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            ma.put(i, distance);
        }
        PriorityQueue<Integer> que = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ma.get(o2) - ma.get(o1);
            }
        });
        for (int i = 0; i < n; i ++){
            if(que.size() < K){
                que.add(i);
            }
            else if(ma.get(i) <= ma.get(que.peek())){
                que.remove();
                que.add(i);
            }
        }
        int[][] ans = new int[K][2];
        for(int i = 0; i < K; i ++){
            ans[i] = points[que.remove()];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{1,3},{2,-2},{-2,2}};
        solution a = new solution();
        a.kClosest(points,2);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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