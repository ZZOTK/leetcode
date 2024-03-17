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