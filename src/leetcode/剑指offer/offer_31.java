package leetcode.剑指offer;
//输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
// 序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

import java.util.Deque;
import java.util.LinkedList;

//用一个栈模拟
public class offer_31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int p1 = 0;
        int p2 = 0;
        Deque<Integer> sta = new LinkedList<>();
        while(p1 < n){
            sta.push(pushed[p1]);
            p1 ++;
            while(!sta.isEmpty() && sta.peek() == popped[p2]){
                sta.pop();
                p2 ++;
            }
        }
        if(!sta.isEmpty()){
            return false;
        }
        return true;
        //直接return sta.isEmpty()即可

    }
}
