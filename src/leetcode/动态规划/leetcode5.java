package leetcode.动态规划;
//最长回文字符串，动态规划解法
public class leetcode5 {
    public String longestPalindrome(String s) {
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
