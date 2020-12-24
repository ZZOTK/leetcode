package leetcode.回溯_DFS_BFS;

import java.util.LinkedList;
import java.util.List;

//给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
//形式上，斐波那契式序列是一个非负整数列表 F，且满足：
//0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
//F.length >= 3；
//对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
//另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
//返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。

//剪枝困难。包括1.第一位不为0 2.何时停止
public class leetcode842 {
    List<Integer> ans;
    public List<Integer> splitIntoFibonacci(String S) {
        ans = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        char[] digit = S.toCharArray();
        backtrack(digit,path,0);
        return ans ;
    }

    public void backtrack(char[] digit, List<Integer> path, int index ){
        if(path.size() >= 3 && index == digit.length){
            ans=new LinkedList<>(path);
            return;
        }
        for(int i = index; i < digit.length; i ++ ){
            //确保第一位不为0
            if (digit[index] == '0' && i > index) {
                break;
            }
            long num = subDigit(digit, index, i + 1);
            //如果截取的数字大于int的最大值，则终止截取
            if (num > Integer.MAX_VALUE) {
                break;
            }
            int size = path.size();
            //大于了直接结束
            if (size >= 2 && num > path.get(size - 1) + path.get(size - 2)) {
                break;
            }
            //小于就继续
            if (size >= 2 && num < path.get(size - 1) + path.get(size - 2)) {
                continue;
            }
            if (size <= 1 || num == path.get(size - 1) + path.get(size - 2)) {
                //选择
                path.add((int) num);
                //回溯
                backtrack(digit, path, i + 1);
                //撤销选择
                path.remove(path.size() - 1);
            }
        }
    }
    //string转数字
    private long subDigit(char[] digit, int start, int end) {
        long res = 0;
        for (int i = start; i < end; i++) {
            res = res * 10 + digit[i] - '0';
        }
        return res;
    }
}
