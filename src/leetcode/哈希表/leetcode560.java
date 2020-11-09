package leetcode.哈希表;
//给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
//连续，想到思路前缀和。
//连续子数组和为k，即presum[j] - presum[i] = k；表示i到j之间的数组和为k
//重要的思路方法。DFS，回溯中有前缀和哈希表优化
import java.util.HashMap;
import java.util.Map;

public class leetcode560 {
    public int subarraySum(int[] nums, int k) {
        //记录前缀和
        int presum = 0;
        //记录数量
        int count = 0;
        Map<Integer,Integer> ma = new HashMap<>();
        //如果有一个值为k，则int tar = 0;所以0要赋值1
        ma.put(0, 1);
        for(int num : nums){
            presum += num;
            //向前寻找是否有这个值
            int tar = presum - k;
            if(ma.containsKey(tar)){
                //这个值有几次则加几次
                count += ma.get(tar);
            }
            //最后再入表
            ma.put(presum, ma.getOrDefault(presum, 0) + 1);
        }
        return  count;
    }
}
