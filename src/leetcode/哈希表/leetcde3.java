package leetcode.哈希表;
//双指针加哈希表
//滑窗思路

import java.util.HashMap;
import java.util.Map;

public class leetcde3 {
    public int lengthOfLongestSubstring(String s) {
        //哈希表，存储元素和位置
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

