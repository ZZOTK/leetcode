package leetcode.单调栈_优先队列;
import java.util.*;

//给定一个整数数组 arr，找到 min(b)的总和，其中 b 的范围为 arr 的每个（连续）子数组。
//由于答案可能很大，因此 返回答案模 10^9 + 7 。

public class leetcode907 {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] pre = new int[n];
        Deque<Integer> stack1 = new LinkedList<>();
        for(int i = 0 ; i < n ; i ++){
            //这里的等于号加在上下都行，但是要加一个
            while(!stack1.isEmpty() && arr[i] <= arr[stack1.peek()]){
                stack1.pop();
            }
            if(stack1.isEmpty()){
                pre[i] = -1;
            }else {
                pre[i] = stack1.peek();
            }
            stack1.push(i);
        }

        Deque<Integer> stack2 = new LinkedList<>();
        int[] las = new int[n];
        for(int i = n-1; i >=0 ;i --){
            while(!stack2.isEmpty() && arr[i] < arr[stack2.peek()]){
                stack2.pop();
            }
            if(stack2.isEmpty()){
                las[i] = n;
            }else{
                las[i] = stack2.peek();
            }
            stack2.push(i);
        }

        int res = 0;
        int mod = 1000000007;
        for(int i = 0 ; i <n ;i ++){
            //防止溢出
            long temp = (long)arr[i] *((long) (i - pre[i]) * (las[i] - i) % mod) % mod;
            res += temp;
            res = res % mod;
        }
        return res;
    }
}
