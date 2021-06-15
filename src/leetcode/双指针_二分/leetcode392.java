package leetcode.双指针_二分;

//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
public class leetcode392 {
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
