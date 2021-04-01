package leetcode.数组_变形_贪心;
//给定一个包含n + 1 个整数的数组nums，其数字都在 1 到 n之间（包括 1 和 n），
// 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
//链接：https://leetcode-cn.com/problems/find-the-duplicate-number
//思路1，不使用额外空间。
//思路2，常规解法，使用set

import java.util.HashSet;

public class leetcode287 {
    //类似二分查找的方式找到结果
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        //l=1是因为nums中的数字从1开始
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) /2;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }


    public int findDuplicate1(int[] nums) {
        HashSet<Integer> visited = new HashSet<>();
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(!visited.contains(nums[i])){
                visited.add(nums[i]);
            }else{
                return nums[i];
            }
        }
        return -1;
    }
}
