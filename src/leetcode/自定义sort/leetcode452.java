package leetcode.自定义sort;
//在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
//给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。

//先排序。再重叠区间按照贪心找箭。

import java.util.Arrays;

public class leetcode452 {
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        //这里直接用-1，1而不用减法是防止出界。
        //只需要排序p[1]而不需要p[0]
        Arrays.sort(points, (p1, p2) -> p1[1] < p2[1] ? -1 : 1);
        int res = 1;
        int pre = points[0][1];
        //贪心。如果p[0]的值大于之前的p[1]，则需要多一支箭
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > pre) {
                res++;
                //更新pre为p[i][1]
                pre = points[i][1];
            }
        }
        return res;
    }
}
