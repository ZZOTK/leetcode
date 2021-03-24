package leetcode.模拟;
//给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
//nums.length == n
//nums[i] 是 正整数 ，其中 0 <= i < n
//abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
//nums 中所有元素之和不超过 maxSum
//nums[index] 的值被 最大化
//返回你所构造的数组中的 nums[index] 。
//注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。

public class leetcode1802 {
    public static int maxValue(int n, int index, int maxSum) {
        // 全部初始化为1
        maxSum -= n;
        int nIndex = 1;
        int l = index;
        int r = index;
        while(l >= 0 && r < n && maxSum - (r - l + 1) >= 0){
            maxSum -= r - l + 1;
            nIndex++;
            l--;
            r++;
        }
        if (l == -1){
            while(r < n && maxSum -(r + 1) >= 0){
                maxSum -= r + 1;
                r++;
                nIndex++;
            }
        }
        if(r == n){
            while(l >= 0 && maxSum - (n - l) >= 0){
                maxSum -= n - l;
                l--;
                nIndex++;
            }
        }
        if (l == -1 && r == n){
            maxSum /= n;
            nIndex += maxSum;
        }
        return nIndex;
    }
}
