package leetcode.哈希表;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class leetcode350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length){
            return intersect(nums2,nums1);
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0)+1;//计数有几个
            map.put(num, count);
        }
        int[] ans =new int[nums1.length];
        int index=0;
        for(int num:nums2){
            int count=map.getOrDefault(num,0);
            if (count>0){
                ans[index]=num;
                index++;
                count--;
                if(count>0){
                    map.put(num,count);
                }
                else{
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(ans, 0, index);
    }
}

