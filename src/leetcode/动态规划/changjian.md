# [91. 解码方法](https://leetcode.cn/problems/decode-ways)

## 题目描述

<!-- 这里写题目描述 -->

<p>一条包含字母&nbsp;<code>A-Z</code> 的消息通过以下映射进行了 <strong>编码</strong> ：</p>

<pre>
'A' -&gt; "1"
'B' -&gt; "2"
...
'Z' -&gt; "26"</pre>

<p>要 <strong>解码</strong> 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，<code>"11106"</code> 可以映射为：</p>

<ul>
	<li><code>"AAJF"</code> ，将消息分组为 <code>(1 1 10 6)</code></li>
	<li><code>"KJF"</code> ，将消息分组为 <code>(11 10 6)</code></li>
</ul>

<p>注意，消息不能分组为&nbsp; <code>(1 11 06)</code> ，因为 <code>"06"</code> 不能映射为 <code>"F"</code> ，这是由于 <code>"6"</code> 和 <code>"06"</code> 在映射中并不等价。</p>

<p>给你一个只含数字的 <strong>非空 </strong>字符串 <code>s</code> ，请计算并返回 <strong>解码</strong> 方法的 <strong>总数</strong> 。</p>

<p>题目数据保证答案肯定是一个 <strong>32 位</strong> 的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "12"
<strong>输出：</strong>2
<strong>解释：</strong>它可以解码为 "AB"（1 2）或者 "L"（12）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "226"
<strong>输出：</strong>3
<strong>解释：</strong>它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "06"
<strong>输出：</strong>0
<strong>解释：</strong>"06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 只包含数字，并且可能包含前导零。</li>
</ul>

```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        if(s.charAt(0)- '0' == 0){
            return 0;
        }
        dp[1] = 1;
        for(int i = 1; i < n; i ++){
            int pre = s.charAt(i-1) - '0';
            int las = s.charAt(i) - '0';
            // 将prelas 看做一个两位整数
            // 如果是11-19,21-26，则可以将新加元素与上一个元素组合，或者单独处理
            if((pre == 1 && las != 0 )|| (pre == 2 && las <= 6 && las > 0 )){
                dp[i + 1] = dp[i] +dp[i-1];
             // 如果是10,20，则只能两个元素合并处理，否则不合法   
            }else if((pre == 1 || pre == 2 ) && las == 0){
                dp[i + 1] = dp[i-1];
              // 如果是01-09,或者是27-29,31-39,41-49。。。。则必须将新加的元素单独处理  
            }else if ((pre == 2 && las > 6) || (pre >= 3 && las != 0) || (pre == 0 && las !=0)){
                dp[i+1] = dp[i];
            }else {
                // 其实总结这时的条件是00,30,40。。。,也可以将这个条件写在if里面
                return 0;
            }
        }
        return dp[n];
    }
}
```

152 乘积最大子数组
 
     给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。



示例 1:

输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。

```java
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int max = nums[0];
        for(int i =1 ; i < n; i ++){
            dp[i][0] = Math.max(dp[i-1][0] * nums[i], Math.max(dp[i-1][1] * nums[i], nums[i]));
            dp[i][1] = Math.min(dp[i-1][0] * nums[i], Math.min(dp[i-1][1] * nums[i], nums[i]));
            max= Math.max(max,dp[i][0]);
        }
        return max;
    }
}
```