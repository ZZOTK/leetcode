package leetcode.程序员面试金典;
//
//字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
public class ms01_05 {
    public boolean oneEditAway(String first, String second) {
        //相同直接返回
        if(first == second){
            return true;
        }
        int len1 = first.length() ;
        int len2 = second.length();
        int r1 = len1-1;
        int r2 = len2 -1;
        //长度差大于1直接返回
        if(Math.abs(r1-r2) > 1){
            return false;
        }
        int l = 0;
        //双指针判断
        while(l < len1 && l < len2 && first.charAt(l) == second.charAt(l)){
            l++;
        }
        while(r1>= 0 && r2 >= 0  &&first.charAt(r1) == second.charAt(r2)){
            r1--;
            r2--;
        }
        if(r1-l<1 && r2-l < 1){
            return true;
        }
        return false;
    }
}
