package leetcode.数组变形_贪心;
//维护两个变量，最小值和最大利润;
public class leetcode121 {
    public int maxProfit(int[] prices) {
        int max=0;
        int n=prices.length;
        if(n<=1){
            return max;
        }
        int minv=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]<minv){
                minv=prices[i];
            }else if(prices[i]-minv>max){
                max=prices[i]-minv;
            }
        }
        return max;

    }
}
