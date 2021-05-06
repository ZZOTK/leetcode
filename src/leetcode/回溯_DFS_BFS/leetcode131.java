package leetcode.回溯_DFS_BFS;

import java.util.ArrayList;
import java.util.List;

//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

public class leetcode131 {
    List<List<String>> ans = new ArrayList<>();
    List<String> path = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backtrack(s,0);
        return ans;
    }

    public void backtrack(String s, int start){
        if(start == s.length()){
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int j = start ; j < s.length();j ++){
            String temp = s.substring(start,j+1);
            if(ishui(temp)){
                path.add(temp);
                backtrack(s,j+1);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean ishui(String s){
        int r = s.length() - 1;
        int l = 0;
        while(l < r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }
            l ++;
            r --;
        }
        return true;
    }
}
