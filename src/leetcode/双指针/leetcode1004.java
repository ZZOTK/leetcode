package leetcode.双指针;
//给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
//返回仅包含 1 的最长（连续）子数组的长度。

//双指针滑窗
public class leetcode1004 {
    public int longestOnes(int[] A, int K) {
        int l = 0;
        int r= 0;
        int len = A.length;
        int zeros = 0;
        int max = 0;
        while(r < A.length){
            if(A[r] == 0){
                zeros ++;
            }
            while(zeros > K){
                if(A[l] == 0){
                    zeros--;
                }
                l++;
            }
            max = Math.max(max,r-l+1);
            r++;
        }
        return max;
    }
}
