package leetcode.单调栈_优先队列;
//请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
//例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

//暴力法与单调栈的方法

import java.util.Deque;
import java.util.LinkedList;

public class leetcode739 {
    //单调栈的做法
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] ans = new int[n];
        Deque<Integer> sta = new LinkedList<>();
        for(int i = 0; i < n; i ++){
            //栈不为空且当前元素大于栈顶元素
            while (!sta.isEmpty() && T[i] > T[sta.peek()]){
                int k  = sta.pop();
                ans[k] = i - k;
            }
            sta.push(i);
        }
        return ans;
    }

    //暴力法，没啥说的
    public int[] dailyTemperatures1(int[] T) {
        int n = T.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i ++){
            for(int j = i + 1 ; j < n; j ++){
                if(T[j] > T[i]){
                    ans[i] = j - i;
                    break;
                }else if(j == n - 1 && T[j] <= T[i]){
                    ans[i] = 0;
                }
            }
        }
        return ans;
    }

}
