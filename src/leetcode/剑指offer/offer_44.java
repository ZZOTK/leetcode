package leetcode.剑指offer;
//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
//
//请写一个函数，求任意第n位对应的数字。
//
public class offer_44 {
    public int findNthDigit(int n) {
        //数字位数(例如三位数digit=3)
        int digit = 1;
        //digit位数的第一个数字
        long start = 1;
        //所有digit位数所占的位数
        long count = 9;
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        //n-1是因为start是第0个数字
        //找到是哪个数字
        long num = start + (n - 1) / digit;
        //（n-1)%digit就是余数，也就是第几位。
        return Long.toString(num).charAt((n - 1) % digit) - '0'; //  -'0'可以转换为int
    }
}
