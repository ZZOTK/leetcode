offer_29

顺时针打印矩阵
```java
    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if(m == 0){
            return new int[0];
        }
        int n = matrix[0].length;
        //l1第一行，l2最后一行
        int l1 = 0; int l2 = m -1;
        //r1第一列，r2最后一列
        int r1 = 0; int r2 = n -1;
        int index = 0;
        int[] ans = new int[m*n];
        while(true){
            for(int i = r1 ; i <= r2;  i ++){
                ans[index++] = matrix[l1][i];
            }
            if(++l1 > l2){break;}
            for(int i = l1; i <= l2; i ++ ){
                ans[index++] = matrix[i][r2];
            }
            if(--r2 < r1){break;}
            for(int i = r2; i >= r1; i--){
                ans[index++] = matrix[l2][i];
            }
            if(--l2 < l1){break;}
            for(int i = l2; i >= l1; i--){
                ans[index++] = matrix[i][r1];
            }
            if(++r1 >r2){break;}

        }
        return ans;
    }
```

面试题01.07

顺时针旋转矩阵

1. 先沿对角线翻转，沿中间列翻转
2. 先沿中间行翻转，再沿对角线翻转
```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //沿对角线翻转
        for(int i= 0; i < n; i ++){
            for(int j = i + 1 ; j < n ; j ++){
                swap(matrix,i,j,j,i);
            }
        }
        //沿中间列翻转
        for(int i = 0 ; i < n; i ++){
            for(int j = 0 ; j < n/2; j ++){
                swap(matrix,i,j,i,n-j -1);
            }
        }
    }
    
    public void swap(int[][] matrix,int i1, int i2, int j1, int j2){
        int temp = matrix[i1][i2];
        matrix[i1][i2] = matrix[j1][j2];
        matrix[j1][j2] = temp;
    }
}
```

