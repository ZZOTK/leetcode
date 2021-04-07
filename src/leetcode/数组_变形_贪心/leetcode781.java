package leetcode.数组_变形_贪心;

import java.util.HashMap;
import java.util.Map;
//华为笔试第二题
//向上取整。a/b向上取整 为 （a + b - 1）/ b 。
public class leetcode781 {
    public int numRabbits(int[] answers) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int ans : answers){
            map.put(ans,map.getOrDefault(ans,0) + 1);
        }
        int res = 0 ;
        for(int i : map.keySet()){
            res += (i+map.get(i))/(i+1) * (i+ 1);
        }
        return res;
    }
}
