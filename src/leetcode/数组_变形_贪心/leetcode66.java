package leetcode.数组_变形_贪心;

//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
//
//最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
//
//你可以假设除了整数 0 之外，这个整数不会以零开头。

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
