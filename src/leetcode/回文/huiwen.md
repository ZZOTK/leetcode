```java
public class leetcode5 {
    //中心扩散法遍历做法
    public String longestPalindrome(String s) {
        int n = s.length();
        int max = 0;
        String res = s;
        for(int i = 0; i < n-1; i ++){
            String longest = getLongest(s, i, i);
            String longest1 = getLongest(s, i, i + 1);
            if(longest.length() >max){
                max = longest.length();
                res = longest;
            }
            if(longest1.length() >max){
                max = longest1.length();
                res = longest1;
            }
        }
        return res;
    }

    public String getLongest(String s, int i, int j){
        int n = s.length();
        while(i >=0 && j<n && s.charAt(i) == s.charAt(j) ){
            i--;
            j++;
        }
        return s.substring(i+1,j);
    }

    //动态规划做法
    public String longestPalindrome1(String s) {
        int n=s.length();
        //特殊情况到单独考虑
        if(n==0){return "";}
        if(n==1){return s;}
        int max=1;
        int begin=0;
        //动态规划矩阵
        boolean[][] dp=new boolean[n][n];
        for (int i=0;i<n;i++){
            dp[i][i]=true;
        }
        //确保i<j的循环写法
        for(int j=1;j<n;j++){
            for (int i=0;i<j;i++){
                if (j-i==1){
                    if (s.charAt(i)==s.charAt(j)){
                        dp[i][j]=true;
                    }else dp[i][j]=false;
                }
                if(j-i>1) {
                    //转移方法
                    dp[i][j]=dp[i+1][j-1]&&(s.charAt(i)==s.charAt(j));
                }
                //取出字符串
                if (dp[i][j]==true&&(j-i+1>max)){
                    max=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin,begin+max);
    }
}
```

leetcode516 最长回文子序列

子序列就不能用中心扩散法了。需要用动态规划

* dp[n][m]表示n-m下标下，s的最长回文子序列
* 状态转移和回文子串是一样的
* 最终结果是dp[0][n],就是整个字符串的最长回文子序列,所以第一行是最后填充的

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i ++){
            dp[i][i] = 1;
        }
        // 注意dp顺序
        for(int i = n-1; i >= 0; i --){
            for(int j = i + 1; j <n ; j ++){
                if(s.charAt(i) ==s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

}
```