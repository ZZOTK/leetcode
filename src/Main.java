import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int n = N - 1;
        int m = M - 1;
        double re = yangHui(n,m);
        //能输出数字就输出
        if(re < Math.log(Long.MAX_VALUE)){
            System.out.println(Math.round(Math.exp(re)));
        }else{
            System.out.println("结果为e的" + re + "次方");
        }
    }

    public static double yangHui(int n,int m){
        if(n < m){
            return -1;
        }
        if(m == 0 || m == n || n == 0){
            return 1;
        }
        if(m > n/2){
            m = n - m;
        }
        double ans = 0;
        //取对数
        for(int i = n; i > n- m ; i --){
            ans += Math.log(i);
        }
        for(int i = 1; i <= m; i ++){
            ans -= Math.log(i);
        }
        return ans;
    }
}

