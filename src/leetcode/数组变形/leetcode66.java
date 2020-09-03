package leetcode.数组变形;

public class leetcode66 {
    public int[] plusOne(int[] digits) {
        int len=digits.length;
        for(int i=len-1;i>=0;i--){
            digits[i]+=1;
            digits[i]=digits[i]%10;//遇到10进1
            if(digits[i]!=0){
                return digits;
            }
        }
        digits = new int[len + 1];//全为9此时加一需要多一位，结果必为第一位1，后面全0
        digits[0] = 1;
        return digits;
    }
}
