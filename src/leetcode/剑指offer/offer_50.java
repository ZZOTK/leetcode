package leetcode.剑指offer;

import java.util.HashMap;
import java.util.Map;
//
//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

//利用哈希表存储是否为只有一次，再遍历数组，找到第一个存储一次的char
public class offer_50 {
    public char firstUniqChar(String s) {
        char[] cs = s.toCharArray();
        Map<Character,Boolean> map = new HashMap<>();
        for(char c : cs){
            map.put(c,map.containsKey(c));
        }
        for(char c : cs ){
            if(!map.get(c)){
                return c;
            }
        }
        return ' ';
    }
}
