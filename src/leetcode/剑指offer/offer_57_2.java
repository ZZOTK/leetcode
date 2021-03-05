package leetcode.剑指offer;

import java.util.ArrayList;
import java.util.List;

public class offer_57_2 {
    //求和公式法
    public int[][] findContinuousSequence(int target) {
        //方便转二维数组
        List<int[]> vec = new ArrayList<int[]>();
        //指针
        for (int l = 1, r = 2; l < r;) {
            //求和公式
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;

            } else {
                //超过target/2时，l会不断++。最后l==r,跳出。
                l++;
            }
        }
        //list转二维数组
        return vec.toArray(new int[0][]);
    }


    //滑窗法
    public int[][] findContinuousSequence1(int target) {
        int i = 1, j = 2, s = 3;
        List<int[]> res = new ArrayList<>();
        while(i < j) {
            if(s == target) {
                int[] ans = new int[j - i + 1];
                for(int k = i; k <= j; k++)
                    ans[k - i] = k;
                res.add(ans);
            }
            //和超过了，左侧边界收缩.超过target/2则一直收缩直到跳出
            if(s >= target) {
                s -= i;
                i++;
            } else {
                //和小于则右侧边界+1
                j++;
                s += j;
            }
        }
        return res.toArray(new int[0][]);
    }
}
