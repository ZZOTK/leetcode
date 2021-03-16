## leetcode39
> 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
candidates中的数字可以无限制重复被选取。

求和为target的子数组。数字可以无限制重复选取。很容易的，想到回溯算法。简单的思路如下。
```java
public void backtrack(){
    if(越界){返回}；
    if(正确){加入一个解}；
    //遍历数组    
    for(int i){
        做出选择
        backtrack（回溯）
        撤销选择
        }
}
```
这里面唯一的难点就是for循环中backtrack怎么写。由于数字可以无限次数使用，我们下一次遍历就从当前的i开始。
* 不能每次都从0开始。这样会有重复。

```java
public class leetcode39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Stack<Integer> path = new Stack<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }
    
    /**
     * @param candidates 数组
     * @param begin   数组的开始位置
     * @param len     数组的长度
     * @param target   目标和
     * @param path   路径
     * @param res   输出的结果
     */
    private void dfs(int[] candidates, int begin, int len, int target, Stack<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        //回溯向后
        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.push(candidates[i]);

            //重难点。保证了不重复。
            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);

            // 状态重置
            path.pop();
        }
    }
}
```

## leetcode40
> 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
> candidates中的每个数字在每个组合中只能使用一次。
> 说明：所有数字（包括目标数）都是正整数。解集不能包含重复的组合。

难点：
* candidates中包含重复数字。
* 解集不包含重复的组合

怎么解决：
1. 问题：有三个1，和为2，如何只输出一个（1，1）
    * 我们画出树，回溯的时候，需要：**树的每一层不包含重复元素**.如果树的一层中有重复的元素，那么解集中必然有重复的结果
    
2. 问题：有{1,7,1},和为8，如何避免（1，7）和（7，1）这两个解
    * 排序原来的数组。使得遍历从小到大开始。再保证每一层不含重复元素，这样就只有（1，7）这个解。
    
对于排序过的candidates，每一层的去重方案：
1. 每一层遍历加入一个set。如果set中已有元素，则直接continue；
2. candidates[i] == candidates[i-1]，则去重。

两种方案的参考代码均给出

方案1
```java
public class leetcode40 {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new LinkedList<>();
        //关键，必须sort
        Arrays.sort(candidates);
        Deque<Integer> path = new LinkedList<>();
        backtrack(candidates,target,path,0);
        return ans;
    }

    public void backtrack(int[] candidates, int target, Deque<Integer> path, int start){
        if(target == 0){
            ans.add(new LinkedList<>(path));
            return;
        }
        for(int i = start; i < candidates.length; i ++){
            //由于从小到大排列，那么target < candidates[i]时可以直接break
            //注意为break.这个枝往后都不用执行。
            if(target - candidates[i] < 0){
                break;
            }
            //难点！
            //在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
            //第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
            //也就是说，此时添加的元素都在同一层！所以可以去除相同的
            if(i > start && candidates[i] == candidates[i-1]){
                continue;
            }
            path.addFirst(candidates[i]);
            backtrack(candidates,target - candidates[i], path, i + 1);
            path.pollFirst();
        }
    }
}
```

方案2
```java
class Solution {
    List<List<Integer>> ans;
    Deque<Integer> dq;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new LinkedList<>();
        dq = new LinkedList<>();
        Arrays.sort(candidates);
        backtrack(candidates,target,0);
        return ans;
    }

    public void backtrack(int[] candidates,int target ,int begin){
        if(target < 0){
            return;
        }
        if(target == 0){
            ans.add(new ArrayList<>(dq));
        }
        //直接每一层加入一个set
        Set<Integer> set = new HashSet<>();
        for(int i = begin; i < candidates.length; i ++){
            //利用set去重
            if(set.contains(candidates[i])){
                continue;
            }
            set.add(candidates[i]);
            dq.add(candidates[i]);
            backtrack(candidates,target - candidates[i], i + 1);
            dq.removeLast();
        }
    }
}
```