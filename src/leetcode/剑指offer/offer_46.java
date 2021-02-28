package leetcode.剑指offer;
//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
//注意String的一些操作函数与比较
//动态规划，类似走楼梯一次1或2步
public class offer_46 {
    public int translateNum(int num) {
        //num变string
        String src = String.valueOf(num);
        int n = src.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 1; i < n; i ++){
            //substring前闭后开
            String pre = src.substring(i - 1, i + 1);
            //String与String的数字大小比较 .compareTo
            if(pre.compareTo("25")<=0&&pre.compareTo("10")>=0){
                dp[i + 1] = dp[i -1] + dp[i];
            }else{
                dp[i +1] = dp[i];
            }
        }
        return dp[n];
    }
}
