package 双指针;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class leetcode16_24 {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans=new LinkedList<>();
        int i=0;
        int j=nums.length-1;
        while(i<j){
            if(nums[i]+nums[j]<target){
                i++;
            }
            else if (nums[i]+nums[j]>target){
                j--;
            }
            else{
                List list=new LinkedList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                ans.add(list);
                i++;
                j--;
            }
        }
        return ans;
    }
}
