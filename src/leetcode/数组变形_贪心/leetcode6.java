package leetcode.数组变形_贪心;
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
