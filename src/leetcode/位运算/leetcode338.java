package leetcode.位运算;

//给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

public class leetcode338 {
    //方法1.从1到n依次计算1的个数，直接
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popcount(i);
        return ans;
    }
    //这就是leetcode191
    //n&n-1可以将最后一个1置0
    //不断n&n-1就可以统计有多少个n
    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count)
            x &= x - 1; //zeroing out the least significant nonzero bit
        return count;
    }

    //方法二，动态规划
    //遵循上一方法的相同原则，我们还可以通过最低有效位来获得状态转移函数。
    //观察x 和 x/2。   x与x/2只相差1位。即最后一个是否为1.
    //这样，我们就有了下面的状态转移函数：
    //P(x) = P(x /2) + (x&1)

    public int[] countBits2(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            //i>>1即x/2。
            ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
        return ans;
    }

}
