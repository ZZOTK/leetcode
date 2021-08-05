package leetcode.程序员面试金典;
import java.util.*;

public class ms08_04 {
    List<List<Integer>> ans= new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        int len = 1 << n;
        for(int i = 0; i < len ; i ++){
            List<Integer> path = new LinkedList<>();
            for(int j = 0 ; j < n ;j ++){
                if(((i >> j) & 1) == 1){
                    path.add(nums[j]);
                }
            }
            ans.add(path);
        }
        return ans;
    }
}
