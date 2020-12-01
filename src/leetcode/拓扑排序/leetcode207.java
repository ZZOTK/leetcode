package leetcode.拓扑排序;
//你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
//在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
//给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？

//拓扑排序问题。dfs，bfs思路都可以

import java.util.ArrayList;
import java.util.List;

public class leetcode207 {

    List<List<Integer>> edges;
    int[] visited;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        visited = new int[numCourses];
        for (int i = 0; i < numCourses ; ++i) {
            if(dfs(i)){
                return false;
            }
        }
        return true;
    }

    public boolean dfs( int u){
        if(visited[u] == 1){
            return true;
        }
        if(visited[u] == 2){
            return false;
        }
        visited[u] = 1;
        for(int i : edges.get(u)){
            if(dfs(i)){
                return true;
            }
        }
        visited[u] = 2;
        return false;
    }
}
