package leetcode.剑指offer;
//例子如3234，high=3, pow=1000, last=234
//
//可以将数字范围分成两部分1~999，1000~1999，2000~2999和3000~3234
//
//1~999这个范围1的个数是f(pow-1)
//1000~1999这个范围1的个数需要分为两部分：
//千分位是1的个数：千分位为1的个数刚好就是pow，注意，这儿只看千分位，不看其他位
//其他位是1的个数：即是999中出现1的个数，为f(pow-1)
//2000~2999这个范围1的个数是f(pow-1)
//3000~3234这个范围1的个数是f(last)
//所以全部加起来是pow + high*f(pow-1) + f(last);
//
public class offer_43 {
    public int countDigitOne(int n) {
        return f(n);
    }
    private int f(int n ) {
        if (n <= 0)
            return 0;
        String s = String.valueOf(n);
        //最高位
        int high = s.charAt(0) - '0';
        int pow = (int) Math.pow(10, s.length()-1);
        int last = n - high*pow;
        //核心
        if (high == 1) {
            return f(pow-1) + last + 1 + f(last);
        } else {
            return pow + high*f(pow-1) + f(last);
        }
    }

    public int countDigitOne1(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}
