package leetcode.数组_变形_贪心;
//有序二维数组搜索目标值。
//每行的元素从左到右升序排列。每列的元素从上到下升序排列。
//固定右上或左下角，再搜索
public class leetcode240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        if(m==0){return false;}
        int n=matrix[0].length;
        //固定右上角，小于目标值向下，大于目标值向左
        int i=0;int j=n-1;
        //界限内搜索，出界则false
        while(i<m&&j>=0){
            if(target==matrix[i][j]){
                return true;
            }else if(target>matrix[i][j]){
                i++;
            }else {
                j--;
            }
        }
        return false;
    }
}
