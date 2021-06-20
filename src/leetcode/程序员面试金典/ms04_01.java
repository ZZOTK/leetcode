package leetcode.程序员面试金典;
//节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。

import java.util.*;

//基础的邻接表加BFS
public class ms04_01 {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        boolean[] visted = new boolean[n];
        //创建领接表
        ArrayList[] edge = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            edge[i] = new ArrayList<Integer>();
        }
        for(int[] gra : graph){
            edge[gra[0]].add(gra[1]);
        }
        Deque<Integer> dq= new LinkedList<>();
        dq.add(start);
        visted[start] =true;
        //用队列BFS
        while(!dq.isEmpty()){
            int node = dq.poll();
            for(int i = 0; i < edge[node].size(); i ++){
                int temp = (int) edge[node].get(i);
                if(visted[temp]){
                    continue;
                }
                if(temp == target){
                    return true;
                }
                dq.add(temp);
                visted[temp] = true;
            }
        }
        return false;
    }
}
