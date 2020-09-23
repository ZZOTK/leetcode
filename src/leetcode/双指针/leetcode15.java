package leetcode.双指针;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class leetcode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans= new LinkedList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            //防止重复
            if(i>=1){
                if(nums[i]==nums[i-1]){
                    continue;
                }
            }
            //排序后固定第一个，使用双指针
            if(nums[i]>0){break;}
            int n=-nums[i];
            int las=nums.length-1;
            int pre=i+1;
            while (pre<las){
                if (nums[pre]+nums[las]>n){
                    las--;
                }
                else if (nums[pre]+nums[las]<n){
                    pre++;
                }
                else {
                    List list=new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[pre]);
                    list.add(nums[las]);
                    ans.add(list);
                    // list.add(i);
                    // list.add(pre);
                    // list.add(las);
                    //防止重复，必须加入pre<las
                    while(pre<las&&nums[las]==nums[las-1]){
                        las--;
                    }
                    while(pre<las&&nums[pre]==nums[pre+1]){
                        pre++;
                    }
                    //继续移动指针
                    pre++;
                    las--;
                }
            }
        }
        return ans;
    }
}
