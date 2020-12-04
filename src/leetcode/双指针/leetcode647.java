package leetcode.双指针;
//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
//具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
//方法1.暴力法。
//方法2.暴力法的优化，中心扩展法。这个方法回文字符串用的多。
public class leetcode647 {
    public int countSubstrings(String s) {
        char[] sa = s.toCharArray();
        int ans = 0;
        int len = sa.length;
        for(int i = 0; i < len - 1; i ++){
            for(int j = i + 1; j < len; j ++ ){
                if(isHW(sa,i,j)){
                    ans ++;
                }
            }
        }
        return ans + len;
    }
    public boolean isHW(char[] sa, int i , int j){
        while(j > i){
            if(sa[j] != sa[i]){
                return false;
            }
            j --;
            i ++;
        }
        return true;
    }

    //中心扩展法的优化写法
    public int countSubstrings2(String s) {
        int n = s.length();
        int ans = 0;
        for(int i = 0; i < n; i ++){
            for(int j = 0; j <= 1; j ++){
                int l = i;
                int r = l + j;
                while(l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
                    l --;
                    r ++;
                    ans ++;
                }
            }
        }
        return ans;
    }

    //中心拓展法。
    public int countSubstrings1(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            //这样可以不区分奇偶。
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }
}
