package leetcode.哈希表;

import java.util.HashMap;

public class leetcode2342 {
    public int maximumSum(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res  = -1;
        for(int i = 0 ;i < nums.length; i++){
            int sum = getsum(nums[i]);
            if(map.get(sum) != null){
                int max = map.get(sum);
                res = Math.max(max+nums[i],res);
            }
            map.put(sum,Math.max(nums[i],map.getOrDefault(sum,0)));
        }
        return res;
    }

    public int getsum(int num){
        int res = 0;
        while(num !=0 ){
            res += num%10;
            num /= 10;
        }
        return res;
    }
}
