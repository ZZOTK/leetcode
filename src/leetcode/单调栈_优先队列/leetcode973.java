package leetcode.单调栈_优先队列;
//我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
//（这里，平面上两点之间的距离是欧几里德距离。）
//类似leetcode347,利用优先队列排序。java中默认为从小到大。
import java.util.*;

public class leetcode973 {
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        Map<Integer,Integer> ma = new HashMap<>();
        for(int i = 0; i < n; i ++){
            int distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            ma.put(i, distance);
        }
        PriorityQueue<Integer> que = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ma.get(o2) - ma.get(o1);
            }
        });
        for (int i = 0; i < n; i ++){
            if(que.size() < K){
                que.add(i);
            }
            else if(ma.get(i) <= ma.get(que.peek())){
                que.remove();
                que.add(i);
            }
        }
        int[][] ans = new int[K][2];
        for(int i = 0; i < K; i ++){
            ans[i] = points[que.remove()];
        }
        return ans;
    }

    public int[][] kClosest1(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] * o1[0] + o1[1] * o1[1] - o2[0] * o2[0] - o2[1] * o2[1];
            }
        });
        return Arrays.copyOfRange(points,0,K);
    }
}
