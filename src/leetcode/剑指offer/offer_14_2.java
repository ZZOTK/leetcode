package leetcode.剑指offer;

//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
// 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
//答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

//与14_1比，重点在于要取余。动态规划算法已不适用。

public class offer_14_2 {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        //必须使用long，int不够大
        long res = 1;
        long mod = 1000000007;
        while (n > 4) {
            res *= 3;
            n -= 3;
            //是否超出
            if (res > mod) {
                res %= mod;
            }
        }
        res *= n;
        res = res > mod ? res % mod : res;
        return (int)res;
    }
}
