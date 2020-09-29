package leetcode.类型转换;

import java.util.Stack;

public class leetcode20 {
    public boolean isValid(String s) {
        Stack<Character> st=new Stack<>();
        int n=s.length();
        //减少一下工作量
        if (n%2==1){
            return false;
        }
        for (int i=0;i<n;i++){
            if (s.charAt(i)=='('){
                st.push(')');
            }
            else if(s.charAt(i)=='['){
                st.push(']');
            }
            else if (s.charAt(i)=='{'){
                st.push('}');
            }
            //重点，还需判断是否为空
            else if(st.isEmpty()||s.charAt(i)!=st.pop()){
                return false;

            }
        }
        return st.isEmpty();
    }
}
