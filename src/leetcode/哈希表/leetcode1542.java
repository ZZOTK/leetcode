package leetcode.哈希表;
import java.util.*;

//给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
//超赞子字符串需满足满足下述两个条件：
//该字符串是 s 的一个非空子字符串
//进行任意次数的字符交换后，该字符串可以变成一个回文字符串

public class leetcode1542 {
    public int longestAwesome(String s) {
        int n = s.length();
        //一共十个数字，则有2的10次方个状态
        int[] count = new int[1 << 10];
        Arrays.fill(count,-1);
        count[0] = 0;
        int presum = 0;
        int ans = 0;
        for(int i = 0 ; i < n ; i ++){
            int temp = s.charAt(i) - '0';
            presum ^= 1 << temp;
            if(count[presum] >= 0){
                ans = Math.max(ans,i+1-count[presum]);
            }else{
                count[presum] = i + 1;
            }
            for (int k = 0; k < 10; ++k) {
                if (count[presum ^ (1 << k)] >= 0) {
                    ans = Math.max(ans, i + 1 - count[presum ^ (1 << k)]);
                }
            }
        }
        return ans;

    }
}
