package leetcode.剑指offer;
//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
public class offer_29 {
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
}
