package DFSBFS;

import java.util.Deque;
import java.util.LinkedList;

public class leetcode695DFS {
    public static int maxAreaOfIsland(int[][] grid) {
        int res=0;
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                if(grid[r][c]==1){
                    int a=area(grid,r,c);
                    res=Math.max(res,a);
                }
            }
        }
        return res;
    }
    public static int area(int[][] grid,int r,int c){
        if(r<0||r>=grid.length||c<0||c>=grid[0].length)//等于length就会溢出报错
            return 0;
        if(grid[r][c] != 1)//必须为不等于1，等于0会与等于2的赋值冲突
            return 0;
        grid[r][c]=2;//标记为2表示已经搜查过
        return 1+
                area(grid,r-1,c)+
                area(grid,r,c-1)+
                area(grid,r+1,c)+
                area(grid,r,c+1);
    }
}

