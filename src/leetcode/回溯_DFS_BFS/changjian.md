leetCode 22. 括号生成

> 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

```java
class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        StringBuilder path = new StringBuilder();
        backtrack(n,n,path);
        return  ans;
    }

    public void backtrack(int left, int right, StringBuilder path){
        // 合法性
        if(left < 0 || right < left){
            return;
        }
        // 终点
        if(left == 0 && right == 0){
            ans.add(path.toString());
            return;
        }
        backtrack(left - 1, right, path.append('('));
        // 撤销选择
        path.deleteCharAt(path.length() - 1);
        backtrack(left, right-1, path.append(')'));
        // 撤销选择
        path.deleteCharAt(path.length() - 1);
    }
}

```