package leetcode.类型转换;

public class leetcode14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }//排除为空的特殊情况
        String ans=strs[0];
        for(int j=1;j<strs.length;j++){
            ans=comm(ans,strs[j]);//两两取最大公共前缀
        }
        return ans;
    }
    public static String comm(String strs1,String strs2){
        int len=Math.min(strs1.length(),strs2.length());
        int i=0;
        while(i<len){
            if (strs1.charAt(i)==strs2.charAt(i))
                i++;
            else return strs1.substring(0,i);
        }
        return strs1.substring(0,i);//字符串中取子字符串
    }
}
