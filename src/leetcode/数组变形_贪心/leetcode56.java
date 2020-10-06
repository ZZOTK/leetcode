package leetcode.数组变形_贪心;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class leetcode56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 先按照区间起始位置排序，这时comparable接口的写法
        //Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        //另一种实现按第一个元素排序的写法
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        // 遍历区间,合并区间
        int[][] res = new int[intervals.length][2];
        int idx = 0;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == 0 || interval[0] > res[idx-1][1]) {
                res[idx] = interval;
                idx++;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx-1][1] = Math.max(res[idx-1][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx);
    }

}
