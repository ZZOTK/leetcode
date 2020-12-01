package leetcode.哈希表;
//给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
//为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

import java.util.HashMap;
import java.util.Map;
//h哈希表存储a+b的和以及出现次数。再遍历c+d，找是否匹配a+b.找到就加上次数。
public class leetcode454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        Map<Integer, Integer> ma = new HashMap<>();
        int ans = 0;
        for(int a : A){
            for(int b : B){
                ma.put(a + b , ma.getOrDefault(a + b, 0) + 1);
            }
        }
        for(int c : C){
            for(int d : D){
                if(ma.containsKey(- (c + d))){
                    ans += ma.get(-(c + d));
                }
            }
        }
        return ans;

    }
}
