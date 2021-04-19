package leetcode.位运算;
//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。

//一个非常有意思的思路，出现几次就是几进制归零。
public class leetcode137 {
    public int singleNumber(int[] nums) {
        int[] bitnum = new int[32];
        int res = 0 ;
        for(int i = 0; i < 32; i ++){
            for(int j = 0; j < nums.length; j ++){
                bitnum[i] += (nums[j]>>i)&1;
            }
            res |= (bitnum[i]%3) <<i;
        }
        return res;
    }
}
