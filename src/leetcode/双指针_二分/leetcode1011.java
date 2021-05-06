package leetcode.双指针_二分;

public class leetcode1011 {
    public int shipWithinDays(int[] weights, int D) {
        int r = 0;
        int l = 0;
        for(int weight : weights){
            r += weight;
            l = Math.max(l,weight);
        }

        while(l <= r){
            int mid = l + (r-l)/2;
            int temp = config(weights,mid);
            if(temp > D){
                l = mid + 1;
            }else{
                r = mid -1;
            }
        }
        return l;
    }

    public int config(int[] weights, int k){
        int sum = 0;
        int count = 1;
        for(int weight : weights){
            if(sum + weight > k){
                count ++;
                sum = weight;
            }else{
                sum += weight;
            }
        }
        return count;
    }
}
