package leetcode.哈希表;
import java.util.*;

//给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
//请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
//子数组 定义为原数组中连续的一组元素。

public class leetcode1590 {
    public int minSubarray(int[] nums, int p) {
        int goal = 0;
        for(int num : nums){
            goal += num;
            goal %= p;
        }
        if(goal == 0){
            return 0;
        }
        int presum = 0;
        int res= nums.length ;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        for(int i = 0; i < nums.length; i ++){
            presum += nums[i];
            presum %= p;
            //取余为正的小技巧
            int tar = (presum - goal + p) % p;
            if(map.containsKey(tar)){
                res = Math.min(i+1 - map.get(tar),res);
            }
            map.put(presum,i + 1);
        }
        if(res == nums.length){
            return -1;
        }
        return res;
    }
}
