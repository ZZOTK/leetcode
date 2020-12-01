package leetcode.回溯_DFS_BFS;

import java.util.*;

//记忆化搜索，不熟
public class leetcode514 {
    Map<Character, List<Integer>> map;
    int [][]memo;
    public int findRotateSteps(String ring, String key) {
        this.map = new HashMap<>();
        for(int i=0;i<ring.length();i++){
            List<Integer> temp = map.getOrDefault(ring.charAt(i), new ArrayList<Integer>());
            temp.add(i);
            map.put(ring.charAt(i), temp);
        }
        this.memo = new int [ring.length()][key.length()];
        for(int i=0;i<ring.length();i++){
            Arrays.fill(memo[i], -1);
        }
        return key.length()+dfs(ring, key, 0, 0);
    }
    private int dfs(String ring,String key,int indexR,int indexK){
        if(indexK==key.length())    return 0;
        if(memo[indexR][indexK]!=-1)    return memo[indexR][indexK];
        char currChar = key.charAt(indexK);
        int res = Integer.MAX_VALUE;
        for(Integer currlength:map.get(currChar)){
            //遍历索引列表，得到距离
            int s1 = Math.abs(indexR-currlength);
            int s2 = Math.abs(ring.length()-s1);
            int currmin = Math.min(s1, s2);
            res = Math.min(res,currmin+dfs(ring, key, currlength, indexK+1));
        }
        memo[indexR][indexK] = res;
        return res;
    }

}
