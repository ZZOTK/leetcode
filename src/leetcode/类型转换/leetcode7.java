package leetcode.类型转换;
//整数反转。考虑溢出
public class leetcode7 {
    public int reverse(int x) {
        int ans = 0;

        while (x != 0) {
            //考虑溢出
            //其实只要判断一次
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }
}
