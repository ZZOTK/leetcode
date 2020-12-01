package leetcode.数组_变形_贪心;

//给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
//若可行，输出任意可行的结果。若不可行，返回空字符串。

public class leetcode76 {
    public String reorganizeString(String S) {
        int n = S.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[S.charAt(i) - 'a']++;
        }
        int max = 0;
        for (int m : count) {
            max = Math.max(m, max);
        }
        //如果超过这个阈值，就直接返回空
        if (max > (n + 1) / 2) {
            return "";
        }
        int l1 = 1;
        int l2 = 0;
        char[] ans = new char[n];
        for (int i = 0; i < 26; i++) {
            //难点
            //如果发现count[i]较大，就要放到从0开始的l2索引上
            while (count[i] > 0 && count[i] <= n/2 && l1 < n) {
                ans[l1] = (char) ('a' + i);
                l1 += 2;
                count[i]--;
            }
            while (count[i] > 0) {
                ans[l2] = (char) ('a' + i);
                l2 += 2;
                count[i]--;
            }
        }
        return new String(ans);
    }
}
