package leetcode.动态规划;
//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。
//注意：1-A，26-Z,0没有编码
//类似Offer_46，注意排查0的问题
public class leetcode91 {
    public int numDecodings(String s) {
        if(s.charAt(0)=='0'){
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;dp[1] = 1;
        for(int i = 1; i< s.length(); i ++){
            if(s.charAt(i) == '0' && (s.charAt(i-1) == '0' || s.charAt(i-1) > '2') ){
                return 0;
            }
            String temp = s.substring(i-1,i+1);
            //string.compareTo函数注意使用。
            //此处的dp[i+1] = dp[i-1]难点 测试用例 “2101”
            if(temp.compareTo("10")==0 || temp.compareTo("20") == 0  ){
                dp[i + 1] = dp[i - 1];
            }else if(temp.compareTo("10")>0 && temp.compareTo("26") <= 0 ){
                dp[i + 1] = dp[i -1] + dp [i];
            }else {
                dp[i + 1] = dp[i];
            }
        }
        return dp[s.length()];
    }
}
