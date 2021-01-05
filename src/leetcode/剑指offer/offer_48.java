package leetcode.剑指offer;

import java.util.HashMap;
import java.util.Map;
//两种HashMap的解法。
//核心思想都是找到第一个重复的位置，即左指针。
public class offer_48 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Boolean> ma=new HashMap<>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(!ma.containsKey(s.charAt(i))){
                ma.put(s.charAt(i),true);
            }else{
                while(s.charAt(left) != s.charAt(i)){
                    ma.remove(s.charAt(left));
                    left ++;
                }
                left ++;
            }
            max = Math.max(i - left + 1,max);
        }
        return max;
    }

    public int lengthOfLongestSubstring1(String s) {
        Map<Character,Integer> ma=new HashMap<>();
        //左指针
        int left=0;
        int max=0;
        //i为右指针
        for (int i=0;i<s.length();i++){
            if (ma.containsKey(s.charAt(i))){
                //移动左指针，即滑窗。两个里面取最大值
                left=Math.max(ma.get(s.charAt(i))+1,left);
            }
            //存值，在if判断之后
            ma.put(s.charAt(i),i);
            max=Math.max(max,i-left+1);
        }
        return max;
    }
}
