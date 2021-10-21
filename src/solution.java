import java.util.*;

public class solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[][] rel = new int[n + 1][n + 1];
        for(int i = 0; i < n ; i ++){
            int nu = sc.nextInt();
            for(int j = 0; j < nu; j ++){
                rel[i + 1][sc.nextInt()] = 1;
            }
        }
        int[] num = new int[n + 1];
        for(int i = 0; i < q; i ++){
            int nu = sc.nextInt();
            int s = sc.nextInt();
            if(nu == 1){
                Deque<Integer> dq = new LinkedList<>();
                dq.add(s);
                while (!dq.isEmpty()){
                    int ss = dq.pop();
                    for(int j = 1; j <= n; j ++){
                        if(rel[ss][j] == 1 && num[j] != 1){
                            dq.add(j);
                        }
                        num[ss] = 1;
                    }
                }
                System.out.println(count1(num));
            }else{
                Deque<Integer> dq = new LinkedList<>();
                dq.add(s);
                while (!dq.isEmpty()){
                    int ss= dq.pop();
                    for(int j = 1; j <= n; j ++){
                        if(rel[j][ss] == 1 && num[j] != 0){
                            dq.add(j);
                        }
                        num[ss] = 0;
                    }
                }
                System.out.println(count1(num));
            }
        }
    }

    public static int count1(int[] num){
        int res = 0;
        for(int nu :num){
            if(nu == 1){
                res ++;
            }
        }
        return res;
    }

}
