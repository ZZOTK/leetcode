## leetcode 121 

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

* 只能买卖一次

1. 贪心，左侧一定是用最小的。利润是当前-最小，取利润最大

2. 动态规划
   * dp[i][0] 表示i天不持有股票，dp[i][1]表示i天持有股票

```java


```


