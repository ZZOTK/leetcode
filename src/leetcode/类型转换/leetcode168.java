package leetcode.类型转换;

public class leetcode168 {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;//没有0，必须先减一！！！！！！！
            sb.append((char) (n % 26 + 'A'));//ASCII码表进行类型转换
            //sb.insert(0, (char) ('A' + c ));每次插入到第一位
            n =n / 26;
        }
        return sb.reverse().toString();//倒序输出
        // return sb.toString();此时无需倒序输出
    }
}
