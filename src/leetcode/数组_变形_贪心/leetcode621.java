package leetcode.数组_变形_贪心;

//给你一个用字符数组tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，
// 并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
//然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
//你需要计算完成所有任务所需要的 最短时间 。

import java.util.Arrays;

//
public class leetcode621 {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for(char task: tasks){
            count[task - 'A'] ++;
        }
        Arrays.sort(count);
        //以task中出现次数最多的为轮第一个
        int maxlen = count[25];
        int maxc = 1;
        for(int i = 25; i >=1 ; i --){
            if(count[i] == count[i - 1]){
                maxc ++;
            }else{
                break;
            }
        }
        int ans = (maxlen - 1) * (n + 1) + maxc;
        int len = tasks.length;
        //结果不可能小于tasks的长度
        return Math.max(ans,len);
    }
}
