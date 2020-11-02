package leetcode.动态规划;
//数组中的山峰。left和right矩阵，记录左右边符合条件的数的数量
public class leetcode845 {
    public int longestMountain(int[] A) {
        int n=A.length;
        if(n<3){return 0;}
        int max=0;
        int[] left=new int[n];
        int[] right=new int[n];
        left[0]=1;
        right[n-1]=1;
        //左边连续比他小的数
        for(int i=1;i<n;i++){
            if(A[i]>A[i-1]){
                left[i]=left[i-1]+1;
            }else{left[i]=1;}
        }
        //右边连续比他小的数
        for(int i=n-2;i>=0;i--){
            if(A[i]>A[i+1]){
                right[i]=right[i+1]+1;
            }else{right[i]=1;}
        }
        int[] dp=new int[n];
        for(int i=0;i<n;i++){
            //这个if条件确保存在峰。
            if(left[i]>1&&right[i]>1){
                //值为左右相加再减一
                dp[i]=left[i]+right[i]-1;
                max=Math.max(dp[i],max);
            }
        }
        return max;
    }
}
