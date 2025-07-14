## 单调栈
单调栈的思路很简单,往栈内压入元素时，排出在他之前比他大（或比他小）的**所有元素**（所以需要用到while）。

单调栈可以作什么？
* 找到第一个比他小或者大的数（位置）
* 利用位置的查来计算结果

简单的示意代码
```java
    for(int i : nums){
        while(!stack.isEmpty && i < stack.peek()){
            stack.pop();
        }
        stack.push(i);
    }
```

## 剑指offer33
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，
否则返回 false。假设输入的数组的任意两个数字都互不相同。

```java
    public boolean verifyPostorder(int[] postorder) {
        int n = postorder.length;
        Deque<Integer> stack = new LinkedList<>();
        int root = Integer.MAX_VALUE;
        for(int i = n - 1; i >= 0; i--) {
            if(postorder[i] > root) {
                return false;
            }
            while(!stack.isEmpty() && stack.peek() > postorder[i]){
                root = stack.pop();
            }
            stack.add(postorder[i]);
        }
        return true;
    }
```

## 84. 柱状图中最大的矩形

核心：找到当前柱子，左右第一个小于它高度的位置。

单调栈左右遍历

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
         int n = heights.length;
        int[] l = new int[n];
        int[] r = new int[n];
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i =0 ; i <n; i ++){
            // 注意这里的=，相同高度可以继续下压
            while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]){
                stack.pop();
            }
            // 也就是这个柱子左侧没有比他低的柱子
            if(stack.isEmpty()){
                l[i] = -1;
            }else {
                l[i] = stack.peek();
            }
            stack.push(i);
        }
        stack.clear();

        for(int i =n-1 ; i >=0; i --){
            while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]){
                stack.pop();
            }
            if(stack.isEmpty()){
                r[i] = n;
            }else {
                r[i] = stack.peek();
            }
            stack.push(i);
        }

        for(int i =0; i < n; i ++){
            max = Math.max(max,(r[i] -l[i] -1) *heights[i]);
        }
        return max; 
        
    }
}
```

## leetcode 95 

```java
class Solution {

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return 0;
        }
        int m = matrix[0].length;
        int[][] heights = new int[n + 1][m];
        // 构建数组，height[i][j]表示以第i行为底，每一列的高度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    heights[i + 1][j] = heights[i][j] + 1;
                }
            }
        }
        int max = 0;
        // for循环每一行，计算每一行为底的最大高度
        for (int i = 1; i <= n; i++) {
            int[] height = heights[i];
            // 调用leetcode84
            int levelMax = largestRectangleArea(height);
            max = Math.max(max, levelMax);
        }
        return max;
    }
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] l = new int[n];
        int[] r = new int[n];
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                l[i] = -1;
            } else {
                l[i] = stack.peek();
            }
            stack.push(i);
        }
        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                r[i] = n;
            } else {
                r[i] = stack.peek();
            }
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            max = Math.max(max, (r[i] - l[i] - 1) * heights[i]);
        }
        return max;
    }


}
```

## leetcode907

给定一个整数数组 arr，找到 min(b)的总和，其中 b 的范围为 arr 的每个（连续）子数组。
由于答案可能很大，因此 返回答案模 10^9 + 7 。

如果i位置的数为子数组的最小值，那么：
* 左边右边的数一定小于i，确保i位置为最小值
* 假设左边界为l（下标），右边界为r，那么arr[i] 一共出现 (i-l)*(r-i)次
* 考虑最小值同为arr[i]的情况，需要将等于放到左边或者右边确保不重复，不遗漏

利用单调栈找到左右位置，有数组记录，最后计算。

```java
public class leetcode907 {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] pre = new int[n];
        Deque<Integer> stack1 = new LinkedList<>();
        //找左边第一个小于它的位置
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
        //找右边第一个小于等于它的位置
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
```

## leetcode 42

接雨水

```java
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                //左边界
                int left = stack.peek();
                //宽度
                int currWidth = i - left - 1;
                //深度，左右中的小值
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }
}

```

另一个思路

```java
public class leetcode42 {
    //使用两个数组l,r,分别存放每个位置左边的最高高度(包括自身)，和右边的最高高度（包括自身）
    //每个位置能接的最多雨水就是 water = min(l[i], r[i]) - height[i]
    public int trap(int[] height) {
        int n = height.length;
        if(n==0){
            return 0;
        }
        int[] left = new int[n];
        left[0] = height[0];
        int[] right = new int[n];
        right[n-1] = height[n-1];
        for(int i= 1 ; i < n; i ++){
            left[i] = Math.max(left[i-1],height[i]);
        }
        for(int i = n-2; i >=0; i--){
            right[i] = Math.max(right[i+1],height[i]);
        }
        int sum = 0;
        for(int i = 0 ; i < n; i ++){
            sum += Math.min(left[i],right[i]) -height[i];
        }
        return sum;
    }
}
```
