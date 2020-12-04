package leetcode.数组_变形_贪心;
//统计所有小于非负整数 n 的质数的数量。

//统计质数。筛法和普通的暴力法
public class leetcode204 {
    public int countPrimes(int n) {
        int ans = 0;
        if(n <= 2){
            return ans;
        }
        int[] isp = new int[n];
        for(int i = 2; i < n ; i ++){
            if(isp[i] == 0){
                ans ++;
            }
            if( (long)i * i < n){
                for(int j = i * i; j < n ; j += i ){
                    isp[j] = 1;
                }
            }
        }
        return ans;
    }

    //暴力法
    public int countPrimes1(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    //判断是否为质数
    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

}
