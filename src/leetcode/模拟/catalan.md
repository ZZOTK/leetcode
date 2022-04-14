给定一个栈，n个数依次进栈一共有多少种出栈的方法。

1. 如果元素a在1号位置，那么只可能a进栈，马上出栈，此时还剩元素b、c、d等待操作，就是子问题f(3)；

2. 如果元素a在2号位置，那么一定有一个元素比a先出栈，即有f(1)种可能顺序（只能是b），还剩c、d，即f(2)，     根据乘法原理，一共的顺序个数为f(1) * f(2)；

3. 如果元素a在3号位置，那么一定有两个元素比1先出栈，即有f(2)种可能顺序（只能是b、c），还剩d，即f(1)，
   根据乘法原理，一共的顺序个数为f(2) * f(1)；

4. 如果元素a在4号位置，那么一定是a先进栈，最后出栈，那么元素b、c、d的出栈顺序即是此小问题的解，即f(3)；


```java
    public static void main(String[] args) {
        int n = 10;
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for(int i = 2; i <= n; i ++){
            for(int j = 0; j < i; j ++ ){
                //核心
                nums[i] += nums[j] * nums[i-1-j];
            }
        }
        System.out.println(nums[n]);
    }
```

回溯模拟（有效括号的做法）
```java
    int ans = 0;
    public void backtrack(int left, int right, int n) {
        // 肯定不合法，提前结束
        //这个判断是核心
        //left < right这个条件保证了括号的合法性
        if (left > n || left < right) {
            return;
        }
        // 到达结束条件
        if (left + right == 2 * n) {
            ans++;
            return;
        }
        // 选择
        backtrack(left + 1, right,n);
        backtrack(left, right + 1,n);
    }
```