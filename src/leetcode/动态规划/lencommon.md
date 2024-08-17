最长公共子序列与子串的问题。

子序列与子串的区别就是是否连续。子序列可以不连续，子串必须连续。都可以用动态规划来解决问题。

## leetcode1143 最长公共子序列

> 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
> 
> 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
> 
> 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
> 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

动态规划，dp[i][j]表示text1的前i个与text2的前j个的最长公共子序列。
显然，当text1[i] == text2[j] 时，dp[i][j] = dp[i-1][j-1] + 1.

当不相等时，dp[i][j] = max(dp[i-1][j] , dp[i][j-1]).

```java
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n][m];
        if(text1.charAt(0) == text2.charAt(0)){
            dp[0][0] =1;
        }
        //第一行与第一列单独赋值
        for(int i = 1 ;i < n; i++){
            if(text1.charAt(i) == text2.charAt(0)){
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }
        for(int j = 1 ;j < m; j++){
            if(text1.charAt(0) == text2.charAt(j)){
                dp[0][j] =1;
            } else {
                dp[0][j] = dp[0][j-1];
            }
        }
        //从（1，1）开始dp。
        for (int i = 1; i < n; i++) {
            for(int j = 1; j <m; j ++){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n-1][m-1];
    }
    
    // 不单独处理
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int n = text1.length();
            int m = text2.length();
            int[][] dp =new int[n+1][m+1];
            for(int i = 0; i < n; i ++){
                char s = text1.charAt(i);
                for(int j = 0; j < m; j ++){
                    if(text2.charAt(j) == s){
                        dp[i+1][j+1] = dp[i][j] + 1;
                    }else {
                        dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]);
                    }
                }
            }
            return dp[n][m];
        }
    }
```

## leetcode718 最长重复子数组

> 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

状态dp[i][j]表示，**A[i]与B[j]相等的情况下**， A的前i个与B的前j个的最长子串。
为什么这里要加相等？因为时公共子串，最长时，A[i]B[j]一定相等。

由于必须要连续，所以当且仅当A[i] == B[j] 时，有状态方程 dp[i][j] = dp[i-1][j-1] + 1;

```java
    public int findLength(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int[][] dp = new int[n][m];
        //第一行与第一列单独赋值
        for (int i= 0; i<m; i ++){
            if(A[0] == B[i]){
                dp[0][i] = 1;
            }
        }
        for (int i= 0; i<n; i ++){
            if(B[0] == A[i]){
                dp[i][0] = 1;
            }
        }
        //用max变量记录最大值
        int max = 0;
        for(int i = 1; i < n; i ++){
            for(int j = 1; j <m; j ++){
                if(A[i] == B[j]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max= Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }
```
补充一个思路：滑窗加哈希。利用一个hash函数判断一个长度为k的序列是否为最长公共子序列。二分去加快k的搜索。

这里的hash算法有一个专门为子串设计的RK（Rabin-Karp ）算法，了解看看。

```java
class Solution {
    int mod = 1000000009;
    int base = 113;

    public int findLength(int[] A, int[] B) {
        int left = 1, right = Math.min(A.length, B.length) + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(A, B, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    public boolean check(int[] A, int[] B, int len) {
        long hashA = 0;
        for (int i = 0; i < len; i++) {
            hashA = (hashA * base + A[i]) % mod;
        }
        Set<Long> bucketA = new HashSet<Long>();
        bucketA.add(hashA);
        long mult = qPow(base, len - 1);
        for (int i = len; i < A.length; i++) {
            hashA = ((hashA - A[i - len] * mult % mod + mod) % mod * base + A[i]) % mod;
            bucketA.add(hashA);
        }
        long hashB = 0;
        for (int i = 0; i < len; i++) {
            hashB = (hashB * base + B[i]) % mod;
        }
        if (bucketA.contains(hashB)) {
            return true;
        }
        for (int i = len; i < B.length; i++) {
            hashB = ((hashB - B[i - len] * mult % mod + mod) % mod * base + B[i]) % mod;
            if (bucketA.contains(hashB)) {
                return true;
            }
        }
        return false;
    }
    
    // 使用快速幂计算 x^n % mod 的值
    public long qPow(long x, long n) {
        long ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                ret = ret * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return ret;
    }
}
```
 


