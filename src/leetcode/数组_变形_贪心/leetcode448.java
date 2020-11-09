package leetcode.数组_变形_贪心;

//给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
//找到所有在 [1, n] 范围之间没有出现在数组中的数字。
//原地操作不用哈希表。
//遍历数组，将值对应的索引处的数取负。再遍历数组，找到所有数字大于0的索引，即为结果

import java.util.LinkedList;
import java.util.List;

public class leetcode448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new LinkedList<>();
        for( int num : nums ){
            int newindex = Math.abs(num) - 1;
            if(nums[newindex] > 0){
                nums[newindex] *= -1;
            }
        }
        for( int i = 0; i < nums.length ; i++){
            if(nums[i] > 0){
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
