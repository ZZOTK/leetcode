leetcode240
```java
 public int[][] mat;
    public int tar;
    public boolean searchMatrix(int[][] matrix, int target) {
        mat = matrix;
        tar = target;
        int right = mat[0].length -1 ;
        int down = mat.length - 1;
        return search(0,right,0,down);
    }

    public boolean search(int left, int right, int up, int down){
        if(left > right || up > down){
            return false;
        }else if(mat[up][left] > tar || mat[down][right] < tar){
            return false;
        }
        int mid = (left + right)/2;
        int row = up;
        while(row <= down && mat[row][mid] <= tar){
            if(mat[row][mid] == tar){
                return true;
            }
            row ++;
        }
        return search(left,mid-1,row,down) || search(mid + 1, right,up,row-1); 
    }
```