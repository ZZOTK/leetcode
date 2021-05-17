package leetcode.模拟;

import java.util.Deque;
import java.util.LinkedList;

//根据 逆波兰表示法，求表达式的值。
public class leetcode150 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0 ; i < tokens.length; i ++){
            if(tokens[i].equals("+")){
                int a = dq.removeLast();
                int b = dq.removeLast();
                dq.addLast(a + b);
            }else if(tokens[i].equals("-")){
                int a = dq.removeLast();
                int b = dq.removeLast();
                //注意a,b的顺序
                dq.addLast(b - a);
            }else if(tokens[i].equals("*")){
                int a = dq.removeLast();
                int b = dq.removeLast();
                dq.addLast(a * b);
            }else if(tokens[i].equals("/")){
                int a = dq.removeLast();
                int b = dq.removeLast();
                //注意a,b的顺序
                dq.addLast(b / a);
            }else{
                dq.addLast(Integer.valueOf(tokens[i]));
            }
        }
        return dq.peek();
    }
}
