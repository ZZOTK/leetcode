
import java.util.*;


public class solution {
    public boolean canJump(int[] nums) {
        int n=nums.length;
        int temp=0;
        for (int i=0;i<n;i++){
            if (i<=temp){
                temp=Math.max(temp,nums[i]+i);
            }
            if (temp>=n-1){
                return true;
            }
        }
        return false;
    }
}