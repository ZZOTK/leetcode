package leetcode.数组_变形_贪心;

//将一个给定字符串根据给定的行数，以从上往下、从左到右进行Z 字形排列。
//比如输入字符串为 "LEETCODEISHIRING"行数为 3 时，排列如下：
//L   C   I   R
//E T O E S I I G
//E   D   H   N
//之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

//请你实现这个将字符串进行指定行数变换的函数：

//分组思维。寻找规律
public class leetcode6 {
    public String convert(String s, int numRows) {
        if(numRows==1) return s;
        int len=s.length();
        int jg=2*numRows-2;
        StringBuilder ans =new StringBuilder();//stringBulider 类进行操作
        for(int i=0;i<numRows;i++) {
            if (i == 0 || i == numRows - 1) {
                for (int j = i; j < len; j += jg) {
                    ans.append(s.charAt(j));
                }
            } else {
                for (int j = i; j < len; j += jg) {
                    ans.append(s.charAt(j));
                    int a = j + jg - 2 * i;//这个下标比较难写
                    if (a < len) {//必须判断，不然会溢出
                        ans.append(s.charAt(a));
                    }
                }
            }
        }
        return ans.toString();
    }
}
