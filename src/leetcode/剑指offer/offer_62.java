package leetcode.剑指offer;
//0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
public class offer_62 {
    // 递归做法
    public int lastRemaining(int n, int m) {
        return f(n,m);
    }

    public int f(int n, int m){
        if(n == 1){
            return 0;
        }
        int x = f(n-1, m);
        return (x+m)%n;
    }
}
