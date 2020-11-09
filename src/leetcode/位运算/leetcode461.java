package leetcode.位运算;
//两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
//给出两个整数 x 和 y，计算它们之间的汉明距离。

//取x XOR y ，即只有xy不同时才为1。再统计有多少个1
public class leetcode461 {
    public int hammingDistance(int x, int y) {
        //x^y 取xor
        int xor = x ^ y;
        int count = 0;
        while(xor != 0){
            count++;
            //计算二进制中有多少个1的位算法，不断x&(x-1)
            xor = xor&(xor - 1);
        }
        return count;
    }

}
