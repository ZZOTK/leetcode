package leetcode.滑窗;
//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
//字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
//

import java.util.LinkedList;
import java.util.List;

public class leetcode438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new LinkedList<>();
        //排除空的情况
        if (s == null || p == null || s.length() < p.length()) return ans;
        int len = p.length();
        int[] pArr = new int[26];
        int[] sArr = new int[26];
        //只有小写字母。新建一个26长度的int[]，对应index直接++，记录各个字母的个数
        for (int i = 0; i < len; i++) {
            sArr[s.charAt(i) - 'a']++;
            pArr[p.charAt(i) - 'a']++;
        }
        //初始位置是否相同
        if(isA(pArr , sArr)){
            ans.add(0);
        }
        int i = 1;
        int j = len ;
        while( j < s.length()){
            //第一个位置的--，新加入的++，就是新的需要比较的sArr
            sArr[s.charAt(i-1) - 'a'] --;
            sArr[s.charAt(j) - 'a'] ++;
            if(isA(sArr , pArr)){
                ans.add(i);
            }
            //滑窗
            i++;
            j++;
        }
        return ans;

    }
    //判断是否为异位词。逐个比较每个字母的个数即可
    public boolean isA (int[] Arr , int[] Srr){
        int len = Arr.length;
        for(int i = 0 ; i< len ; i ++ ){
            if(Arr [i] != Srr[i]){
                return false ;
            }
        }
        return true;
    }
}
