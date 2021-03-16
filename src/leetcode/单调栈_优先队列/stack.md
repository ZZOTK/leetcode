## 单调栈
单调栈的思路很简单,往栈内压入元素时，排出在他之前比他大（或比他小）的**所有元素**（所以需要用到while）。

单调栈可以作什么？
* 
* 

简单的示意代码
```java
    for(int i : nums){
        while(!stack.isEmpty && i < stack.peek()){
            stack.pop();
        }
        stack.push(i);
    }
```