
import java.util.*;


public class solution {
    public static String lengthOfLongestSubstring(String s) {
        int n=s.length();
        int max=0;
        int begin=0;
        boolean[][] dp=new boolean[n][n];
        for (int i=0;i<n;i++){
            dp[i][i]=true;
        }
        for(int j=1;j<n;j++){
            for (int i=0;i<j;i++){
                if (j-i==1){
                    if (s.charAt(i)==s.charAt(j)){
                        dp[i][j]=true;
                    }else dp[i][j]=false;
                }
                if(j-i>1) {
                    dp[i][j]=dp[i+1][j-1]&&(s.charAt(i)==s.charAt(j));
                }
                if (dp[i][j]==true&&(j-i+1>max)){
                    max=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin,begin+max);

    }
}