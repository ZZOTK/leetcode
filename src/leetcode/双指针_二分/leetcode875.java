package leetcode.双指针_二分;

public class leetcode875 {
    public int minEatingSpeed(int[] piles, int h) {
        int r = 0;
        for(int pile : piles){
            r = Math.max(r,pile);
        }
        int l = 1;
        while(l <= r){
            int mid = l+(r-l)/2;
            int temp = config(piles,mid);
            if(temp > h){
                l = mid + 1;
            }else if(temp <= h){
                r = mid -1;
            }
        }
        return l;
    }

    public int config(int[] piles, int k){
        int count = 0;
        for(int pile : piles){
            count += (pile + k - 1)/k;
        }
        return count;
    }
}
