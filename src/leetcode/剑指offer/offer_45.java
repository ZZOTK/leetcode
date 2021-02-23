package leetcode.剑指offer;

import java.util.Arrays;
import java.util.Comparator;

//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
public class offer_45 {
    public String minNumber(int[] nums) {
        int n = nums.length;
        String[] copy = new String[n];
        for(int i = 0 ; i < n; i ++){
            copy[i] = nums[i] + "";
        }
        //字符串比较不用-。使用compareTo函数
        //这种情况下使用a+b与b+a比较大小再自定义排序
        //Arrays.sort(copy, (x, y) -> (x + y).compareTo(y + x));
        Arrays.sort(copy, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1) ;
            }
        });
        StringBuilder res = new StringBuilder();
        for(String cop : copy){
            res.append(cop);
        }
        return res.toString();
    }
}
