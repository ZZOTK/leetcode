## leetcode150
> 根据 逆波兰表示法，求表达式的值。
> 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

用栈模拟逆波兰表达式.注意-,/时的顺序

```java
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
```