package leetcode.哈希表;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class leetcode1365 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        temp = Arrays.copyOf(nums, n);
        //副本加排序
        Arrays.sort(temp);
        Map<Integer, Integer> map = new HashMap<>();
        //值为key，索引为value。需要的为value
        map.put(temp[0],0);
        for(int i = 1; i < n; i++){
            if(temp[i] > temp[i-1]){
                //相同的数只记第一个索引
                map.put(temp[i],i);
            }
        }
        for(int i = 0; i < n; i++){
            //按nums的顺序输出
            temp[i] = map.get(nums[i]);
        }
        return temp;
    }
}
