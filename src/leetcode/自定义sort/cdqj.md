## leetcode452
用最少的箭射爆气球

1. 按照points[ ][1]排序
2. 贪心。当有points[0][ ]超过当前的最大值(即上一个points[ ][1]),结果加一
3. 统计的结果就是最少的重叠区间！
```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] -o2[1];
            }
        });
        int res = 1;
        int max = points[0][1];
        for(int i = 0; i < points.length; i ++){
            if(points[i][0] > max){
                res ++;
                max = points[i][1];
            }
        }
        return res;
    }
}
```


