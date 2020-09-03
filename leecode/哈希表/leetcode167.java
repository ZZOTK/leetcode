package 哈希表;

import java.util.HashMap;
import java.util.Map;

public class leetcode167 {
    public int[] twoSum(int[] num, int target) {
        Map<Integer, Integer> map = new HashMap<>(num.length);
        for (int i = 0; i < num.length; i++) {
            if (map.get(target - num[i]) != null) {
                return new int[]{map.get(target - num[i]) + 1, i + 1};
            }
            map.put(num[i], i);
        }
        return new int[]{0, 0};
    }
}
//题目中num已排序，双指针就可以，哈希表方法做练习学习用