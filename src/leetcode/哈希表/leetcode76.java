package leetcode.哈希表;

import java.util.HashMap;
import java.util.Map;

//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//哈希表加滑窗

public class leetcode76 {
    public String minWindow(String s, String t) {
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> se = new HashMap<>();
        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();
        for(char tch : tchar){
            //t的每个char入哈希表
            need.put(tch,need.getOrDefault(tch,0) + 1);
        }
        int left = 0;
        int right = 0;
        int n = schar.length;
        int len = n + 2;
        int valid = 0;
        int start = 0;
        while(right < n){
            //c为右端字符
            char c = schar[right];
            //如果c在need中
            if(need.containsKey(c)){
                //se记录当前窗口中的
                se.put(c,se.getOrDefault(c,0) + 1);
                //有一个值相同了，说明这个字符是子字符了，valid++
                if(se.get(c).equals(need.get(c))){
                    valid ++;
                }
            }
            //说明此时窗口已经完全包含t
            while (valid == need.size()){
                //比较窗口大小。比之前小就记录
                if(right - left < len){
                    start = left;
                    len  = right - left;
                }
                //d为最左侧的字符。left++后出窗口
                char d = schar[left];
                left ++ ;
                //d在子字符串中
                if(need.containsKey(d)){
                    //如果d正好，则valid--。
                    if(se.get(d).equals(need.get(d))){
                        valid --;
                    }
                    //d移除了窗口所以se中d的value减一
                    se.put(d,se.getOrDefault(d,0) - 1);
                }
            }
            //右移
            right ++;
        }
        if(len == n + 2){
            return "";
        }else{
            return s.substring(start,start+len+1);
        }
    }
}
