package leetcode.剑指offer;
// 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
// 二进制加法的写法
//
public class offer_65 {
    public int add(int a, int b) {
        int yu = a&b;
        int yihuo = a^b;
        while(yu != 0 ){
            int e = yihuo;
            int f= a&b<<1;
            yu = e&f;
            yihuo = e^f;
        }
        return yihuo;
    }
}
